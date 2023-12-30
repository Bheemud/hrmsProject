import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Titles } from '../login-registerform/Titles';
import { Employee } from './Employee';
import { Salaries } from './Salaries';

@Injectable({
  providedIn: 'root'
})
export class DataserviceService {

  baseUrl: string = "http://localhost:9999/api/v1/titles";
  baseUrl1: string = "http://localhost:9999/api/v1/employee";
  baseurl: string = "http://localhost:9999/api/v1";

  constructor(private httpclient: HttpClient) { }



  getAlltitles(): Observable<Titles[]> {
    return this.httpclient.get<Titles[]>(this.baseUrl);
  }
  deleteTitle(empNo: string): Observable<any> {
    return this.httpclient.delete<any>(`${this.baseUrl}/empno/${empNo}`);
  }

  getTitlesByEmpNoAndTitle(empNo: string, title: string): Observable<Titles[]> {
    const url = `${this.baseUrl}/empno/${empNo}/title/${title}`;
    return this.httpclient.get<Titles[]>(url); 
  }

  deleteTitlesByTitle(title: string): Observable<Titles[]> {
    const url = `${this.baseUrl}/title/${title}`;
    return this.httpclient.delete<Titles[]>(url);
  }

  getTitlesByTitle(title: string): Observable<Titles[]> {
    const url = `${this.baseUrl}/title/${title}`;
    return this.httpclient.get<Titles[]>(url);
  }

  deleteTitleByEmpNoFromDateAndTitle(empNo: string, fromDate: string, title: string): Observable<any> {
    const url = `${this.baseUrl}/empno/${empNo}/fromdate/${fromDate}/title/${title}`;
    return this.httpclient.delete<any>(url);
  }

  getTitlesByEmpNoFromDateAndTitle(empNo: string, fromDate: string, title: string): Observable<Titles[]> {
    const url = `${this.baseUrl}/empno/${empNo}/fromdate/${fromDate}/title/${title}`;
    return this.httpclient.get<Titles[]>(url);
  }

  getTitlesByFromDate(fromDate: string): Observable<Titles[]> {
    const url = `${this.baseUrl}/fromdate/${fromDate}`;
    return this.httpclient.get<Titles[]>(url);
  }
  getAllTitles(): Observable<Titles[]> {
    return this.httpclient.get<Titles[]>(`${this.baseUrl}`);
  }
  addNewTitle(newTitle: Titles): Observable<any> {
    return this.httpclient.post<any>(`${this.baseUrl}/add`, newTitle);
  }


  addTitle(title: Titles): Observable<any> {
    return this.httpclient.post<any>(`${this.baseUrl}/add`, title);
  }


  getAllEmployee(): Observable<Employee[]> {
    return this.httpclient.get<Employee[]>(`${this.baseUrl1}`);
  }
 
  
  deleteEmployeeByEmpNo(empNo: string): Observable<any> {
    return this.httpclient.delete<any>(`${this.baseUrl1}/deleteEmployee/empId/${empNo}`);
  }
 
  // updateEmployeeForEmployee(empNo: string, employee: Employee[]): Observable<string> {
  //   const url = `${this.baseUrl1}/${empNo}`;
  //   return this.httpclient.put<string>(url, employee);
  // }
  updateEmployee(data: any): Observable<any> {
    const updateUrl = `${this.baseUrl1}/update`;
 
    return this.httpclient.post<any>(updateUrl, data);
  }
  getEmployeeById(empNo: number): Observable<Employee> {
    const url = `${this.baseUrl1}/employee/getEmployee/empId/${empNo}`;
    return this.httpclient.get<Employee>(url);
  }
  getEmployeeByfirstName(name: string): Observable<Employee> {
    const url = `${this.baseUrl1}/employee/fetch/firstName/${name}`;
    return this.httpclient.get<Employee>(url);
  }
  getWomenCount():Observable<any>{
    const url = `${this.baseUrl1}/employee/womenEmployeesCount`;
    return this.httpclient.get<any>(url);
  }
 
  getDataByLastName(lastName: string): Observable<any> {
   
 
    return this.httpclient.get<any>(`${this.baseUrl1}/employee/fetch/lastName/${lastName}`);
  }
  updateByFirstName(empNo: number, newFirstName: string): Observable<Employee> {
    const updateUrl = `${this.baseUrl1}/update/firstname/${empNo}`; 
    return this.httpclient.put<Employee>(updateUrl, newFirstName);
  }
  updateByLastName(empNo: number, newLastName: string): Observable<Employee> {
    const updateUrl = `${this.baseUrl1}/update/lastname/${empNo}`; 
    return this.httpclient.put<Employee>(updateUrl, newLastName);
  }
  updateHireDate(empNo: any, newHireDate: any): Observable<Employee> {
    const updateUrl = `${this.baseUrl1}/update/hiredateByEmpId/${empNo}`; 
    const payload = { hireDate: newHireDate };
 
    return this.httpclient.put<Employee>(updateUrl, payload);
  }
 
  deleteEmployee(empNo: number): Observable<any> {
    const deleteUrl = `${this.baseUrl1}/deleteEmployee/empId/${empNo}`;
    return this.httpclient.delete<any>(deleteUrl);
  }
 
  getAllRoles(): Observable<Employee> {
    const url = `${this.baseUrl1}/getDistinct/roles`;
    return this.httpclient.get<Employee>(url);
  }
 
  // addEmployee(newEmployee: any): Observable<any> {
  //   const addUrl = `${this.baseUrl1}`;
  //   return this.httpclient.post<any>(addUrl, newEmployee);
  // }
  addEmployee(newEmployee: any): Observable<any> {
    const addUrl = `${this.baseUrl1}`; // Adjust endpoint as per your API
    return this.httpclient.post<any>(addUrl, newEmployee);
  }
  updateBirthDate(empNo: any, newBirthDate: any): Observable<any> {
    const updateUrl = `${this.baseUrl1}/update/birthdayByEmpId/${empNo}`; 
    const payload = { birthDate: newBirthDate };
 
    return this.httpclient.put<any>(updateUrl, payload);
  }
  getAllManagers(): Observable<any> {
    const manaurl=`${this.baseUrl1}/getAllManagers`;
    return this.httpclient.get<any>(manaurl);
  }
 
  getAllMidAgeEmployes(): Observable<any> {
    const age = `${this.baseUrl1}/MidAgeEmps`;
    return this.httpclient.get<any>(age);
  }
  

  getAllsalaries(): Observable<Salaries[]> {
    return this.httpclient.get<Salaries[]>(`${this.baseurl}/salaries`);
    }
   
    getAllSalaries(): Observable<Salaries[]> {
      return this.httpclient.get<Salaries[]>(`${this.baseurl}/salaries`);
    }
    getSalariesByFromDate(fromDate: Date): Observable<Salaries[]> {
      const url = `${this.baseurl}/salaries/fromdate/${fromDate}`;
      return this.httpclient.get<Salaries[]>(url);
    }
      getSalariesByEmpNo(empNo: string): Observable<Salaries[]> {
      // const formattedDate = this.formatDate(fromDate);
      const url = `${this.baseurl}/salaries/empno/${empNo}`;
      return this.httpclient.get<Salaries[]>(url);
    }
   
    searchSalaryByEmpNoAndFromDate(empNo: string, fromDate: Date): Observable<Salaries[]> {
      const url = `${this.baseurl}/salaries/empno/${empNo}/fromdate/${fromDate}`;
      return this.httpclient.get<Salaries[]>(url);
    }
   
    fetchAllSalary(minSalary: any, maxSalary: any): Observable<Salaries[]> {
      const url = `${this.baseurl}/salaries/minsalary=${minSalary}&maxsalary=${maxSalary}`;
      return this.httpclient.get<Salaries[]>(url);
    }
   
    deleteSalaryByEmpNo(empNo: string): Observable<Salaries[]> {
      const url = `${this.baseurl}/salaries/empno/${empNo}`;
      return this.httpclient.delete<Salaries[]>(url);
    }
   
    deleteSalaryByFromDate(fromDate: Date): Observable<Salaries[]> {
      const url = `${this.baseurl}/salaries/fromdate/${fromDate}`;
      return this.httpclient.delete<Salaries[]>(url);
    }
   
    deleteSalaryByEmpNoAndFromDate(empNo: string, fromDate: Date): Observable<Salaries[]> {
      const url = `${this.baseurl}/salaries/empno/${empNo}/fromdate/${fromDate}`;
      return this.httpclient.delete<Salaries[]>(url);
    }
   
    updateSalaryByEmpNo(empNo: string, salary: number): Observable<Salaries[]> {
      const url = `${this.baseurl}/salaries/empno/${empNo}?salary=${salary}`;
      return this.httpclient.put<Salaries[]>(url, {});
    }
   
    updateSalaryByEmpNo1(empNo: string, fromDate: Date): Observable<Salaries[]> {
      const url = `${this.baseurl}/salaries/empno/${empNo}/fromdate/${fromDate}`;
      return this.httpclient.put<Salaries[]>(url, {});
    }
   
    updateSalaryByFromDate(fromDate: Date, salary: number): Observable<Salaries[]> {
      const url = `${this.baseurl}/salaries/fromdate/${fromDate}/salary/${salary}`;
      return this.httpclient.put<Salaries[]>(url, {});
    }


}