import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeptEmployeeComponent } from './dept-employee.component';

describe('DeptEmployeeComponent', () => {
  let component: DeptEmployeeComponent;
  let fixture: ComponentFixture<DeptEmployeeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeptEmployeeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DeptEmployeeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
