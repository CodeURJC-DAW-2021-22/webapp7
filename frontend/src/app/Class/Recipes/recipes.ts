export class Recipes {
  id?: number;
  name: string;
  cookTime: number;
  difficulty: string;
  uploadDate: Date;
  ingredients: string;
  creator: string;
  preparation:string;

  hasPhoto: boolean;
  recipeImage: Blob;

  vegetbales: boolean;
  protein: boolean;
  hydrates: boolean;
  carbohydrates: boolean;
  highinfat: boolean;
}
