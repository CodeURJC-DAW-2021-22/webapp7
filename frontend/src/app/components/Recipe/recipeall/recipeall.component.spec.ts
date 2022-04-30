import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecipeallComponent } from './recipeall.component';

describe('RecipeallComponent', () => {
  let component: RecipeallComponent;
  let fixture: ComponentFixture<RecipeallComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecipeallComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RecipeallComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
