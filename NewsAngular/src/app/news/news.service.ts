import { Injectable, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { News } from './news';
import { map } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../user/user';



@Injectable()
export class NewsService {

    private user: User = new User();

    constructor(private _http: HttpClient) { }

    login(user: User) {
        this.user = user;
    }

    getUser() {
        return this.user;
    }

    getHeaders(): HttpHeaders {
        return new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': 'Basic ' + btoa(this.user.username + ':' + this.user.password)
        })
    }


    getNews(): Observable<any> {

        let httpOptions = {
            headers: this.getHeaders()
        };
        return this._http.get("http://localhost:8899/api/news", httpOptions)
    }

    addNews(news: News) {
        let body = JSON.stringify(news);
        return this._http.post("http://localhost:8899/api/news", body, this.httpOptions);
    }

    deleteNews(newsId: number) {
        return this._http.delete("http://localhost:8899/api/news/" + newsId);
    }

    getNewsById(newsId: number): Observable<any> {
        return this._http.get("http://localhost:8899/api/news/" + newsId)
    }

    updateNews(news: News): Observable<any> {
        let body = JSON.stringify(news);
        return this._http.put("http://localhost:8899/api/news/" + news.id, body, this.httpOptions)
    }
}