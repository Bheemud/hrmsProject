import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { compileDeclareDirectiveFromMetadata } from '@angular/compiler';
import { Departments } from './Departments';
 
@Injectable({
  providedIn: 'root'
})
export class DepartmentsService {
 
 
  private apiUrl = 'http://localhost:9999/api/v1/Department';
 
  constructor(private http: HttpClient) {
}

 
 
getAllDepartments(): Observable<Departments[]> {
  return this.http.get<Departments[]>(this.apiUrl);
}
getDepartmentByDeptNo(deptNo: string): Observable<Departments> {
  const url = `${this.apiUrl}/${deptNo}`;
  return this.http.get<Departments>(url);
}
getDepartmentByDeptName(deptName: string): Observable<Departments> {
  const url = `${this.apiUrl}/name/${deptName}`;
  return this.http.get<Departments>(url);
}

 
updateDepartments(deptno: string, dept: Departments): Observable<Departments> {
  const url = `${this.apiUrl}/${deptno}`;
  return this.http.put<Departments>(url, dept);
}
updateDepartmentByName(deptName: string, updatedDept: Departments): Observable<Departments> {
  const url = `${this.apiUrl}/name/{deptName}`;
  return this.http.put<Departments>(url, updatedDept);
}
deleteDepartment(deptNo: string): Observable<string> {
  const url = `${this.apiUrl}/${deptNo}`;
  return this.http.delete<string>(url);
}
deleteDepartmentByName(deptName: string): Observable<string> {
  const url = `${this.apiUrl}/name/${deptName}`;
  return this.http.delete<string>(url);
}
 
 
 
createDepartment(department: Departments): Observable<Departments> {
  return this.http.post<Departments>(this.apiUrl, department);
}
 
} 