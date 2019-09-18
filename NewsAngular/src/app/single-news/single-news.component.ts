import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import { News } from '../news/news';
import { NewsService } from '../news/news.service';

@Component({
  selector: 'app-single-news',
  templateUrl: './single-news.component.html',
  styleUrls: ['./single-news.component.css']
})
export class SingleNewsComponent implements OnInit {

  private news: News;

  private comments;
  
  constructor(private _newsService: NewsService , private _route: ActivatedRoute) { }

  ngOnInit() {
    let id = parseInt(this._route.snapshot.paramMap.get('id'));
    this.getNewsById(id);
    this.news = new News();
  }

  getNewsById(newsId: number){
    this._newsService.getNewsById(newsId)
    .subscribe((newsData) =>
    // {this.setPropertiesToNews(newsData)},
    (error) =>{console.log(error);})
}

private setPropertiesToNews(newsData){
  this.news.title = newsData.title;
  this.news.brief = newsData.brief;
  this.news.date = newsData.date;
  this.news.content = newsData.content;
}

getCommentsByNewsId(){
  this._newsService.getCommentsByNewsId(this.news.id, 2)
  .subscribe((newsData) =>
    {this.comments = newsData}, 
    (error) =>{console.log(error);})
}
}
