package com.redbee.repositories;

import com.redbee.bean.UserBean;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by goroma on 17/08/2017.
 */
public interface UserRepositoryCrud extends CrudRepository<UserBean, String> {
}
