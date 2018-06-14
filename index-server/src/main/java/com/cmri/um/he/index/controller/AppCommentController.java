package com.cmri.um.he.index.controller;

import com.cmri.spring.common.controller.ZRestController;
import com.cmri.spring.common.data.ResponseMessage;
import com.cmri.um.he.index.service.AppCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 口碑指数，当前是应用的评论
 * @author lch
 * Created on 2018/06/13 14:51
 */
@RestController
@RequestMapping("/app-comment")
@CrossOrigin
public class AppCommentController extends ZRestController{
    @Autowired
    private AppCommentService commentService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseMessage get(@RequestParam String month,@RequestParam String category, @RequestParam Integer page, @RequestParam Integer step) {

            return commentService.find(month, category, page, step).updateResponse(genResponseMessage());
    }
}
