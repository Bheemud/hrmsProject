import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { DataserviceService } from 'src/app/service/dataservice.service';

@Component({
  selector: 'app-deleteemployee',
  templateUrl: './deleteemployee.component.html',
  styleUrls: ['./deleteemployee.component.css']
})
export class DeleteemployeeComponent {
  empNoToDelete: any;


    constructor(private dataService: DataserviceService,private router:Router) {
    }



  deleteEmployeeById(): void {
    if (this.empNoToDelete) {
      this.dataService.deleteEmployee(this.empNoToDelete)
        .subscribe(
          (deletedEmployee: any) => {
            console.log('Employee deleted:', deletedEmployee);
            // Further actions upon successful deletion, if needed
          },
          (error: any) => {
            console.error('Error deleting employee:', error);
            // Handle specific error details here
          }
        );
    } else {
      console.error('Employee number is required for deletion.');
    }
  }

}
