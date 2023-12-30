import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { Titles } from 'src/app/login-registerform/Titles';
import { DataserviceService } from 'src/app/service/dataservice.service';

@Component({
  selector: 'app-titles',
  templateUrl: './titles.component.html',
  styleUrls: ['./titles.component.css']
})
export class TitlesComponent implements OnInit {

  

  fetchedTitlesCount: number = 0;
  searchText: string = '';

  empNum: string = ''; 
titlle: string = ''; 

employeeNumber: string = '';
jobTitle: string = '';

  titles: Titles[] = [];
  searchTerm: string = '';
  filteredTitles: Titles[] = [];
empNoToDelete: any;
empNoToDeletee: any;
empNoToDeleteee: any;
titlet: string = '';
empNoo: string = '';
empNo: string = '';
  title: string = '';
  titleee: string = '';
  titlee: string = '';
  fetchedTitles: Titles[] = [];
  fromDate: string = '';
  fromDatee: string = '';
  uniqueTitles: { title: string, count: number }[] = [];


titless:Titles[]=[];

  





  constructor(private dataService: DataserviceService,private route:Router) {
    this.titles = [];
    this.filteredTitles = [];
  }

  ngOnInit(): void {
  }



  

  onDelete(): void {
    if (!this.empNoToDelete || this.empNoToDelete.trim() === '') {
      alert('Please enter an employee number.');
      return;
    }
  
    if (confirm(`Are you sure you want to delete titles for employee number ${this.empNoToDelete}?`)) {
      this.dataService.deleteTitle(this.empNoToDelete).subscribe(
        () => {
          console.log(`Titles for employee ${this.empNoToDelete} deleted successfully.`);
        //  this.loadTitles(); // Reload titles after deletion
        },
        error => {
          console.error('Error deleting titles:', error);
        }
      );
    }
  }

  onSearch(): void {
    if (this.empNo && this.title) {
      this.dataService.getTitlesByEmpNoAndTitle(this.empNo, this.title).subscribe(
        titles => {
          console.log('Titles:', titles);
        },
        error => {
          console.error('Error fetching titles:', error);
        }
      );
    } else {
      alert('Please enter both employee number and title.');
    }
  }
  
  Delete(form: any): void {
    if (form.valid) {
      const titleToDelete: string = form.value.title;
      this.dataService.deleteTitlesByTitle(titleToDelete).subscribe(
        (response) => {
          console.log('Titles deleted:', response);
          form.reset(); 
        },
        (error) => {
          console.error('Error deleting titles:', error);
        }
      );
    } else {
      alert('Please enter a title to delete.');
    }
  }

  onSearchh(): void {
    if (this.title.trim()) {
      this.dataService.getTitlesByTitle(this.title).subscribe(
        titles => {
          this.fetchedTitles = titles;
          this.countFetchedTitles(); // Update the count after fetching titles
        },
        error => {
          console.error('Error fetching titles:', error);
        }
      );
    } else {
      alert('Please enter a title to search.');
    }
  }
  
  countFetchedTitles(): void {
    this.fetchedTitlesCount = this.fetchedTitles.length;
    console.log('Total fetched titles:', this.fetchedTitlesCount);
    // You can use this.fetchedTitlesCount to display the count in your UI
  }

  onDeleteTitle(): void {
    if (this.empNo && this.fromDate && this.title) {
      this.dataService.deleteTitleByEmpNoFromDateAndTitle(
        this.empNo,
        this.fromDate.toString(), 
        this.title
      ).subscribe(
        response => {
          console.log('Title deleted:', response);
        },
        error => {
          console.error('Error deleting title:', error);
        }
      );
    } else {
      alert('Please fill in all the fields.');
    }
  }
  
  onSearching(): void {
    if (this.empNo && this.fromDate && this.title) {
      this.dataService.getTitlesByEmpNoFromDateAndTitle(this.empNo, this.fromDate, this.title).subscribe(
        titles => {
          this.fetchedTitles = titles;
        },
        error => {
          console.error('Error fetching titles:', error);
          if (error.status === 404) {
            this.fetchedTitles = [];
          }
        }
      );
    } else {
      alert('Please fill in all the fields.');
    }
  }


  onSearched(): void {
    if (this.fromDate) {
      this.dataService.getTitlesByFromDate(this.fromDate).subscribe(
        titles => {
          this.fetchedTitles = titles;
        },
        error => {
          console.error('Error fetching titles:', error);
        }
      );
    } else {
      alert('Please select a from date.');
    }
  }
  
  getAllTitles(): void {
    this.dataService.getAllTitles().subscribe(
      (titles: Titles[]) => {
        const uniqueTitlesMap: { [key: string]: number } = {};
  
        titles.forEach(title => {
          if (!uniqueTitlesMap[title.title]) {
            uniqueTitlesMap[title.title] = 1;
          } else {
            uniqueTitlesMap[title.title]++;
          }
        });
  
        this.uniqueTitles = Object.keys(uniqueTitlesMap).map(title => ({ title, count: uniqueTitlesMap[title] }));
      },
      (error: any) => {
        console.error('Error fetching titles:', error);
      }
    );
  }

  redirectToAddTitles() {
    this.route.navigate(['/addtitles']);
  }

  getTitles(): void {
    if (this.employeeNumber && this.jobTitle) {
      this.dataService.getTitlesByEmpNoAndTitle(this.employeeNumber, this.jobTitle)
        .subscribe(
          (data) => {
            this.titles = data;
            console.log('Fetched Titles:', this.titles);
          },
          (error) => {
            console.error('Error fetching titles:', error);
          }
        );
    } else {
      alert('Please fill in both Employee Number and Title.');
    }
  }


  logout():void{
    sessionStorage.clear();
    this.route.navigate([''])
 
  }


}
