package com.redbee.repositoriesImp;

import com.redbee.bean.UserBean;
import com.redbee.repositories.UserRepository;
import com.redbee.rest.UserRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by goroma on 18/08/2017.
 */
@Repository
public class UserRepositoryImp implements UserRepository {

    public static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    private MongoOperations mongoOperation;

    @Autowired
    public UserRepositoryImp(MongoOperations mongoOperation) {
        this.mongoOperation = mongoOperation;
    }

    @Override
    public UserBean findOneByEmail(String email) {

        logger.info("buscando el usuario con mail: " + email);

        Query query2 = new Query();
        query2.addCriteria(Criteria.where("email").is(email));

        return mongoOperation.findOne(query2, UserBean.class);
    }
}
