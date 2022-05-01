import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DietloaderComponent } from './dietloader.component';

describe('DietloaderComponent', () => {
  let component: DietloaderComponent;
  let fixture: ComponentFixture<DietloaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DietloaderComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DietloaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
