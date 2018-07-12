package com.cmri.um.he.index.operations.service.impl;

import com.cmri.um.he.index.operations.dao.AppCalculationOperationsDao;
import com.cmri.um.he.index.operations.dao.AppOriginalContentDao;
import com.cmri.um.he.index.operations.entity.AppCalculationOperationsEntity;
import com.cmri.um.he.index.operations.entity.AppOriginalContentEntity;
import com.cmri.um.he.index.operations.service.AppOriginalContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * 运营指数中内容得分
 * @author shihao
 * Created on 2018/7/12
 */
@Service
public class AppOriginalContentServiceImpl implements AppOriginalContentService {

    @Autowired
    private AppOriginalContentDao appOriginalContentDao;

    @Autowired
    private AppCalculationOperationsDao appCalculationOperationsDao;

    /**
     * 查询内容的分类
     * @return
     */
    @Override
    public List<Map<String, Object>> getContent() {
        List<Map<String, Object>> list=appOriginalContentDao.getContent();
        return list;
    }

    @Override
    public boolean saveContent(List<AppOriginalContentEntity> list) {

        int app=0;
        String month="";
        int category=0;
        String version="";
        for (AppOriginalContentEntity appOriginalContentEntity : list) {
            app=appOriginalContentEntity.getApp();
            category=appOriginalContentEntity.getCategory();
            month=appOriginalContentEntity.getMonth();
            version=appOriginalContentEntity.getVersion();
            List<Map<String, Object>> list2=appOriginalContentDao.getContent();
            for (int i=0;i<list2.size();i++){
                int id = (int)list2.get(i).get("id");
                if(appOriginalContentEntity.getContentId()==id){
                    appOriginalContentEntity.setAtime(new Date());
                    //保存内容数据
                    appOriginalContentDao.saveContent(appOriginalContentEntity);
                }
            }
        }
        List list3= new ArrayList();
        List<Map<String, Object>> list4=appOriginalContentDao.getContent();
        for (int i=0;i<list4.size();i++){
            //查得样本库大小
           int contentId=(int)list4.get(i).get("id");
           int count1= appOriginalContentDao.getContentByContentId(contentId,app);
            //查得测量值为1的数据数
           int count2= appOriginalContentDao.getContentByContentIdAndMeasureValue(contentId,1,app);
           //计算得分
            if(count1==0){
                double sorce=0.0;
                list3.add(sorce);
            }else {
                double sorce=new BigDecimal((float)count2/count1*100).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                list3.add(sorce);
            }
        }
        double content=0.0;
        double contents=0.0;
        for (int i=0;i<list3.size();i++){
             contents +=(double) list3.get(i);
             content=new BigDecimal((float)contents/3).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        }
        //封装数据
        AppCalculationOperationsEntity appCalculationOperationsEntity=new AppCalculationOperationsEntity();
        appCalculationOperationsEntity.setCategory(category);
        appCalculationOperationsEntity.setApp(app);
        appCalculationOperationsEntity.setVersion(version);
        appCalculationOperationsEntity.setMonth(month);
        appCalculationOperationsEntity.setContent(content);
        appCalculationOperationsEntity.setStatus(0);
        appCalculationOperationsEntity.setAtime(new Date());
        //根据app查询是否存在内容得分
        boolean b=false;
        String contentValue=appCalculationOperationsDao.getDataByApp(app,month,version);
        if(contentValue==null){
            //保存得分
            int number=appCalculationOperationsDao.saveContentValue(appCalculationOperationsEntity);
            if(number>0){
                b=true;
            }
        }else if(contentValue!=null){
            //修改得分
            int number=appCalculationOperationsDao.updateContentValue(content,app,month,version);
            if(number>0){
                b=true;
            }
        }
        return b;
    }
}
