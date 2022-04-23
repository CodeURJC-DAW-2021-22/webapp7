import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenumakerComponent } from './menumaker.component';

describe('MenumakerComponent', () => {
  let component: MenumakerComponent;
  let fixture: ComponentFixture<MenumakerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MenumakerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MenumakerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
