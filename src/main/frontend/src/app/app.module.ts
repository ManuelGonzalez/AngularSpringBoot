import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule  } from '@angular/http';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AngularFireModule } from 'angularfire2';
import { AngularFireDatabaseModule } from 'angularfire2/database';
import { AngularFireAuthModule } from 'angularfire2/auth';
import { FacebookModule } from 'ngx-facebook';

import { AuthGuard } from "./auth.guard";

import { LoginUserService } from "./login-user.service";
import { UserService } from "./user.service";
import { FeedService } from "./feed.service";
import { FacebookApiService } from "./facebook.service";

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { HeaderComponent } from './header/header.component';
import { MenuComponent } from './menu/menu.component';

const environment = {
  production: false,
  firebase: {
    apiKey: 'AIzaSyCKI0HvHDvqZh9xJ_wc-aIw7MH5sncXsDA',
    authDomain: 'redbee-fad8d.firebaseapp.com',
    databaseURL: 'https://redbee-fad8d.firebaseio.com',
    projectId: 'redbee-fad8d',
    storageBucket: 'redbee-fad8d.appspot.com',
    messagingSenderId: '717351514060'
  }
};

const appRoutes:Routes = [
  {
    path: '',
    component: LoginComponent
  },
  {
    path: 'dashboard',
    canActivate: [AuthGuard],
    component: DashboardComponent,
    children:[
      /*
      {
        path: ':name',
        component: UserComponent
      }
      */
    ]
  },
  {
    path: '**',
    component: NotFoundComponent
  }
]

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    NotFoundComponent,
    HeaderComponent,
    MenuComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(appRoutes),
    AngularFireModule.initializeApp(environment.firebase),
    AngularFireDatabaseModule,
    AngularFireAuthModule,
    FacebookModule.forRoot()
  ],
  providers: [
    AuthGuard,
    LoginUserService,
    UserService,
    FeedService,
    FacebookApiService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
