package com.pal.controller;


import com.pal.model.User;
import com.pal.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2017/8/16.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    private static Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;



    @GetMapping(value = "/all/{pageNum}/{pageSize}", produces = {"application/json;charset=UTF-8"})
    public List<User> findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){

        return userService.findAllUser(pageNum,pageSize);
    }
}
