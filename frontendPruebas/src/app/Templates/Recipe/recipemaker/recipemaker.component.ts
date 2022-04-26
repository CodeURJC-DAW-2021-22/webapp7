import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-recipemaker',
  templateUrl: './recipemaker.component.html',
  styleUrls: ['./recipemaker.component.css']
})
export class RecipemakerComponent implements OnInit {

  difficulties = [
    { id: 1, name: "Easy" },
    { id: 2, name: "Medium" },
    { id: 3, name: "Hard" }
  ];

  @ViewChild("file")
  file: any;

  removeImage:boolean;

  constructor(
    private router: Router,
    activatedRoute: ActivatedRoute,
    private service: BooksService) {

    const id = activatedRoute.snapshot.params['id'];
    if (id) {
      service.getBook(id).subscribe(
        book => this.book = book,
        error => console.error(error)
      );
      this.newBook = false;
    } else {
      this.book = { title: '', description: '', image: false };
      this.newBook = true;
    }
  }

  cancel() {
    window.history.back();
  }

  save() {
    if(this.book.image && this.removeImage){
      this.book.image = false;
    }
    this.service.addBook(this.book).subscribe(
      (book: Book) => this.uploadImage(book),
      error => alert('Error creating new book: ' + error)
    );
  }

  uploadImage(book: Book): void {

    const image = this.file.nativeElement.files[0];
    if (image) {
      let formData = new FormData();
      formData.append("imageFile", image);
      this.service.setBookImage(book, formData).subscribe(
        _ => this.afterUploadImage(book),
        error => alert('Error uploading book image: ' + error)
      );
    } else if(this.removeImage){
      this.service.deleteBookImage(book).subscribe(
        _ => this.afterUploadImage(book),
        error => alert('Error deleting book image: ' + error)
      );
    } else {
      this.afterUploadImage(book);
    }
  }

  private afterUploadImage(book: Book){
    this.router.navigate(['/books/', book.id]);
  }

  bookImage() {
    return this.book.image ? '/api/books/' + this.book.id + '/image' : '/assets/images/no_image.png';
  }

}
