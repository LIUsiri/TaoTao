package com.taotao.sso.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

/**
 * class_name: UserRegisterService
 * package: com.taotao.sso.service
 * describe: TODO
 * @author: Liuxianglong
 * @date: 2018/2/6
 * creat_time: 20:28
 **/


public interface UserRegisterService {

    public TaotaoResult checkData(String param,Integer type);

    public TaotaoResult register(TbUser user);
}
