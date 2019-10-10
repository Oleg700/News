import { Injectable, OnInit } from '@angular/core';
import { Observable, of } from 'rxjs';
import { News } from './news';
import { Privilege } from '../privilege/privilege';
import { map } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../user/user';
import { Role } from '../role/role';
import { Comment } from '../comment/comment';
import { CommentRequest } from '../comment/comment-request';



@Injectable()
export class NewsService {

    private user: User = new User();

    language = "en";

    setLanguage(language: string){
        this.language = language;
    }

    addLocaleToUrl(){
        return "?lang="+this.language;
    }

    constructor(private _http: HttpClient) { }

    login(user: User) {
        this.user = user;
        localStorage.setItem("user", JSON.stringify(this.user));
    }

    logout() {
        localStorage.setItem("user", null);
        window.location.reload();
    }

    getUserObservable(): Observable<User> {
        var authorizedUser = JSON.parse(localStorage.getItem("user"));
        if (authorizedUser != null && authorizedUser.username != null) {
            return of(authorizedUser);
        }
        localStorage.setItem("user", JSON.stringify(this.user));
        return of(JSON.parse(localStorage.getItem("user")));
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
    

    // NEWS

    getNews(): Observable<any> {
        return this._http.get("http://localhost:8899/api/news" + this.addLocaleToUrl(), this.getHeaders());
    }

    getNewsById(newsId: number): Observable<any> {
        return this._http.get("http://localhost:8899/api/news/" + this.addLocaleToUrl() + newsId, this.getHeaders())
    }

    addNews(news: News) {
        let body = JSON.stringify(news);
        return this._http.post("http://localhost:8899/api/news" + this.addLocaleToUrl(), body, this.getHeaders());
    }

    deleteNews(newsId: number) {
        return this._http.delete("http://localhost:8899/api/news/" + newsId + this.addLocaleToUrl(), this.getHeaders());
    }

    updateNews(news: News): Observable<any> {
        let body = JSON.stringify(news);
        return this._http.put("http://localhost:8899/api/news/" + news.id + this.addLocaleToUrl(), body, this.getHeaders());
    }



    //USERS

    requestUserRoles(user: User): Observable<any>{
        let body = JSON.stringify(user);
        return this._http.get("http://localhost:8899/api/users/"+user.username+"/roles" + this.addLocaleToUrl(), this.getHeaders());
    }

    getAllUsers(): Observable<any>{
        return this._http.get("http://localhost:8899/api/users" + this.addLocaleToUrl(), this.getHeaders());
    }

    addUser(user: User){
        let body = JSON.stringify(user);
        return this._http.post("http://localhost:8899/api/users" + this.addLocaleToUrl(), body, this.getHeaders());
    }


    //ROLES

    getRoles(): Observable<any> {
        return this._http.get("http://localhost:8899/api/roles" + this.addLocaleToUrl(), this.getHeaders());
    }

    addRole(role: Role) {
        let body = JSON.stringify(role);
        return this._http.post("http://localhost:8899/api/roles" + this.addLocaleToUrl(), body, this.getHeaders());
    }



    // PRIVILEGES

    getPrivileges(): Observable<any> {
        return this._http.get("http://localhost:8899/api/privileges" + this.addLocaleToUrl(), this.getHeaders());
    }

    addPrivilege(privilege: Privilege) {
        let body = JSON.stringify(privilege);
        return this._http.post("http://localhost:8899/api/privileges" + this.addLocaleToUrl(), body, this.getHeaders());
    }

    //COMMENT

    addComment(comment: Comment, newsId: number) {
        return this._http.post("http://localhost:8899/api/news/"+ newsId + "/comments" + this.addLocaleToUrl(), comment, this.getHeaders());
    }

   
   
    getNewsWithTwoRecentComments(newsId, page): Observable<any> {
        return this._http.get("http://localhost:8899/api/news/"+newsId+"/comments/"+page + this.addLocaleToUrl(), this.getHeaders());
    }


}