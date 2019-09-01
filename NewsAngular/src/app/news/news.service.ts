import { Http, RequestOptions, Response, Headers } from '@angular/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {News} from './news';
import { map } from 'rxjs/operators';




@Injectable()
export class NewsService{

    constructor(private _httpService: Http){}

    getNews():Observable<News[]>{
        return this._httpService.get("http://localhost:8899/api/news")
        .pipe(map((response: Response) => response.json()))  
    }

    addNews(news: News){
        let body = JSON.stringify(news);
        let headers = new Headers({'Content-Type': 'application/json'});
        let options = new RequestOptions({headers: headers});
        return this._httpService.post("http://localhost:8899/api/news", body, options);
    }

    deleteNews(newsId: number){
        return this._httpService.delete("http://localhost:8899/api/news/" + newsId);
    }

    getNewsById(newsId: number):Observable<News>{
        return this._httpService.get("http://localhost:8899/api/news/"+newsId)
        .pipe(map((response: Response) => response.json()));
    }

    updateNews(news: News):Observable<News>{
        let body = JSON.stringify(news);
        let headers = new Headers({'Content-Type': 'application/json'});
        let options = new RequestOptions({headers: headers});
        return this._httpService.put("http://localhost:8899/api/news/"+news.id, body, options)
        .pipe(map((response: Response) => response.json()));
    }
}