import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginRegisterformComponent } from './login-registerform/login-registerform.component';
import { TitlesComponent } from './components/titles/titles.component';
import { DepartmentsComponent } from './components/departments/departments.component';
import { DeptEmployeeComponent } from './components/dept-employee/dept-employee.component';
import { DeptManagerComponent } from './components/dept-manager/dept-manager.component';
import { EmployeeComponent } from './components/employee/employee.component';
import { HomeComponent } from './components/home/home.component';
import { SalariesComponent } from './components/salaries/salaries.component';
import { RegistercomponentComponent } from './login-registerform/registercomponent/registercomponent.component';
import { AddtitleComponent } from './components/addtitle/addtitle.component';
import { AuthGuardGuard } from './auth-guard.guard';
import { AllEmployeesComponent } from './components/employee/all-employees/all-employees.component';
import { AddemployeeComponent } from './components/employee/addemployee/addemployee.component';
import { UpdateEmployeeComponent } from './components/employee/update-employee/update-employee.component';
import { DeleteemployeeComponent } from './components/employee/deleteemployee/deleteemployee.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { ForgotpasswordComponent } from './components/forgotpassword/forgotpassword.component';
import { ResetpasswordComponent } from './components/resetpassword/resetpassword.component';

const routes: Routes = [
  {path: "", component: LoginRegisterformComponent},
  {path:"register",component:RegistercomponentComponent},
  {path:"Home",component:HomeComponent,canActivate:[AuthGuardGuard]},
  {path:"departments",component:DepartmentsComponent,canActivate:[AuthGuardGuard]},
  {path:"departmentmanger",component:DeptManagerComponent,canActivate:[AuthGuardGuard]},
  {path:"departmentemployee",component:DeptEmployeeComponent,canActivate:[AuthGuardGuard]},
  {path:"employee",component:EmployeeComponent,canActivate:[AuthGuardGuard]},
  {path:"salaries",component:SalariesComponent,canActivate:[AuthGuardGuard]},
  {path:"titles",component:TitlesComponent,canActivate:[AuthGuardGuard]},
  {path:"addtitles",component:AddtitleComponent,canActivate:[AuthGuardGuard]},
  {path:"allemployees",component:AllEmployeesComponent,canActivate:[AuthGuardGuard]},
  {path:"addemployee",component:AddemployeeComponent,canActivate:[AuthGuardGuard]},
  {path:"updateemployee",component:UpdateEmployeeComponent,canActivate:[AuthGuardGuard]},
  {path:"deleteemployee",component:DeleteemployeeComponent,canActivate:[AuthGuardGuard]},
  { path: 'home', component: HomeComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  {path:"loginregisterform" ,component:LoginRegisterformComponent},
  {path:"forgotpassword",component:ForgotpasswordComponent},
  {path:"resetpassword",component:ResetpasswordComponent},
  { path: '**', component: PageNotFoundComponent },


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
