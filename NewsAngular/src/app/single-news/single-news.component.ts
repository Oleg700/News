import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import { News } from '../news/news';
import { NewsService } from '../news/news.service';
import {Comment} from '../comment/comment';

@Component({
  selector: 'app-single-news',
  templateUrl: './single-news.component.html',
  styleUrls: ['./single-news.component.css']
})
export class SingleNewsComponent implements OnInit {

  private news: News;

  private comments: Comment[];

  private page;
  
  constructor(private _newsService: NewsService , private _route: ActivatedRoute) { }

  ngOnInit() {
    let id = parseInt(this._route.snapshot.paramMap.get('id'));
     this.page = 2;
    this.getNewsWithTwoRecentComments(id, this.page);
    this.news = new News();
    this.comments = [];
  }

getNewsWithTwoRecentComments(newsId: number, page: number){
  this._newsService.getNewsWithTwoRecentComments(newsId, page)
  .subscribe((newsData) =>
  {this.news = newsData,
    this.comments =  this.comments.concat(this.news.comments)},
  (error) =>{console.log(error);})
}

increasePageByTwo(){
  this.page = this.page * 2;
  console.log(this.page)
  this.getNewsWithTwoRecentComments(this.news.id ,this.page)
}

}
