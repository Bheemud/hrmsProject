import { Component } from '@angular/core';
import { DeptEmployeeService } from 'src/app/service/dept-employee.service';
 
import { ChangeDetectorRef } from '@angular/core';
import { DeptEmployee } from '../DeptEmployee';
import { Router } from '@angular/router';
 
@Component({
  selector: 'app-dept-employee',
  templateUrl: './dept-employee.component.html',
  styleUrls: ['./dept-employee.component.css'],
})
export class DeptEmployeeComponent {
  deptEmployees: DeptEmployee[] = [];
  searchResults: DeptEmployee[] = [];
  empNo: any;
  deptNo: any;
  fromDate: any;
  toDate: any;
  firstName: any;
  lastName: any;
  deptName: any;
  deleteSuccessMessage: string | null = null;
  deleteErrorMessage: string |null = null;
  updateSuccess: boolean = false;
  updateError: boolean = false;
  deleteEmpNo!: number;
  deleteDeptNo!: string;
  result: any;
  deleteSuccess: boolean = false;
  deletefail: boolean = false;
  successMessage!: string;
  errorMessage!: string;
  searchdata: any;
  updatedEmployee: any;
  deptEmp: DeptEmployee[] = [];
  showDeptEmployees: boolean = false;
  showSearchResults : boolean=false;
  isSearchResultsVisible!: boolean;
  isDataVisible: boolean = false;
  displaySearchData: boolean = false;
  dataVisible: boolean=false;
  isUpdatedDataVisible: boolean = false;
  filteredDeptEmployees: DeptEmployee[] = [];
  searchValue: string | Date | undefined;
  searchBy: string = 'deptNo';
  noDataFoundMessage: string = '';
 
 
 
  constructor(private deptEmpService: DeptEmployeeService, private cdr: ChangeDetectorRef,private router:Router) {}
 
 
 
 
  fetchData(): void {
    this.deptEmpService.fetchAllDeptEmp().subscribe((data) => {
      this.deptEmployees = data;
      if (this.deptNo) {
        this.filteredDeptEmployees = this.deptEmployees.filter(
          (employee) => employee.id.deptNo === this.deptNo
        );
      } else if (this.deptName) {
        this.filteredDeptEmployees = this.deptEmployees.filter(
          (employee) =>
            employee.department &&
            employee.department.deptName
              .toLowerCase()
              .includes(this.deptName.toLowerCase())
        );
      } else if (this.fromDate) {
        this.filteredDeptEmployees = this.deptEmployees.filter(
          (employee) =>
            employee.fromDate &&
            new Date(this.fromDate).toDateString() ===
              new Date(employee.fromDate).toDateString()
        );
      } else if (this.toDate) {
        this.filteredDeptEmployees = this.deptEmployees.filter(
          (employee) =>
            employee.toDate &&
            new Date(this.toDate).toDateString() ===
              new Date(employee.toDate).toDateString()
        );
      }  else {
        this.filteredDeptEmployees = this.deptEmployees;
      }
      this.showDeptEmployees = true;
      if (this.filteredDeptEmployees.length === 0) {
        this.noDataFoundMessage = 'No data found.';
      } else {
        this.noDataFoundMessage = ''; // Reset the message if data is found
      }
    });
  }
 
  hideDeptEmployees(): void {
    this.showDeptEmployees = false;
  }
 
  search() {
    this.deptEmpService.searchByEmpnoAndDeptno(this.empNo, this.deptNo).subscribe(
      (data) => {
        this.searchResults = data;
        this.resetUpdateFlags();
        this.isSearchResultsVisible = true;
      },
      (error) => {
        alert("Data not found. Enter valid data");
      }
    );
  }
 
  hideSearchResults() {
    this.isSearchResultsVisible = false;
  }
 
  onSubmit(form: any) {
    if (form.valid) {
      this.deptEmpService.searchByEmpnoAndFromdate(this.empNo, this.fromDate).subscribe(
        (data) => {
          this.searchdata = data || [];
          this.isSearchResultsVisible = true;
        },
        (error) => {
          alert("Data not found. Enter valid data");
        }
      );
    }
  }
 
  closeSearchResults() {
    this.isSearchResultsVisible = false;
  }
 
  searchByDeptnoAndFromdate() {
    this.deptEmpService.searchByDeptnoAndFromdate(this.deptNo, this.fromDate).subscribe(
      (data: DeptEmployee[]) => {
        this.deptEmp = data;
        this.displaySearchData = true;
      },
      (error: any) => {
        alert("Data not found. Enter valid data");
      }
    );
  }
 
  hideSearchData() {
    this.displaySearchData = false;
  }
 
  updateDeptEmployee(): void {
    this.deptEmpService.updateDeptEmployee(this.empNo, this.deptNo, this.fromDate, this.toDate).subscribe(
      (updatedDeptEmployee) => {
        console.log('Updated DeptEmployee:', updatedDeptEmployee);
        this.updateSuccess = true;
        this.updateError = false;
        this.updatedEmployee = updatedDeptEmployee;
        this.isUpdatedDataVisible = true;
        this.dataVisible = false;
      },
      (error) => {
        console.error('Error updating DeptEmployee:', error);
        this.updateSuccess = false;
        this.updateError = true;
      }
    );
  }
 
  hideData(): void {
    this.dataVisible = false;
    this.isUpdatedDataVisible = false;
  }
 
 
 
  deleteDeptEmp(empNo: number, deptNo: string): void {
    this.deptEmpService.deleteDeptEmp(empNo, deptNo).subscribe(
      () => {
        console.log('DeptEmp deleted successfully');
        this.deleteSuccess = true;
        this.deletefail = false;
      },
      (error) => {
        console.error('Error deleting DeptEmp:', error);
        this.deleteSuccess = false;
        this.deletefail = true;
      }
    );
  }
 
  deleteDeptEmpl(empNo: number, fromDate: string): void {
    this.deptEmpService.deleteDeptEmpByEmpNoAndFromDate(empNo, fromDate).subscribe(
      (response) => {
        console.log('DeptEmp deleted successfully');
        this.successMessage = response;
        this.errorMessage = '';
      },
      (error) => {
        console.error('Error deleting DeptEmp', error);
        this.errorMessage = 'Error deleting DeptEmp. Please try again.';
        this.successMessage = '';
      }
    );
  }
 
 
  deleteDeptEmployee(): void {
    if (this.deptNo && this.fromDate) {
      this.deptEmpService.deleteDeptEmpByDeptNoAndFromDate(this.deptNo, this.fromDate).subscribe(
        () => {
          console.log('DeptEmp deleted successfully');
          this.deleteSuccessMessage = 'DeptEmp deleted successfully';
        },
        (error) => {
          console.error(error);
          this.deleteErrorMessage = 'Error in deleting';
        }
      );
    } else {
      console.error('DeptNo and FromDate are required.');
    }
  }
 
 
  deleteDepartmentEmployee(): void {
    this.deptEmpService.deleteDeptEmpByEmpNoDeptNoFromDate(this.empNo, this.deptNo, this.fromDate).subscribe(
      () => {
        console.log('Department Employee deleted successfully');
        console.log(`The employee with ${this.deptNo} has been deleted successfully`);
        this.deleteSuccessMessage='deleted successfully'
      },
      (error) => {
        console.error('Error deleting Department Employee', error);
        this.deleteErrorMessage='Error in deleting'
      }
    );
  }
 
  private resetUpdateFlags(): void {
    this.updateSuccess = false;
    this.updateError = false;
  }
 
  logout():void{
    sessionStorage.clear();
    this.router.navigate([''])
 
  }
}
 
