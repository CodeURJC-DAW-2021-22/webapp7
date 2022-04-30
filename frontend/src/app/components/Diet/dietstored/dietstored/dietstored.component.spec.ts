import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DietstoredComponent } from './dietstored.component';

describe('DietstoredComponent', () => {
  let component: DietstoredComponent;
  let fixture: ComponentFixture<DietstoredComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DietstoredComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DietstoredComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
