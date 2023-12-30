export interface DeptManagerId {
    departmentNumber: string;
    employeeNumber: string;
  }
   
  export interface DeptManager {
    id: DeptManagerId;
    department: any; // Define Departments interface as needed
    employee: any; // Define Employee interface as needed
    fromDate: Date;
    toDate: Date;
  }