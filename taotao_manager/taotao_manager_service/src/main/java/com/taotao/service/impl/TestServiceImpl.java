package com.taotao.service.impl;

import com.taotao.mapper.TestMapper;
import com.taotao.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * class_name: TestServiceImpl
 * package: com.taotao.service.impl
 * describe: TODO
 * @author: Liuxianglong
 * @date: 2018/1/23
 * creat_time: 16:24
 **/

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public String getNow() {
        return testMapper.getNow();
    }
}
