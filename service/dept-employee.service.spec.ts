import { TestBed } from '@angular/core/testing';

import { DeptEmployeeService } from './dept-employee.service';

describe('DeptEmployeeService', () => {
  let service: DeptEmployeeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DeptEmployeeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
