import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class JwtinterceptorInterceptor implements HttpInterceptor {

  constructor() {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    console.log("intercepting......")
    let token:string=`${sessionStorage.getItem('token')}`;
    console.log("token: ",token)
    if(sessionStorage.getItem('username')&&sessionStorage.getItem('token')){
      console.log("setting headers");
      request = request.clone({headers:request.headers.set('Authorization',token)})
    }
    return next.handle(request);
    
  }
}
