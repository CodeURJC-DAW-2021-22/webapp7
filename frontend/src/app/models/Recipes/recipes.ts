export class Recipes {
  id: number;
  preparation:string;
  name: string;
  cookTime: number;
  difficulty: string;
  uploadDate: Date;
  ingredients: string;
  creator: string;

  hasPhoto: boolean;

  vegetables: boolean;
  protein: boolean;
  hydrates: boolean;
  carbohydrates: boolean;
  highinfat: boolean;


  recipeImage: Blob;
}
