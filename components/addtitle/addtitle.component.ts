import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { DataserviceService } from 'src/app/service/dataservice.service';

@Component({
  selector: 'app-addtitle',
  templateUrl: './addtitle.component.html',
  styleUrls: ['./addtitle.component.css']
})
export class AddtitleComponent {
  title: any = {};

  constructor(private dataService: DataserviceService,private router:Router) {}


  logout():void{
    sessionStorage.clear();
    this.router.navigate([''])
 
  }

  addTitle() {
    console.log(this.title)
    this.dataService.addNewTitle(this.title).subscribe(
    
      (response) => {
        // Optionally perform actions after successful addition
        this.title = { name: '' }; // Reset the title object to clear the form or other actions
      },
      (error) => {
        console.error('Error adding title:', error);
        // Handle errors, display error messages, etc.
      }
    );
  }
}
