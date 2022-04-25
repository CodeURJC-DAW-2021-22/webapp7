import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecipestoredComponent } from './recipestored.component';

describe('RecipestoredComponent', () => {
  let component: RecipestoredComponent;
  let fixture: ComponentFixture<RecipestoredComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecipestoredComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RecipestoredComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
