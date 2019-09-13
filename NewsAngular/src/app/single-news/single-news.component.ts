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
    {this.news = newsData
    this.comments = this.news.comments}, 
    (error) =>{console.log(error);})
}
}
