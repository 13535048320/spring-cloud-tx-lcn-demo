package com.example.clienttwo.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.example.clienttwo.mapper.TestMapper;
import com.example.clienttwo.model.Test;
import com.example.clienttwo.service.ITestService;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
public class TestServiceImpl extends BaseServiceImpl<Test> implements ITestService {

    private static final String BASE_MESSAGE = "";

    private static final Logger LOGGER = LogManager.getLogger("TestServiceImpl");

    @Resource
    private TestMapper testMapper;

    @Override
    protected Mapper<Test> getBaseMapper() {
        return testMapper;
    }

    @Override
    protected String getBaseMessage() {
        return BASE_MESSAGE;
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }

    @Override
//    @GlobalTransactional
    @LcnTransaction //分布式事务注解
    @Transactional  //本地事务注解
    public void insertTest(String name) {
        testMapper.insertTest(name);
//        int a = 1 / 0;
    }
}
