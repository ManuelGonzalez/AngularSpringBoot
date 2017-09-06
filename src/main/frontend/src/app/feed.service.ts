import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import {Response, Http} from "@angular/http";
import 'rxjs/add/operator/toPromise';
import { Feed } from "./feed";

@Injectable()
export class FeedService {

  private url="http://localhost:8080";

  constructor(private http: Http) { }

  saveFeed(data:Feed):any{
    console.log("se guardara:" + data);
    return this.http.post(this.url+'/feeds/save', data).toPromise().then(
      ((res:Response) => res.json() as Feed))
      .catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }

  saveListFeed(data:Feed[]):any{
    console.log("se guardara:" + data);
    return this.http.post(this.url+'/feeds/saveList', data).toPromise().then(
      ((res:Response) => res.json() as Feed[]))
      .catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }

  getFeedById(data:Feed):any{
    return this.http.get(this.url+'/feeds/getById?id='+data.id).toPromise().then(
      (res:Response) => res.json() as Feed)
      .catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }

  getFeedsByEmail(email:string):any{
    return this.http.get(this.url+'/feeds/getByEmail?email='+email).toPromise().then(
      (res:Response) => res.json() as Feed[])
      .catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }

  getFeeds(){
    this.http.get(this.url+'/feeds/all').toPromise().then(
      (res:Response) => res.json() as Feed[])
      .catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }
}
