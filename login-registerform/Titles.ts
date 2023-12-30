export interface Titles {
  empNo: string;
  name: string;
  fromDate: Date;
  toDate: Date;
  title: string;
  employee: {
    empNo: number;
    firstName: string;
    lastName: string;
  };
}
