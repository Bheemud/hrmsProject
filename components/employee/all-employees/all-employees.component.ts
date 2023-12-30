import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from 'src/app/service/Employee';
import { DataserviceService } from 'src/app/service/dataservice.service';

@Component({
  selector: 'app-all-employees',
  templateUrl: './all-employees.component.html',
  styleUrls: ['./all-employees.component.css']
})
export class AllEmployeesComponent implements OnInit{


  start:number=0;
  end:number=20;

  employee: Employee[] = [];
  employeeToDisplay:Employee[]=[];

  idSearch: string = '';
  firstNameSearch: string = '';
  lastNameSearch: string = '';
  genderSearch: string = '';


  constructor(private dataService: DataserviceService,private router:Router) {
  
    }
  ngOnInit(): void {
    this.dataService.getAllEmployee()
      .subscribe((data: Employee[]) => {
        this.employee = data
        this.filterEmployees();
        this.employeeToDisplay =this.employee.splice(this.start,this.end)
      }
      )

    this.start=this.end;
    this.end=this.end+20;
    
    
    }
    filterEmployees(): void {
      this.employeeToDisplay = this.employee.filter(employee =>
        employee.empNo.toString().toLowerCase().includes(this.idSearch.toLowerCase()) &&
        employee.firstName.toLowerCase().includes(this.firstNameSearch.toLowerCase()) &&
        employee.lastName.toLowerCase().includes(this.lastNameSearch.toLowerCase()) &&
        employee.gender.toLowerCase().includes(this.genderSearch.toLowerCase())
      );
    }

    
  showEmployees = false;
  fetchEmployees(chk:boolean): void {
   
        
        if(chk){
            if(this.end<=this.employee.length){
              this.employeeToDisplay  = this.employee.slice(this.start,this.end);
        this.showEmployees = true;
        this.start = this.end
        this.end = this.end + 20
        }
          else{
          this.start=0;
          this.end=20;
          this.employee  = this.employee.slice(this.start,this.end);

        }
      }
    if(!chk){
        if(this.start>0){
          this.end = this.start
          this.start = this.start-20
          this.employeeToDisplay  = this.employee.slice(this.start,this.end);
          this.showEmployees = true;
         
          }
          else{
            this.start=0;
            this.end=20;
            
         
            this.employeeToDisplay = this.employee.slice(this.start,this.end)
          }

      }

        
     
  }
  logout():void{
    sessionStorage.clear();
    this.router.navigate([''])
 
  }

}
