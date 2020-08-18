package com.markerhub.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.markerhub.common.lang.Result;
import com.markerhub.entity.User;
import com.markerhub.service.UserService;
import com.markerhub.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.BeanUtils;
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
        if(userService.save(user))
            return Result.success(user);
        else
            return Result.fail("Sign Up failed!");
    }

    @PostMapping("/edit")
    @RequiresAuthentication
    public Result edit(@Validated @RequestBody User user){
        User temp = userService.getById(ShiroUtil.getProfile().getId());
        BeanUtils.copyProperties(user, temp, "id", "status", "created");
        userService.saveOrUpdate(temp);
        return Result.success(temp);
    }

    @GetMapping("/getUserByUserName/{username}")
    public Result getUserByUserName(@PathVariable("username") String username){
        User user = userService.getOne(new QueryWrapper<User>().eq("username",username));
        if(user == null) {
            return Result.success(false);
        } else {
            return Result.success(true);
        }
    }
}
