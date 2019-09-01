import { Component, OnInit } from '@angular/core';
import { NewsService } from '../news/news.service';
import { News } from '../news/news';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-add',
  templateUrl: './admin-add.component.html',
  styleUrls: ['./admin-add.component.css']
})
export class AdminAddComponent implements OnInit {

  private news: News;

  constructor(private _newsService: NewsService, private _router: Router) { }

  ngOnInit() {
    this.news = new News();
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
