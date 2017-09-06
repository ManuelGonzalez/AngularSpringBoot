import { Injectable } from '@angular/core';
import * as firebase from 'firebase/app';
import { FacebookService, InitParams, LoginResponse, LoginOptions, UIResponse, UIParams, FBVideoComponent } from 'ngx-facebook';
import FacebookAuthProvider = firebase.auth.FacebookAuthProvider;
import {Observable} from "rxjs";

@Injectable()
export class FacebookApiService {

  constructor(public fb: FacebookService) {

    let initParams = {
      facebook:{
        appId: '1525641980791313',
        secret: 'd83e337c212b6d98b7ac367333086138',
        xfbml: true,
        version: 'v2.10'
      }
    };

    fb.init(initParams.facebook);

  }

  getFacebookFeeds() : any {
    return this.fb.api("me/feed?access_token=" + localStorage.getItem("accessToken") + "&limit=25")
      .then(res => res.data)
      .catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }

}
