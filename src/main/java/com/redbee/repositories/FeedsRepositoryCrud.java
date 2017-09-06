package com.redbee.repositories;

import com.redbee.bean.FeedBean;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by goroma on 17/08/2017.
 */
public interface FeedsRepositoryCrud extends CrudRepository<FeedBean, String> {
}
