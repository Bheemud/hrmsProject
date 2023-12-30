import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, catchError, tap, throwError } from 'rxjs';
import { DeptEmployee } from '../components/DeptEmployee';
 
 
@Injectable({
  providedIn: 'root',
})
export class DeptEmployeeService {
 
  private apiUrl = 'http://localhost:9999/api/v1/deptemp';
  constructor(private http: HttpClient) {}
 
  
 
  fetchAllDeptEmp(): Observable<DeptEmployee[]> {
    return this.http.get<DeptEmployee[]>(this.apiUrl);
  }
 
  searchByEmpnoAndDeptno(empNo: number, deptNo: string): Observable<DeptEmployee[]> {
    const url = `${this.apiUrl}/empno/${empNo}/deptno/${deptNo}`;
    return this.http.get<DeptEmployee[]>(url, { params: { empNo: empNo.toString(), deptNo: deptNo } });
  }
 
  updateDeptEmployee(empNo: number, deptNo: string, fromDate: string, toDate: string): Observable<DeptEmployee> {
    const updateUrl = `${this.apiUrl}/${empNo}/${deptNo}?fromDate=${fromDate}&toDate=${toDate}`;
 
    return this.http.put<DeptEmployee>(updateUrl, null); // Assuming you are sending an empty body for the update
  }
 
  deleteDeptEmp(empNo: number, deptNo: string): Observable<string> {
    const url = `${this.apiUrl}/empno/${empNo}/deptno/${deptNo}`;
    return this.http.delete(url, { responseType: 'text' });
  }
 
 
  searchByDeptnoAndFromdate(deptNo: string, fromDate: string): Observable<DeptEmployee[]> {
    const url = `${this.apiUrl}/deptno/${deptNo}/fromdate/${fromDate}`;
    return this.http.get<DeptEmployee[]>(url);
  }
 
  searchByEmpnoAndFromdate(empNo: number, fromDate: string): Observable<DeptEmployee[]> {
    const url = `${this.apiUrl}/empno/${empNo}/fromdate/${fromDate}`;
    console.log('Request URL:', url);
 
    return this.http.get<DeptEmployee[]>(url);
  }
  // deleteDeptEmpByEmpNoAndFromDate(empNo: number, fromDate: string): Observable<any> {
  //   const url = `${this.apiUrl}/empno/${empNo}/fromdate/${fromDate}`;
  //   return this.http.delete(url);
  // }
  deleteDeptEmpByEmpNoAndFromDate(empNo: number, fromDate: string): Observable<any> {
    const url = `${this.apiUrl}/empno/${empNo}/fromdate/${fromDate}`;
    return this.http.delete(url, { responseType: 'text' });
  }
 
  deleteDeptEmpByDeptNoAndFromDate(deptNo: string, fromDate: string): Observable<any> {
    const url = `${this.apiUrl}/deptno/${deptNo}/fromdate/${fromDate}`;
    return this.http.delete(url, { responseType: 'text' });
  }
 
  deleteDeptEmpByEmpNoDeptNoFromDate(empNo: number, deptNo: string, fromDate: string): Observable<any> {
    const url = `${this.apiUrl}/empno/${empNo}/deptno/${deptNo}/fromdate/${fromDate}`;
    return this.http.delete(url, { responseType: 'text' });
  }
 
 
 
}
 
 
