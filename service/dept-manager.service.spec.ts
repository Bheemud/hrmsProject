import { TestBed } from '@angular/core/testing';

import { DeptManagerService } from './dept-manager.service';

describe('DeptManagerService', () => {
  let service: DeptManagerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DeptManagerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
