import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { LoginUserService } from "../login-user.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(public loginUser: LoginUserService) {

  }

  ngOnInit() {
  }

  loginFacebook() {
    this.loginUser.loginFacebook();
  }

  logout() {
    this.loginUser.logout();
  }
}
