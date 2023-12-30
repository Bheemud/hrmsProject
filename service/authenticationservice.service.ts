import { Injectable } from '@angular/core';
import { HttpClient, JsonpInterceptor } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  
  baseUrl:string='http://localhost:9999/';
  constructor(private httpClient:HttpClient) { 


}

verifyuser(user:any){
  return this.httpClient.post<any>(this.baseUrl+"login",user);
}

registerUser(user: any) {
  return this.httpClient.post<any>(this.baseUrl + 'register', user);
}
 
}
