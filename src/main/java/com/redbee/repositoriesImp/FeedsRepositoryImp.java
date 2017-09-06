package com.redbee.repositoriesImp;

import com.redbee.bean.FeedBean;
import com.redbee.repositories.FeedsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.redbee.repositoriesImp.UserRepositoryImp.logger;

/**
 * Created by goroma on 18/08/2017.
 */
@Repository
public class FeedsRepositoryImp implements FeedsRepository {

    private MongoOperations mongoOperation;

    @Autowired
    public FeedsRepositoryImp(MongoOperations mongoOperation) {
        this.mongoOperation = mongoOperation;
    }

    @Override
    public FeedBean findOneById(String id) {

        logger.info("buscando el feed con id: " + id);

        Query query2 = new Query();
        query2.addCriteria(Criteria.where("id").is(id));

        return mongoOperation.findOne(query2, FeedBean.class);
    }

    @Override
    public List<FeedBean> findByEmail(String email) {
        logger.info("buscando los feeds con email: " + email);

        Query query2 = new Query();
        query2.addCriteria(Criteria.where("email").is(email));

        return mongoOperation.find(query2, FeedBean.class);
    }

    @Override
    public void setList(List<FeedBean> list) {
        mongoOperation.insertAll(list);
    }
}
