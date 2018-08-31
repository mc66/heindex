package com.cmri.um.he.index.operations.dao;

import com.cmri.um.he.index.operations.entity.AppOriginalContentEntity;
import com.cmri.um.he.index.operations.mapper.AppOriginalContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
/**
 * 运营指数中内容得分
 * @author shihao
 * Created on 2018/7/12
 */
@Repository
public class AppOriginalContentDao {

    @Autowired
    private AppOriginalContentMapper mapper;

    /**
     * 查询内容的分类
     * @return list
     */
    public List<Map<String, Object>> getContent(){
        List<Map<String, Object>> list=mapper.getContent();
        return list;
    }

    /**
     * 添加内容数据
     * @param appOriginalContentEntity
     */
    public void saveContent(AppOriginalContentEntity appOriginalContentEntity){
        mapper.saveContent(appOriginalContentEntity);

    }

    /**
     * 查得样本库大小
     * @param contentId
     * @return
     */
    public int getContentByContentId(int contentId,int app,String month){
        int count1= mapper.getContentByContentId(contentId,app,month);
        return count1;
    }

    /**
     * 查得测量值为1的数据数
     * @param contentId
     * @param measuredValue
     * @return
     */
    public int getContentByContentIdAndMeasureValue(int contentId,int measuredValue,int app,String month){
        int count2= mapper.getContentByContentIdAndMeasureValue(contentId,measuredValue,app,month);
        return count2;
    }
}
