import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import {Response, Http} from "@angular/http";
import 'rxjs/add/operator/toPromise';
import { User } from "./user";

@Injectable()
export class UserService {

  private url="http://localhost:8080";

  constructor(private http: Http) { }

  saveUser(data:User):any{
    console.log("se guardara:" + data);
    return this.http.post(this.url+'/users/save', data).toPromise().then(
      ((res:Response) => res.json() as User))
      .catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }

  getUser(data:User):any{
    return this.http.get(this.url+'/users/getByEmail?email='+data.email).toPromise().then(
      (res:Response) => res.json() as User)
      .catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }

  getUsers():any{
    return this.http.get(this.url+'/users/all').toPromise().then(
      (res:Response) => res.json() as User[])
      .catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }

}
