import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from '../service/authenticationservice.service';

@Component({
  selector: 'app-login-registerform',
  templateUrl: './login-registerform.component.html',
  styleUrls: ['./login-registerform.component.css']
})
export class LoginRegisterformComponent implements OnInit{
  
  username = '';
  password = '';
  invalidLogin = false;
  invalidmsg="";
  constructor(private authservice: AuthenticationService, private router: Router, private route: ActivatedRoute) {

  }

  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }
  checkUser() {
  if (!this.username || !this.password) {
    alert('Username and password are required.');
    return;
  }

  var user = { "username": this.username, "password": this.password };
  
  this.authservice.verifyuser(user).subscribe(
    data => {
      sessionStorage.setItem("token", "Bearer " + data.token);
      sessionStorage.setItem("username", this.username);
      if(data.role==='[admin]')
        this.router.navigate(['Home']);
      if(data.role==='[employee]')
      this.router.navigate(['employee']);
    },
    error => {
      this.invalidLogin = true;
      this.invalidmsg = 'Invalid Credentials';
      alert('Invalid credentials. Please check your username and password.');
    }
  );
  }

}
