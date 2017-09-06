package com.redbee.serviceImp;

import com.redbee.bean.UserBean;
import com.redbee.repositories.UserRepository;
import com.redbee.repositories.UserRepositoryCrud;
import com.redbee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by goroma on 17/08/2017.
 */
@Service
public class UserServiceImp implements UserService {

    private UserRepositoryCrud userRepositoryCrud;
    private UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepositoryCrud userRepositoryCrud,UserRepository userRepository) {
        this.userRepositoryCrud = userRepositoryCrud;
        this.userRepository = userRepository;
    }

    @Override
    public List<UserBean> listAll() {
        List<UserBean> products = new ArrayList<>();
        userRepositoryCrud.findAll().forEach(products::add); //fun with Java 8
        return products;
    }

    public UserBean getByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    @Override
    public UserBean saveOrUpdate(UserBean userBean) {
        userRepositoryCrud.save(userBean);
        return userBean;
    }

    @Override
    public void delete(String id) {
        userRepositoryCrud.delete(id);
    }
}
