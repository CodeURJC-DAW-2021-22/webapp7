import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecipeupdaterComponent } from './recipeupdater.component';

describe('RecipeupdaterComponent', () => {
  let component: RecipeupdaterComponent;
  let fixture: ComponentFixture<RecipeupdaterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecipeupdaterComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RecipeupdaterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
