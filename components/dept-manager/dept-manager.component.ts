import { Component } from '@angular/core';
import { DeptManagerService } from 'src/app/service/dept-manager.service';
import { NgForm } from '@angular/forms';
import { DeptManager } from 'src/app/service/dept-manager';
import { Router } from '@angular/router';
@Component({
  selector: 'app-dept-manager',
  templateUrl: './dept-manager.component.html',
  styleUrls: ['./dept-manager.component.css']
})
export class DeptManagerComponent {
 
  deptManagers: any[] = [];
  empNo!: number;
  deptNo!: string;
  fromDate: string = '';
  deptManager!: DeptManager;
  deptmngr:any[]=[];
  fetchedData: boolean = false;
  errorFetchingManager: boolean = false;
  manager: any;
  deletionSuccessMessage: string = '';
  deletionErrorMessage: string = '';
 
 
  constructor(private deptManagerService: DeptManagerService,private router:Router) { }
 
  departmentManager: any;
 
 
  fetchData(): void {
    this.deptManagerService.getAllDeptManagers().subscribe(
      (data: DeptManager[]) => {
        this.departmentManager = data;
      },
      (error) => {
        console.error('Error fetching data:', error);
      }
    );
  }
 
  logout():void{
    sessionStorage.clear();
    this.router.navigate([''])
  }
  fetchDeptManager(): void {
    if (this.empNo !== 0 && this.deptNo !== '') {
      this.deptManagerService.getDeptManagerByEmpNoAndDeptNo(this.empNo, this.deptNo).subscribe(
        (data) => {
          this.manager = data;
          console.log('Fetched department manager:', this.manager);
        },
        (error) => {
          console.error('Error fetching department manager:', error);
        }
      );
    } else {
      console.error('Employee number and department number are required.');
    }
  }
//
 
  fetchDeptManagers(empNo: number, fromDate: string): void {
    this.deptManagerService.getDeptManagersByEmpNoAndFromDate(empNo, fromDate)
      .subscribe(
        (data: DeptManager[]) => {
          this.deptManagers = data;
          console.log('Fetched department managers:', this.deptManagers);
        },
        (error) => {
          console.error('Error fetching DeptManagers:', error);
        }
      );
  }
  deleteDeptManager(deleteForm: NgForm): void {
    if (deleteForm.valid) {
      this.deptManagerService.deleteDeptManagerByEmpNoAndDeptNo(this.empNo, this.deptNo).subscribe(
        () => {
          this.deletionSuccessMessage = 'DeptManager deleted successfully';
          this.deletionErrorMessage = '';
        },
        (error) => {
          this.deletionErrorMessage = 'Error deleting DeptManager: ' + error.message;
          this.deletionSuccessMessage = '';
        }
      );
    } else {
      console.error('Employee number and department number are required.');
    }
  }
//
 
  onSubmit(form: any): void {
    const empNo: number = form.value.empNo;
    const fromDate: string = form.value.fromDate;
 
    if (empNo && fromDate) {
      this.fetchDeptManagers(empNo, fromDate);
    } else {
      console.error('Employee number and from date are required.');
    }
  }
 
  fetchdeptManager(): void {
    if (this.deptNo.trim() !== '' && this.fromDate.trim() !== '') {
      this.deptManagerService.getDeptManagersByDeptNoAndFromDate(this.deptNo, this.fromDate)
        .subscribe(
          (data: DeptManager[]) => {
            this.deptManagers = data; // Store data in deptManagers variable
          },
          (error) => {
            console.error('Error fetching dept managers:', error);
          }
        );
    } else {
      console.error('Please provide both dept number and from date.');
    }
  }
 
}
 
 
 
 
 
