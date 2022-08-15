package com.atyyx.spring.Dao.impl;

import com.atyyx.spring.Dao.UserDao;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository // 表示是个持久层组件
public class UserDaoImpl implements UserDao {

    @Override
    public void saveUser() {
        System.out.println("保存用户成功");
    }
}
