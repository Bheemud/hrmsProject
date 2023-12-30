import { Component } from '@angular/core';

@Component({
  selector: 'app-navigation-component',
  templateUrl: './navigation-component.component.html',
  styleUrls: ['./navigation-component.component.css']
})
export class NavigationComponentComponent {
router: any;


logout():void{
  sessionStorage.clear();
  this.router.navigate([''])

}
}