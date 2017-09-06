import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { AngularFireAuth } from 'angularfire2/auth';
import * as firebase from 'firebase/app';
import {User} from "./user";
import {UserService} from "./user.service";
import FacebookAuthProvider = firebase.auth.FacebookAuthProvider;
import {Router} from "@angular/router";
import {Feed} from "./feed";
import {FeedService} from "./feed.service";
import {FacebookApiService} from "./facebook.service";


@Injectable()
export class LoginUserService {

  userObs: Observable<firebase.User>;
  User: User;
  accessToken: string;

    constructor(
      public afAuth: AngularFireAuth,
      private userService:UserService,
      private router: Router,
      private feedService:FeedService,
      private facebookApiService:FacebookApiService) {

      console.log(afAuth.authState);
      this.userObs = afAuth.authState;

    }

    ngOnInit(){

    }

    loginFacebook() {
      this.afAuth.auth.signInWithPopup(new firebase.auth.FacebookAuthProvider().addScope('email,user_hometown,user_religion_politics,user_likes,user_status,user_about_me,user_location,user_tagged_places,user_birthday,user_photos,user_videos,user_education_history,user_posts,user_website,user_friends,user_relationship_details,user_work_history,user_games_activity,user_relationships,user_events,pages_show_list,user_actions.books,user_actions.music,user_actions.video,user_actions.fitness,user_actions.news')).then(
        res => { // Success
          localStorage.setItem('accessToken', res.credential.accessToken);
          this.setProfile();
        },
        msg => { // Error
          console.log(msg);
        }
      );
    }

    loginWithEmail(event, email, password){
      event.preventDefault();
      this.afAuth.auth.signInWithEmailAndPassword(email, password).then((res) => {
        this.setProfile();
      }).catch((error: any) => {
        if (error) {
          console.log(error);
        }
      });
    }

    register(event, name, email, password) {
      event.preventDefault();
      this.afAuth.auth.createUserWithEmailAndPassword(email, password).then((res) => {
        this.setProfile();
      }).catch((error: any) => {
        if (error) {
          console.log(error);
        }
      });
    }

    logout() {
      this.afAuth.auth.signOut().then(
        res => { // Success
          this.destroyProfile();
          localStorage.removeItem("accessToken");
        },
        msg => { // Error
          console.log(msg);
        }
      );
    }

    setProfile(){
      this.userObs.subscribe
      (user=> {
        if (user) {
          console.log(user.toJSON());
          this.User = {
            name : user.displayName,
            email : user.email==null?user.providerData[0].email:user.email,
            phone  : user.phoneNumber,
            photoURL  : user.photoURL
          };
          this.userService.getUser(this.User).then((res)=>{
            this.getFeeds(res.email);
            this.router.navigate(['/dashboard']);
          }).catch((e)=>{
            console.log(e);
            this.userService.saveUser(this.User).then((res)=>{
              this.getFeeds(res.email);
              this.router.navigate(['/dashboard']);
            }).catch((e)=> console.log(e));
          });
        }
      });
    }

    getProfile():User{
      this.userObs.subscribe
      (user=> {
        if (user) {
          console.log(user.toJSON());
          this.User = {
            name : user.displayName,
            email : user.email==null?user.providerData[0].email:user.email,
            phone  : user.phoneNumber,
            photoURL  : user.photoURL
          };
        }
      });
      return this.User;
    }

    destroyProfile(){
      this.User=null;
    }

    getFeeds(userEmail:string){
      this.feedService.getFeedsByEmail(userEmail).then((res)=>{
        if(res.length==0){
          this.getFacebookFeeds(userEmail);
        }
      }).catch((e)=>{
        console.log(e);
        this.getFacebookFeeds(userEmail);
      });
    }

    getFacebookFeeds(userEmail:string){
      this.facebookApiService.getFacebookFeeds().then((res)=>{
        res.forEach((feedRest:Feed) => {
          feedRest.email=userEmail;
        });
        console.log(res);
        this.feedService.saveListFeed(res);
      }).catch(e=>console.log(e));
    }

    private handleError(error) {
      console.error('Error processing action', error);
    }

}
