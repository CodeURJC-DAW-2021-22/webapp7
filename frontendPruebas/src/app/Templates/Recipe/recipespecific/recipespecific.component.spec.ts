import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecipespecificComponent } from './recipespecific.component';

describe('RecipespecificComponent', () => {
  let component: RecipespecificComponent;
  let fixture: ComponentFixture<RecipespecificComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecipespecificComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RecipespecificComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
