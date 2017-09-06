package com.redbee.repositories;

import com.redbee.bean.FeedBean;

import java.util.List;

/**
 * Created by goroma on 18/08/2017.
 */
public interface FeedsRepository {

    FeedBean findOneById(String id);

    List<FeedBean> findByEmail(String email);

    void setList(List<FeedBean> list);

}
