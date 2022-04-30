import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuloaderComponent } from './menuloader.component';

describe('MenuloaderComponent', () => {
  let component: MenuloaderComponent;
  let fixture: ComponentFixture<MenuloaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MenuloaderComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MenuloaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
