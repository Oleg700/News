import { Component, OnInit } from '@angular/core';
import { NewsService } from '../news/news.service';
import { News } from '../news/news';
import { ActivatedRoute } from '@angular/router';
import { Comment } from './comment';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {

  comment: Comment;
  news: News;

  constructor(private _newsService: NewsService , private _route: ActivatedRoute) {
    this.comment = new Comment();
   }

  ngOnInit() {
    let id = parseInt(this._route.snapshot.paramMap.get('id'));
    this.getNewsById(id);
  }

  getNewsById(newsId: number){
    this._newsService.getNewsById(newsId)
    .subscribe((newsData) =>
    {this.news = newsData;
      this.comment.news = this.news;}, 
    (error) =>{console.log(error);})
}

  addComment(comment: Comment){
    this._newsService.addComment(comment)
    .subscribe((response) =>
    {console.log(response);
      });
    }
}
