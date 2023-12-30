import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddtitleComponent } from './addtitle.component';

describe('AddtitleComponent', () => {
  let component: AddtitleComponent;
  let fixture: ComponentFixture<AddtitleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddtitleComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddtitleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
