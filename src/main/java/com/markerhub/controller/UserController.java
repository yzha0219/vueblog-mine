package com.markerhub.controller;


import com.markerhub.common.lang.Result;
import com.markerhub.entity.User;
import com.markerhub.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2020-07-05
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequiresAuthentication
    @GetMapping("/getUserById/{id}")
    public Object getUserById(@PathVariable("id") Long id){
        User user = userService.getById(id);
        if(user == null)
            return Result.fail("500","operate failed");
        return Result.success(user);
    }

    @PostMapping("/save")
    public Result save(@Validated @RequestBody User user){
        return Result.success(user);
    }

}
