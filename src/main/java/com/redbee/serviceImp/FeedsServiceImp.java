package com.redbee.serviceImp;

import com.redbee.bean.FeedBean;
import com.redbee.repositories.FeedsRepository;
import com.redbee.repositories.FeedsRepositoryCrud;
import com.redbee.service.FeedsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by goroma on 17/08/2017.
 */
@Service
public class FeedsServiceImp implements FeedsService {

    private FeedsRepositoryCrud feedsRepositoryCrud;
    private FeedsRepository feedsRepository;

    @Autowired
    public FeedsServiceImp(FeedsRepositoryCrud feedsRepositoryCrud, FeedsRepository feedsRepository) {
        this.feedsRepositoryCrud = feedsRepositoryCrud;
        this.feedsRepository = feedsRepository;
    }

    @Override
    public List<FeedBean> listAll() {
        List<FeedBean> products = new ArrayList<>();
        feedsRepositoryCrud.findAll().forEach(products::add); //fun with Java 8
        return products;
    }

    public FeedBean getById(String id) {
        return feedsRepository.findOneById(id);
    }

    @Override
    public List<FeedBean> getByEmail(String email) {
        return feedsRepository.findByEmail(email);
    }

    @Override
    public FeedBean saveOrUpdate(FeedBean userBean) {
        feedsRepositoryCrud.save(userBean);
        return userBean;
    }

    @Override
    public List<FeedBean> saveOrUpdate(List<FeedBean> userBean) {
        feedsRepositoryCrud.save(userBean);
        return listAll();
    }

    @Override
    public void delete(String id) {
        feedsRepositoryCrud.delete(id);
    }
}
