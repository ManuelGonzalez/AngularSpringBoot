package com.redbee.repositories;

import com.redbee.bean.UserBean;
import org.springframework.stereotype.Repository;

/**
 * Created by goroma on 18/08/2017.
 */
public interface UserRepository {

    UserBean findOneByEmail(String id);

}
