package com.cmri.um.he.index.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.service.OriginalExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 查询某类未添加应用体验接口
 *
 * @author limin
 * Created on 2018/6/26
 */
@RestController
@RequestMapping("/app-quality-original-experience-list")
@CrossOrigin
public class OriginalExperienceController  extends ZRestController {

    @Autowired
    private OriginalExperienceService originalExperienceService;

    /**
     *
     * @param category
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseMessage get( @RequestParam Integer category) {
        List<Map<String, Object>> maps = originalExperienceService.find( category);
        ResponseMessage responseMessage = this.genResponseMessage();
        responseMessage.set("emotions",maps);
        return responseMessage;
    }




}
