import { Component, OnInit } from '@angular/core';
import { News } from '../news/news';
import { NewsService } from '../news/news.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-editor-update',
  templateUrl: './editor-update.component.html',
  styleUrls: ['./editor-update.component.css']
})
export class EditorUpdateComponent implements OnInit {

  news: News;

  constructor(private _newsService: NewsService, private _route: ActivatedRoute, private _router: Router) { }

  ngOnInit() {
    this.news = new News();
    let id = parseInt( this._route.snapshot.paramMap.get('id'));
    this.getNewsById(id);
  }

  getNewsById(newsId: number){
    this._newsService.getNewsById(newsId)
    .subscribe((newsData) =>
    {this.news = newsData}, 
    (error) =>{console.log(error);})
  }

 updateNews(news: News){
    this._newsService.updateNews(news)
    .subscribe((response) =>
    {console.log(response);
    this.navigateToAdminPage()}); 
 }

 navigateToAdminPage(){
  this._router.navigate(["editor"]);
 }

}
