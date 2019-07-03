package com.mooyle.seckill.controller;

import com.mooyle.entity.model.Comments;
import com.mooyle.seckill.result.Result;
import com.mooyle.seckill.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/demo", produces="application/json;charset=UTF-8")
public class SampleController {

    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/db/get", method = RequestMethod.GET)
    @ResponseBody
    public Result<Comments> dbGet(){
        Comments comment = commentService.getById("1");
        return Result.success(comment);
    }

}
