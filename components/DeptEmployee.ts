export interface DeptEmployee {
    id: {
      deptNo: string;
      empNo: number;
    };
    department: any; 
    employee: any; 
    fromDate: Date;
    toDate: Date;
  }