import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Departments } from 'src/app/service/Departments';
import { DepartmentsService } from 'src/app/service/department.service';
 
@Component({
  selector: 'app-departments',
  templateUrl: './departments.component.html',
  styleUrls: ['./departments.component.css']
})
export class DepartmentsComponent {
  departments: Departments[] = [];
  selectedDepartment: Departments | null = null;
  depno!: string;
  newname!: string;
  deptNoToFetch: string = '';
  deptName: string = '';
  updatedName: string = '';
 
  deptNo: any;
  department!: Departments;
 
  constructor(private deptService: DepartmentsService,private router:Router) {
   
  }
 
  logout():void{
    sessionStorage.clear();
    this.router.navigate([''])
 
  }
 
 
 
  getDepartmentByDeptNo(deptNo: string): void {
    this.deptService.getDepartmentByDeptNo(deptNo).subscribe(
      (data) => {
        this.selectedDepartment = data;
      },
      (error) => {
        console.error(`Error fetching department with deptNo ${deptNo}`, error);
      }
    );
  }
  fetchData(): void {
    this.deptService.getAllDepartments().subscribe(
      (data) => {
        this.departments = data;
      },
      (error) => {
        console.error('Error fetching departments', error);
      }
    );
  }
  name:any;
  getdata:any;
  getDepartmentByDeptName(deptName: string): void {
    this.deptService.getDepartmentByDeptName(deptName).subscribe(
      (data) => {
        this.getdata = data;
        console.log(data);
      },
      (error) => {
        console.error(`Error fetching department with deptNo ${deptName}`, error);
      }
    );
  }
/*  depno1: any;
newname: any;
dep: any;
 
updateDept(): void {
  if (this.depno1 && this.newname) {
    // Assuming 'updateDepartments' method expects an object as the second parameter
    const updatedDept = {
      deptNo: this.depno1,
      deptName: this.newname
    };
 
    this.deptService.updateDepartments(this.depno1, updatedDept)
      .subscribe((dept: any) => {
        this.dep = dept;
        console.log('Department updated:', dept);
      }, error => {
        console.error('Error updating department:', error);
      });
  } else {
    console.error('Department number and new name are required.');
  }
}*/
 
 
updateDept(): void {
  if (this.depno && this.newname) {
    const updatedDept: Departments = {
      deptNo: this.depno,
      deptName: this.newname,
    };
 
    this.deptService.updateDepartments(this.depno, updatedDept)
      .subscribe(
        (dept: Departments) => {
          const index = this.departments.findIndex(d => d.deptNo === dept.deptNo);
          if (index !== -1) {
            this.departments[index] = dept;
          } else {
            this.departments.push(dept);
          }
 
          if (this.selectedDepartment && this.selectedDepartment.deptNo === dept.deptNo) {
            this.selectedDepartment = dept;
          }
 
          console.log('Department updated:', dept);
        },
        (error) => {
          console.error('Error updating department:', error);
        }
      );
  } else {
    console.error('Department number and new name are required.');
  }
}
 
// departments.service
 
updateDepartmentName(): void {
  console.log('deptName:', this.deptName);
  console.log('updatedName:', this.updatedName);
  console.log('Button clicked');
  if (this.deptName && this.updatedName) {
    const updatedDept: Departments = {
      deptNo: '', // Assuming you don't need to update the deptNo
      deptName: this.updatedName,
    };
 
    this.deptService.updateDepartmentByName(this.deptName, updatedDept)
      .subscribe(
        (dept: Departments) => {
          console.log(dept);
        },
        (error) => {
          console.error('Error updating department name:', error);
        }
      );
  } else {
    console.error('Department name and updated name are required.');
  }
}
deleteDepartment(): void {
  if (this.deptNo) {
    this.deptService.deleteDepartment(this.deptNo)
      .subscribe(
        (response: string) => {
          console.log('Department deleted:', response);
        },
        (error: any) => {
          console.error('Error deleting department:', error);
        }
      );
  } else {
    console.error('Department number is required.');
  }
}
deleteDepartmentByName(): void {
  if (this.deptName) {
    this.deptService.deleteDepartmentByName(this.deptName)
      .subscribe(
        (response: string) => {
          console.log('Department deleted:', response);
        },
        (error: any) => {
          console.error('Error deleting department:', error);
        }
      );
  } else {
    console.error('Department name is required.');
  }
}
 
newDepartment: Departments = {
  deptNo: '',
  deptName: ''
};
 
createDepartment(): void {
  console.log(this.newDepartment)
  this.deptService.createDepartment(this.newDepartment)
  // .subscribe(dept=>{
  //   console.log(dept)
  // })
    .subscribe(
      (createdDepartment: Departments) => {
        console.log('Department created:', createdDepartment);
        // Handle success, reset form, or navigate to another page
      },
      (error) => {
        console.error('Error creating department:', error);
        // Handle error
      }
    );
   
}
 
}