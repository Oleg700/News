import { Component, OnInit } from '@angular/core';
import { NewsService } from '../news/news.service';
import { News } from '../news/news';
import { Router } from '@angular/router';
import { FormControl, Validators, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-admin-add',
  templateUrl: './admin-add.component.html',
  styleUrls: ['./admin-add.component.css']
})
export class AdminAddComponent implements OnInit {

  private news: News;
  private myForm: FormGroup;
  private title: FormControl;
  private brief: FormControl;
  private content: FormControl;

  constructor(private _newsService: NewsService, private _router: Router) { }

  createControls() {
    this.title = new FormControl('', [
      Validators.required,
      Validators.maxLength(100),
    ]);
    this.brief = new FormControl('', [
      Validators.required,
      Validators.maxLength(500),
    ]);
    this.content = new FormControl('', [
      Validators.required,
      Validators.maxLength(2048),
    ]);
  }

  createForm() {
    this.myForm = new FormGroup({
      title: this.title,
      brief: this.brief,
      content: this.content
    });
  }

  ngOnInit() {
    this.news = new News();
    this.createControls();
    this.createForm();
  }

  addNews(news: News){
    this._newsService.addNews(this.news)
    .subscribe((response) =>
    {console.log(response);
     this.navigateToAdminPage();
      })
  }

  navigateToAdminPage(){
    this._router.navigate(["admin"]);
   }

}
