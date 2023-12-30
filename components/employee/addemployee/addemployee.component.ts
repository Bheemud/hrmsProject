import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { DataserviceService } from 'src/app/service/dataservice.service';

@Component({
  selector: 'app-addemployee',
  templateUrl: './addemployee.component.html',
  styleUrls: ['./addemployee.component.css']
})
export class AddemployeeComponent {


  constructor(private dataService: DataserviceService,private router:Router) {

  }

  empNo: any;
  birthDate: any;
  hireDate: any;
  firstName: any;
  lastName: any;
  email: any;
  gender: any;
  password: any;
  role: any;
 
  // addEmployee(): void {
  //   const newEmployee = {
  //     empNo: this.empNo,
  //     birthDate: this.birthDate,
  //     hireDate: this.hireDate,
  //     firstName: this.firstName,
  //     lastName: this.lastName,
  //     email: this.email,
  //     gender: this.gender,
  //     password: this.password,
  //     role: this.role
  //   };
 
  //   this.dataService.addEmployee(newEmployee)
  //     .subscribe(
  //       (response) => {
  //         console.log('Employee added successfully:', response);
  //       },
  //       (error) => {
  //         console.error('Error adding employee:', error);
  //       }
  //     );
  // }

addEmployee(): void {
    const newEmployee = {
      empNo: this.empNo,
      birthDate: this.birthDate,
      hireDate: this.hireDate,
      firstName: this.firstName,
      lastName: this.lastName,
      email: this.email,
      gender: this.gender,
      password: this.password,
      role: this.role
    };
 
    this.dataService.addEmployee(newEmployee)
      .subscribe(
        (response) => {
          console.log('Employee added successfully:', response);
          // You can perform further actions upon successful addition
        },
        (error) => {
          console.error('Error adding employee:', error);
        }
      );
  }
  empno2: any;
  newBirthDate: any;

}
