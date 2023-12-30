import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DeptManager } from './dept-manager';
 
@Injectable({
  providedIn: 'root'
})
export class DeptManagerService {
  deleteDeptManager(empNo: number, fromDateAsDate: Date) {
    throw new Error('Method not implemented.');
  }
  deleteDeptManagerByEmpNoAndFromDate(empNo: number, fromDate: string) {
    throw new Error('Method not implemented.');
  }
  private apiUrl = 'http://localhost:9999/api/v1/deptmanager';
 
  constructor(private http: HttpClient) {}
 
  getAllDeptManagers(): Observable<DeptManager[]> {
    let url = `${this.apiUrl}/`;
    return this.http.get<DeptManager[]>(url);
  }
  //
  getDeptManagerByEmpNoAndDeptNo(empNo: number, deptNo: string): Observable<DeptManager> {
    let url = `${this.apiUrl}/empno/${empNo}/deptno/${deptNo}`;
    return this.http.get<DeptManager>(url);
  }
  //
  getDeptManagersByDeptNoAndFromDate(deptNo: string, fromDate: string): Observable<DeptManager[]> {
    const url = `${this.apiUrl}/deptno/${deptNo}/fromdate/${fromDate}`;
    return this.http.get<DeptManager[]>(url);
  }
 
  deleteDeptManagerByEmpNoAndDeptNo(empNo: number, deptNo: string): Observable<DeptManager[]> {
    const url = `${this.apiUrl}/empno/${empNo}/deptno/${deptNo}`;
    return this.http.get<DeptManager[]>(url);
  }
  //
 
getDeptManagersByEmpNoAndFromDate(empNo: number, fromDate: string): Observable<DeptManager[]> {
  const url = `${this.apiUrl}/empno/${empNo}/fromdate/${fromDate}`;
  return this.http.get<DeptManager[]>(url);
}
//;
 
}
 