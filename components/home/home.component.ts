import { Component } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  showMenu: boolean = false;


  constructor(private router:Router){}

  toggleMenu() {
    this.showMenu = !this.showMenu;
  }
  logout():void{
    sessionStorage.clear();
    this.router.navigate([''])
 
  }
}
