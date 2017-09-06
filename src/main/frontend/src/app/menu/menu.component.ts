import {Component, OnInit} from '@angular/core';
import {LoginUserService} from "../login-user.service";
import {Feed} from "../feed";
import { FacebookService, InitParams, LoginResponse, LoginOptions, UIResponse, UIParams, FBVideoComponent } from 'ngx-facebook';
import {DashboardComponent} from "../dashboard/dashboard.component";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css'],
})
export class MenuComponent implements OnInit {

  feeds:Feed[];

  constructor(private loginUser: LoginUserService) {



  }

  ngOnInit() {

  }

}
