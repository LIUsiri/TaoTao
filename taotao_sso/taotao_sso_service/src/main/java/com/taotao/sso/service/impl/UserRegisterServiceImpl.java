package com.taotao.sso.service.impl;

import com.sun.org.apache.regexp.internal.RE;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.sso.service.UserRegisterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * class_name: UserRegisterServiceImpl
 * package: com.taotao.sso.service.impl
 * describe: TODO
 *
 * @author: Liuxianglong
 * @date: 2018/2/6
 * creat_time: 20:32
 **/

@Service
public class UserRegisterServiceImpl implements UserRegisterService {

    @Autowired
    private TbUserMapper tbUserMapper;


    @Override
    public TaotaoResult checkData(String param, Integer type) {
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        if (type == 1) {
            criteria.andUsernameEqualTo(param);
        } else if (type == 2) {
            criteria.andPhoneEqualTo(param);
        } else if (type == 3) {
            criteria.andEmailEqualTo(param);
        } else {
            return TaotaoResult.build(400, "非法的参数类型");
        }
        //执行查询
        List<TbUser> list = tbUserMapper.selectByExample(example);
        if (list.size() > 0 && list != null) {
            return TaotaoResult.ok(false);
        }


        return TaotaoResult.ok(true);
    }

    @Override
    public TaotaoResult register(TbUser user) {
        //1.校验用户名和密码不能为空
        if (StringUtils.isEmpty(user.getUsername())) {
            return TaotaoResult.build(400, "注册失败.请校验数据后请再提交数据");
        }
        if (StringUtils.isEmpty(user.getPassword())) {
            return TaotaoResult.build(400, "注册失败.请校验数据后请再提交数据");
        }
        //2.校验数据的可用性
        //2.1校验用户名是否可用
        TaotaoResult checkData = checkData(user.getUsername(), 1);
        if (!(Boolean) checkData.getData()) {
            return TaotaoResult.build(400, "用户名已经被注册");
        }
        //2.2校验手机
        if (StringUtils.isNotBlank(user.getPhone())) {
            TaotaoResult checkDataPhone = checkData(user.getPhone(), 2);
            if (!(Boolean) checkDataPhone.getData()) {
                return TaotaoResult.build(400, "手机已经被注册");
            }
        }
        //2.3email
        if (StringUtils.isNotBlank(user.getEmail())) {
            TaotaoResult checkDataEmail = checkData(user.getEmail(), 2);
            if (!(Boolean) checkDataEmail.getData()) {

                return TaotaoResult.build(400, "邮箱已经被注册");
            }
        }
        //密码加密
        String password = user.getPassword();
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        user.setPassword(md5Password);
        //补全属性
        user.setCreated(new Date());
        user.setUpdated(user.getCreated());
        tbUserMapper.insertSelective(user);

        return TaotaoResult.ok();
    }
}
