import {Component, OnInit} from '@angular/core';
import {News} from './news';
import { sharedStylesheetJitUrl } from '@angular/compiler';
import { NewsService } from './news.service';
import {Router, ActivatedRoute} from '@angular/router'

@Component({
    selector: 'app-news',
    templateUrl: './news.component.html',
    styleUrls: ['./news.component.css']
})
export class NewsComponent implements OnInit{

private listNews: News[];  
private news: News;
constructor(private _newsService: NewsService, private _router: Router, private _route: ActivatedRoute){
    this.news = new News();
}

ngOnInit(): void{
    this.getNews();
}

getNews(): void{
    this._newsService.getNews()
    .subscribe((newsData) =>
     {this.listNews = newsData,
         console.log(newsData)
      }, (error) =>{
        console.log(error);
    });
}

navigateToSingleNews(news){
 this._router.navigate([news.id],{relativeTo: this._route});
}

}