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

    registerUser(user: User){
        let body = JSON.stringify(user);
        return this._http.post("http://localhost:8899/api/register", body, this.getHeaders());
    }

    login(user: User) {
        this.user = user;
        localStorage.setItem("user", JSON.stringify(this.user));
    }

    logout() {
        localStorage.setItem("user", null);
        window.location.reload();
    }

    getUser() {
        var authorizedUser = JSON.parse(localStorage.getItem("user"));
        if (authorizedUser != null && authorizedUser.username != null) {
            return authorizedUser;
        }
        localStorage.setItem("user", JSON.stringify(this.user));
        return JSON.parse(localStorage.getItem("user"));
    }

    getHeaders(): any {
        var authorizedUser = JSON.parse(localStorage.getItem("user"));
        if (authorizedUser != null && authorizedUser.username != null) {
            return {
                headers: new HttpHeaders({
                    'Content-Type': 'application/json',
                    'Authorization': 'Basic ' + btoa(authorizedUser.username + ':' + authorizedUser.password)
                })
            }
        }
        return  {
            headers: new HttpHeaders({
                'Content-Type': 'application/json',
            })
        }
    }

    getNews(): Observable<any> {
        return this._http.get("http://localhost:8899/api/news", this.getHeaders());
    }

    getNewsById(newsId: number): Observable<any> {
        return this._http.get("http://localhost:8899/api/news/" + newsId, this.getHeaders())
    }

    addNews(news: News) {
        let body = JSON.stringify(news);
        return this._http.post("http://localhost:8899/api/admin/news", body, this.getHeaders());
    }

    deleteNews(newsId: number) {
        return this._http.delete("http://localhost:8899/api/admin/news/" + newsId, this.getHeaders());
    }

    updateNews(news: News): Observable<any> {
        let body = JSON.stringify(news);
        return this._http.put("http://localhost:8899/api/admin/news/" + news.id, body, this.getHeaders());
    }
}