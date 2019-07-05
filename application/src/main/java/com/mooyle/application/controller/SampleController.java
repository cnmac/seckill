package com.mooyle.application.controller;

import com.mooyle.entity.model.Comments;
import com.mooyle.common.redis.UserKey;
import com.mooyle.application.result.Result;
import com.mooyle.application.service.CommentService;
import com.mooyle.application.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/demo", produces = "application/json;charset=UTF-8")
public class SampleController {

    @Autowired
    CommentService commentService;

    @Autowired
    RedisService redisService;

    @RequestMapping(value = "/db/get", method = RequestMethod.GET)
    @ResponseBody
    public Result<Comments> dbGet() {
        Comments comment = commentService.getById("1");
        return Result.success(comment);
    }

    @RequestMapping(value = "/redis/incr", method = RequestMethod.GET)
    @ResponseBody
    public Result<Boolean> redisSet() {
        boolean isSet = redisService.set(UserKey.getPrefixById, "1", 123);

        return Result.success(isSet);
    }

    @RequestMapping(value = "/redis/setget", method = RequestMethod.GET)
    @ResponseBody
    public Result<String> redisGet() {
//        boolean isSet = redisService.set(new UserKey(60, "id"), "1", "hello");
        String res = redisService.get(new UserKey("id"), "1", String.class);
        return Result.success("get res:" + res);
    }

}
