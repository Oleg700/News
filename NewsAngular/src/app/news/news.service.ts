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

    logout(){
        this.user = new User();
    }

    getUser() {
        return this.user;
    }

    getHeaders(): any {
        return  {
            headers: new HttpHeaders({
                'Content-Type': 'application/json',
                'Authorization': 'Basic ' + btoa(this.user.username + ':' + this.user.password)
            })
        }
    }


    getNews(): Observable<any> {
        return this._http.get("http://localhost:8899/api/news", this.getHeaders())
    }

     addNews(news: News) {
         
         let body = JSON.stringify(news);
         return this._http.post("http://localhost:8899/api/news", body, this.getHeaders());
     }

    deleteNews(newsId: number) {
        return this._http.delete("http://localhost:8899/api/news/" + newsId, this.getHeaders());
    }

    getNewsById(newsId: number): Observable<any> {
        return this._http.get("http://localhost:8899/api/news/" + newsId, this.getHeaders())
    }

     updateNews(news: News): Observable<any> {
         let body = JSON.stringify(news);
         return this._http.put("http://localhost:8899/api/news/" + news.id, body, this.getHeaders());
     }
}