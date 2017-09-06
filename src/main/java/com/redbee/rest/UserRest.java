package com.redbee.rest;

import com.redbee.bean.UserBean;
import com.redbee.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by goroma on 17/08/2017.
 */
@RestController
@RequestMapping("/users")
public class UserRest {

    private UserService userService;

    public static final Logger logger = LoggerFactory.getLogger(UserRest.class);

    @Autowired
    public UserRest(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserBean>> getUsers() {
        return ResponseEntity.ok(userService.listAll());
    }

    @CrossOrigin
    @RequestMapping(value = "/getByEmail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserBean> getUserByManil(@RequestParam String email) {
        logger.info("Fetching User with id {}", email);
        UserBean user = userService.getByEmail(email);
        if (user == null) {
            logger.error("User with id {} not found.", email);
            return new ResponseEntity("User with id "+email+" not found.", HttpStatus.INTERNAL_SERVER_ERROR);
        }else {
            return ResponseEntity.ok(user);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserBean> saveOrUpdateUsers(@RequestBody UserBean user) {
        return ResponseEntity.ok(userService.saveOrUpdate(user));
    }

    @CrossOrigin
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteUserById(@PathVariable(value="id") String id) {
        userService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
