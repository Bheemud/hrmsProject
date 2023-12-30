import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from 'src/app/service/Employee';
import { DataserviceService } from 'src/app/service/dataservice.service';
 
@Component({
  selector: '',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {
 
 
  employee1:any;
  emp:Employee | undefined;
  employee: Employee[] = [];
  searchTerm: any;
  filteredEmployee: Employee[]=[];
 
  constructor(private dataService: DataserviceService,private router:Router) {
  this.employee=[]
  this.filteredEmployee=this.employee
  }
 
  logout():void{
    sessionStorage.clear();
    this.router.navigate([''])
 
  }
  ngOnInit(): void {
    this.loadEmployee();
  }
 
  loadEmployee(): void {
    this.dataService.getAllEmployee().subscribe(employee => {
      this.employee= this.filteredEmployee=employee;
 
      
    });
  }
 
  applyFilter(): void {
   
        this.filteredEmployee = this.employee.filter(e=>e.firstName.indexOf(this.searchTerm)>=0);
    }
 
 
  editEmployee(emp: Employee): void {
    console.log('Edit:', emp);
  }
 
  showEmployees = false;
  fetchEmployees(): void {
    this.dataService.getAllEmployee()
      .subscribe((data: Employee[]) => {
        this.employee = data;
        this.showEmployees = true;
        
      });
  }
 
  deleteEmployee(emp: Employee): void {
    if (confirm('Are you sure you want to delete this employee?')) {
      this.dataService.deleteEmployeeByEmpNo(emp.empNo).subscribe(() => {
        this.loadEmployee();
      }, error => {
        console.error('Error deleting employee:', error);
      });
    }
  }
 
  ngOnChanges(): void {
    this.applyFilter();
  }
 
  getEmployeeById(): void {
 
    if (this.searchTerm) {
      this.dataService.getEmployeeById(this.searchTerm)
        .subscribe(
          (data: any) => {
            this.employee1 = data; 
            console.log(data)
          },
          (error: any) => {
            console.error('Error:', error); 
          }
        );
    } else {
      console.error('Invalid Employee ID');
    }
  }
 
  search:any;
  employee2:any;
  getEmployeeByfirstName(name:string):void{
    if(name){
      this.dataService.getEmployeeByfirstName(name).subscribe((data1:any)=>{
        this.employee2=(data1)
        console.log(data1)
    },
    (error:any)=>{
      console.error('Error :',error);
    }
      );
    }
    else{
        console.error('Invalid FirstName')
    }
  }
  womencount:any;
 womenCount():void{
  this.dataService.getWomenCount().subscribe(count => {
    this.womencount = count;
    console.log(count);
    console.log('sdfghjkl');
  });
}
 
  search1:any;
  employeesByLastName: any;
  getDataByLastName(): void {
    this.dataService.getDataByLastName(this.search1)
      .subscribe(data => {
        this.employeesByLastName = data;
        console.log(data)
      });
  }
 
  // empno3: any;
  // newFirstName: any;
  // updateFirstName(): void {
  //   if (this.emp && this.newFirstName) {
  //     this.dataService.updateFirstName(this.empno3, this.newFirstName)
  //       .subscribe((updatedEmployee: Employee) => {
  //         console.log('Employee updated:', updatedEmployee);
  //       }, error => {
  //         console.error('Error updating employee:', error);
  //       });
  //   } else {
  //     console.error('Employee number and new first name are required.');
  //   }
  // }
 
  //  empno: any;
  //  newLastName: any;
  // updateLastName(): void {
  //   if (this.empno && this.newLastName) {
  //     this.dataService.updateLastName(this.empno, this.newLastName)
  //       .subscribe((updatedEmployee: Employee) => {
  //         console.log('Employee updated:', updatedEmployee);
  //       }, error => {
  //         console.error('Error updating employee:', error);
  //       });
  //   } else {
  //     console.error('Employee number and new first name are required.');
  //   }
  // }
 
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
 
  empNoToDelete:any;
  deleteEmployeeById(): void {
    if (this.empNoToDelete) {
      this.dataService.deleteEmployee(this.empNoToDelete)
        .subscribe(
          (deletedEmployee: any) => {
            console.log('Employee deleted:', deletedEmployee);
          },
          (error: any) => {
            console.error('Error deleting employee:', error);
          }
        );
    } else {
      console.error('Employee number is required for deletion.');
    }
  }
 
  roles:any;
  getRoles(): void {
      this.dataService.getAllRoles()
      .subscribe(roles => {
        this.roles= roles;
        console.log(roles);
      });
   
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
        },
        (error) => {
          console.error('Error adding employee:', error);
        }
      );
  }
  empno2: any;
  newBirthDate: any;
 
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
 
  managers: any[] = [];
  getManagers(): void {
    this.dataService.getAllManagers()
      .subscribe((data: any) => {
        console.log(data);
        this.managers = data; 
      });
  }
  ages:any;
  fetchMidAgeEmployees() {
    this.dataService.getAllMidAgeEmployes().subscribe(
      (data) => {
        console.log('Mid Age Employees:', data);
      },
      (error) => {
        console.error('Error fetching mid-age employees:', error);
      }
    );
  }

  showEmp(){
    this.router.navigate(['/allemployees'])
  }
  redirectToAllemployees() {
    this.router.navigate(['/allemployees']);
  }
}