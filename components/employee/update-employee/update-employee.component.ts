import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from 'src/app/service/Employee';
import { DataserviceService } from 'src/app/service/dataservice.service';

@Component({
  selector: 'app-update-employee',
  templateUrl: './update-employee.component.html',
  styleUrls: ['./update-employee.component.css']
})
export class UpdateEmployeeComponent {
  emp: any;
  empno2: any;
  newBirthDate: any;


  constructor(private dataService: DataserviceService,private router:Router) {
  }



  empno3: any;
  newFirstName: any;
  updateFirstName(): void {
    if (this.empno3 && this.newFirstName) {
      console.log(this.empno3,this.newFirstName)
      this.dataService.updateByFirstName(this.empno3, this.newFirstName)

        .subscribe((updatedEmployee: Employee) => {
          console.log('Employee updated:', updatedEmployee);
        }, error => {
          console.error('Error updating employee:', error);
        });
    } else {
      console.error('Employee number and new first name are required.');
    }
  }
 
   empno: any;
   newLastName: any;
  updateLastName(): void {
    if (this.empno && this.newLastName) {
      console.log(this.empno,this.newLastName)
      this.dataService.updateByLastName(this.empno, this.newLastName)

        .subscribe((updatedEmployee: Employee) => {
        }, error => {
          console.error('Error updating employee:', error);
          if (error instanceof HttpErrorResponse) {
            console.error('Status:', error.status);
            
          }
        });
    } else {
      console.error('Employee number and new first name are required.');
    }
  }
 
  empno1: any;
  newHireDate: any;
  updateHireDate(): void {
    if (this.empno1 && this.newHireDate) {
      this.dataService.updateHireDate(this.empNo, this.newHireDate)
        .subscribe((updatedEmployee: Employee) => {
          console.log('Employee updated:', updatedEmployee);
        }, error => {
          console.error('Error updating employee:', error);
        });
    } else {
      console.error('Employee number and new hire date are required.');
    }
  }
  empNo(empNo: any, newHireDate: any) {
    throw new Error('Method not implemented.');
  }

  updateBirthDate(): void {
    if (this.empno2 && this.newBirthDate) {
      this.dataService.updateBirthDate(this.empno2, this.newBirthDate)
        .subscribe(
          (updatedEmployee: any) => {
            console.log('Employee updated:', updatedEmployee);
          },
          error => {
            console.error('Error updating employee:', error);
          }
        );
    } else {
      console.error('Employee number and new birth date are required.');
    }
  }

}
