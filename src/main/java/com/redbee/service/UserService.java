package com.redbee.service;

import com.redbee.bean.UserBean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by goroma on 17/08/2017.
 */
@Service
public interface UserService {

    List<UserBean> listAll();

    UserBean getByEmail(String email);

    UserBean saveOrUpdate(UserBean userBean);

    void delete(String id);

}
