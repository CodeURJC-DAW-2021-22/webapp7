import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuallComponent } from './menuall.component';

describe('MenuallComponent', () => {
  let component: MenuallComponent;
  let fixture: ComponentFixture<MenuallComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MenuallComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MenuallComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
