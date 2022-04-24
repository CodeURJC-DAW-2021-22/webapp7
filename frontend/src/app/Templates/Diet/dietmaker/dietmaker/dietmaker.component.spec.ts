import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DietmakerComponent } from './dietmaker.component';

describe('DietmakerComponent', () => {
  let component: DietmakerComponent;
  let fixture: ComponentFixture<DietmakerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DietmakerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DietmakerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
