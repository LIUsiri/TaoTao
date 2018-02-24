package com.taotao.sso.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * class_name: UserController
 * package: com.taotao.sso.controller
 * describe: TODO
 * @author: Liuxianglong
 * @date: 2018/2/6
 * creat_time: 20:58
 **/

@Controller
public class UserController {

    @Autowired
    private UserRegisterService userRegisterService;


    @RequestMapping("/user/check/{param}/{type}")
    @ResponseBody
    public TaotaoResult checkDate(@PathVariable String param,@PathVariable Integer type){


        TaotaoResult taotaoResult = userRegisterService.checkData(param, type);

        return taotaoResult;
    }


    @RequestMapping(value="/user/register",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult register(TbUser user) {

        TaotaoResult result = userRegisterService.register(user);
        return result;
    }


}
