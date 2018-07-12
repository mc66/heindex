package com.cmri.um.he.index.operations.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.operations.entity.AppOriginalContentEntity;
import com.cmri.um.he.index.operations.service.AppOriginalContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
/**
 * 运营指数中内容得分
 * @author shihao
 * Created on 2018/7/11
 */
@RestController
@CrossOrigin
public class AppOriginalContentController extends ZRestController{
    @Autowired
    private AppOriginalContentService appOriginalContentService;

    /**
     * 添加内容的原始数据(三个内容分类)
     * @param map
     * @return
     */
    @RequestMapping(value = "/app-original-content",method = RequestMethod.POST)
    public ResponseMessage saveContent(@RequestBody Map<String , List<AppOriginalContentEntity>> map){
        List<AppOriginalContentEntity> list = map.get("data");
        boolean b=appOriginalContentService.saveContent(list);
        ResponseMessage responseMessage=this.genResponseMessage();
        if(b){
            responseMessage.setMsg("添加成功");
        }
        return responseMessage;
    }

    /**
     * 查询内容的分类
     * @return
     */
    @RequestMapping(value = "/app-content-category",method =RequestMethod.GET)
    public ResponseMessage getContent(){
        List<Map<String,Object>> list= appOriginalContentService.getContent();
        ResponseMessage responseMessage=this.genResponseMessage();
        responseMessage.set("list",list);
        return responseMessage;

    }

}
