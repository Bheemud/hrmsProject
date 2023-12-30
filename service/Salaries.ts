export interface Salaries{
    empNo:string;
    fromDate: Date;
    toDate: Date;
    salary:any;
    Employee:{
    empNo: string;
    birthDate:Date;
    hireDate:Date;
    firstName:string;
    lastName:string;
    email:string;
    gender:any;
    password:string;
    role:string;
    }
}