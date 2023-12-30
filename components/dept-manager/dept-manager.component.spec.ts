import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeptManagerComponent } from './dept-manager.component';

describe('DeptManagerComponent', () => {
  let component: DeptManagerComponent;
  let fixture: ComponentFixture<DeptManagerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeptManagerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DeptManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
