import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuactiveComponent } from './menuactive.component';

describe('MenuactiveComponent', () => {
  let component: MenuactiveComponent;
  let fixture: ComponentFixture<MenuactiveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MenuactiveComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MenuactiveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
