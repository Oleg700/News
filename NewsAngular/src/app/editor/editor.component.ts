import { Component, OnInit } from '@angular/core';
import { NewsService } from '../news/news.service';
import { Router, ActivatedRoute } from '@angular/router';
import { News } from '../news/news';

@Component({
  selector: 'app-editor',
  templateUrl: './editor.component.html',
  styleUrls: ['./editor.component.css']
})
export class EditorComponent implements OnInit {

  private listNews;
  private news;

  constructor(private _newsService: NewsService, private _router: Router, private _route: ActivatedRoute) { }

  ngOnInit() {
    this.getNews();
    this.news = new News();
  }

  getNews(){
    this._newsService.getNews()
    .subscribe((newsData) => { this.listNews = newsData},
                (error) => {console.log(error)});   
  }

  deleteNews(id: number){
    this._newsService.deleteNews(id)
    .subscribe((response) =>
     {console.log(response);
      this.getNews();}); 
  }

  navigateToAddNews(){
    this._router.navigate(["add"],{relativeTo: this._route});
  }

  navigateToUpdateNews(news: News){
    this._router.navigate(["update/" + news.id], {relativeTo: this._route});
  }

  navigateToSingleNews(news){
    this._router.navigate(["news",news.id]);
   }

}
