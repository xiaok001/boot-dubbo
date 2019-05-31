package ck.janko.controller;


import ck.janko.Service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "数据库链接")
@RestController
@RequestMapping("/testBoot")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取用户", notes = "根据id查询用户信息")
    @GetMapping("getUser/{id}")
    public String GetUser(@PathVariable int id){
        return userService.Sel(id).toString();
    }
}
