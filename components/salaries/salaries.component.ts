import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Salaries } from 'src/app/service/Salaries';
import { DataserviceService } from 'src/app/service/dataservice.service';
 
@Component({
  selector: 'app-salaries',
  templateUrl: './salaries.component.html',
  styleUrls: ['./salaries.component.css']
})
export class SalariesComponent implements OnInit {
  fromDate: any;
  empNo: any;
  salary: any;
  minSalary: any;
  maxSalary: any;
deleteSalary(_t52: any) {
throw new Error('Method not implemented.');
}
uniqueSalaries: any;
 
  searchTerm: string = '';
  salaries: Salaries[] = [];
  filteredSalaries: any;
  salaryParams: any;
  newSalary: any;
  // salary: any;
  // salaries: any;
 
  constructor(private dataService: DataserviceService,private router:Router) {
    this.salaries = [];
    this.filteredSalaries=this.salaries
  }
 
  ngOnInit(): void {
   // this.loadSalaries();
  }
 
  // loadSalaries(): void {
  //   this.dataService.getAllsalaries().subscribe((salaries: any) => {
  //     console.log(salaries);
  //     this.salaries=this.filteredSalaries=salaries;
  //     this.applyFilter(); // Apply filter after fetching titles
  //   });
  // }
 
  applyFilter(): void {
    this.filteredSalaries=this.salaries.filter(s=>
      s.empNo.indexOf(this.searchTerm)>=0
      // s.salary.indexOf(this.searchTerm)>=0
    );
  }
 
 
  editSalary(salaries: Salaries): void {
    console.log('Edit:', salaries);
  }
 
  ngOnChanges(): void {
    this.applyFilter();
  }
 
 
  getAllSalaries(): void {
    this.dataService.getAllSalaries().subscribe(
      (salaries: Salaries[]) => {
        const uniqueSalariesMap: { [key: string]: number } = {};
 
        salaries.forEach(salary => {
          if (!uniqueSalariesMap[salary.salary]) {
            uniqueSalariesMap[salary.salary] = 1;
          } else {
            uniqueSalariesMap[salary.salary]++;
          }
        });
 
        this.uniqueSalaries = Object.keys(uniqueSalariesMap).map(salary => ({ salary, count: uniqueSalariesMap[salary] }));
      },
      (error: any) => {
        console.error('Error fetching salaries:', error);
      }
    );
  }
 
  fetchedSalaries:any;
  onSearched(): void {
    if (this.fromDate) {
      this.dataService.getSalariesByFromDate(this.fromDate.toString).subscribe(
        (salaries: Salaries[]) => {
          this.fetchedSalaries = salaries;
        },
        (error: any) => {
          console.error('Error fetching salaries:', error);
        }
      );
    } else {
      alert('Please select a from date.');
    }
  }
 
  onSearching(): void {
    if (this.empNo) {
      this.dataService.getSalariesByEmpNo(this.empNo).subscribe(
        (salaries: Salaries[]) => {
          this.fetchedSalaries = salaries;
        },
        (error: any) => {
          console.error('Error fetching salaries:', error);
        }
      );
    } else {
      alert('Please fill in all the required fields.');
    }
  }
 
  onSearch(): void {
    if (this.empNo && this.fromDate) {
      this.dataService.searchSalaryByEmpNoAndFromDate(this.empNo, this.fromDate.toString).subscribe(
        (salaries: Salaries[]) => {
          this.fetchedSalaries = salaries;
        },
        (error: any) => {
          console.error('Error fetching salaries:', error);
          if (error.status === 404) {
            this.fetchedSalaries = [];
          }
        }
      );
    } else {
      alert('Please fill in all the fields.');
    }
  }
 
  onFetchAllSalary(): void {
    this.dataService.fetchAllSalary(this.minSalary, this.maxSalary).subscribe(
      (salaries: Salaries[]) => {
        this.fetchedSalaries = salaries;
      },
      (error: any) => {
        console.error('Error fetching salaries:', error);
      }
    );
  }
 
  onDeleteByEmpNo(): void {
    if (this.empNo) {
      this.dataService.deleteSalaryByEmpNo(this.empNo).subscribe(
        (deletedSalaries: Salaries[]) => {
          this.fetchedSalaries = deletedSalaries;
        },
        (error: any) => {
          console.error('Error deleting salaries:', error);
        }
      );
    } else {
      alert('Please fill in the Employee Number.');
    }
  }
 
  onDeleteByFromDate(): void {
    if (this.fromDate) {
      this.dataService.deleteSalaryByFromDate(this.fromDate.toString).subscribe(
        (deletedSalaries: Salaries[]) => {
          this.fetchedSalaries = deletedSalaries;
        },
        (error: any) => {
          console.error('Error deleting salaries:', error);
        }
      );
    } else {
      alert('Please select a From Date.');
    }
  }
 
  onDeleteByEmpNoAndFromDate(): void {
    if (this.empNo && this.fromDate) {
      this.dataService.deleteSalaryByEmpNoAndFromDate(this.empNo, this.fromDate.toString).subscribe(
        (deletedSalaries: Salaries[]) => {
          this.fetchedSalaries = deletedSalaries;
        },
        (error: any) => {
          console.error('Error deleting salaries:', error);
        }
      );
    } else {
      alert('Please fill in all the required fields.');
    }
  }
 
  onUpdateSalaryByEmpNo(): void {
    if (this.empNo && this.newSalary) {
      this.dataService.updateSalaryByEmpNo(this.empNo, this.newSalary).subscribe(
        (updatedSalaries: Salaries[]) => {
          this.fetchedSalaries = updatedSalaries;
        },
        (error: any) => {
          console.error('Error updating salaries:', error);
        }
      );
    } else {
      alert('Please fill in all the required fields.');
    }
  }
 
  onUpdateSalaryByEmpNo1(): void {
    if (this.empNo && this.fromDate) {
      this.dataService.updateSalaryByEmpNo(this.empNo, this.fromDate.toString).subscribe(
        (updatedSalaries: Salaries[]) => {
          this.fetchedSalaries = updatedSalaries;
        },
        (error: any) => {
          console.error('Error updating salaries:', error);
        }
      );
    } else {
      alert('Please fill in all the required fields.');
    }
  }
 
  onUpdateSalaryByFromDate(): void {
    if (this.fromDate && this.newSalary) {
      this.dataService.updateSalaryByFromDate(this.fromDate.toString, this.newSalary).subscribe(
        (updatedSalaries: Salaries[]) => {
          this.fetchedSalaries = updatedSalaries;
        },
        (error: any) => {
          console.error('Error updating salaries:', error);
        }
      );
    } else {
      alert('Please fill in all the required fields.');
    }
  }

  logout():void{
    sessionStorage.clear();
    this.router.navigate([''])
 
  }
 
  // onAddNewSalary(): void {
  //   this.dataService.addNewSalary(this.newSalary).subscribe(
  //     (addedSalary: Salaries) => {
  //       console.log('New salary added:', addedSalary);
  //     },
  //     (error: any) => {
  //       console.error('Error adding new salary:', error);
  //     }
  //   );
  // }
}