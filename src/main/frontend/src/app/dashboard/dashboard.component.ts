import { Component, OnInit } from '@angular/core';
import {LoginUserService} from "../login-user.service";
import {FacebookService} from "ngx-facebook";
import {Feed} from "../feed";
import {FeedService} from "../feed.service";
import {User} from "../user";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  user:User;
  feeds:Feed[];

  constructor(private loginUser: LoginUserService, private feedService:FeedService) {
    console.log(this.getProfile());
  }

  ngOnInit() {
    //this.feeds=this.feedService.getFeedsByEmail(this.loginUser.getProfile().email);

  }

  getProfile(){
    this.loginUser.userObs.subscribe
    (user=> {
      if (user) {
        return user.email==null?user.providerData[0].email:user.email;
      }
    });
  }

}
