import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginRegisterformComponent } from './login-registerform.component';

describe('LoginRegisterformComponent', () => {
  let component: LoginRegisterformComponent;
  let fixture: ComponentFixture<LoginRegisterformComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoginRegisterformComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoginRegisterformComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
