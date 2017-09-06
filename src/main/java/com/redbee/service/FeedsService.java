package com.redbee.service;

import com.redbee.bean.FeedBean;

import java.util.List;

/**
 * Created by goroma on 24/08/2017.
 */
public interface FeedsService {

    List<FeedBean> listAll();

    FeedBean getById(String id);

    List<FeedBean> getByEmail(String email);

    FeedBean saveOrUpdate(FeedBean feedBean);

    List<FeedBean> saveOrUpdate(List<FeedBean> userBean);

    void delete(String id);
}
