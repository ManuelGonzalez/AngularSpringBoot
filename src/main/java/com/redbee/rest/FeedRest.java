package com.redbee.rest;

import com.redbee.bean.FeedBean;
import com.redbee.service.FeedsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by goroma on 24/08/2017.
 */
@RestController
@RequestMapping("/feeds")
public class FeedRest {

    private FeedsService feedsService;

    public static final Logger logger = LoggerFactory.getLogger(FeedRest.class);

    @Autowired
    public FeedRest(FeedsService feedsService) {
        this.feedsService = feedsService;
    }

    @CrossOrigin
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FeedBean>> getfeeds() {
        return ResponseEntity.ok(feedsService.listAll());
    }

    @CrossOrigin
    @RequestMapping(value = "/getById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FeedBean> getfeedById(@RequestParam String id) {
        logger.info("Fetching feed with id {}", id);
        FeedBean feed = feedsService.getById(id);
        if (feed == null) {
            logger.error("Feed with id {} not found.", id);
            return new ResponseEntity("feed with id "+id+" not found.", HttpStatus.INTERNAL_SERVER_ERROR);
        }else {
            return ResponseEntity.ok(feed);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/getByEmail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FeedBean>> getfeedByManil(@RequestParam String email) {
        logger.info("Fetching feed with email {}", email);
        List<FeedBean> feeds = feedsService.getByEmail(email);
        if (feeds == null) {
            logger.error("Feeds with email {} not found.", email);
            return new ResponseEntity("feed with email "+email+" not found.", HttpStatus.INTERNAL_SERVER_ERROR);
        }else {
            return ResponseEntity.ok(feeds);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FeedBean> saveOrUpdatefeeds(@RequestBody FeedBean feed) {
        return ResponseEntity.ok(feedsService.saveOrUpdate(feed));
    }

    @CrossOrigin
    @RequestMapping(value = "/saveList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FeedBean>> saveOrUpdatefeeds(@RequestBody List<FeedBean> feed) {
        return ResponseEntity.ok(feedsService.saveOrUpdate(feed));
    }

    @CrossOrigin
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deletefeedById(@PathVariable(value="id") String id) {
        feedsService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
