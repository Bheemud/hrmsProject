import { Component } from '@angular/core';
import { AuthenticationService } from 'src/app/service/authenticationservice.service';

@Component({
  selector: 'app-registercomponent',
  templateUrl: './registercomponent.component.html',
  styleUrls: ['./registercomponent.component.css']
})
export class RegistercomponentComponent {

  
  userData = {
    username: '',
    password: '',
    role: ''
  };
 
  constructor(private authService: AuthenticationService) { }
 
  registerUser() {
    this.authService.registerUser(this.userData)
      .subscribe(
        response => {
          console.log('Registration successful!', response);
        },
        error => {
          console.error('Registration failed:', error);
        }
      );
  }
 
}