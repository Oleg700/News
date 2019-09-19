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
        return this._http.get("http://localhost:8899/api/news", this.getHeaders());
    }

    getNewsById(newsId: number): Observable<any> {
        return this._http.get("http://localhost:8899/api/news/" + newsId, this.getHeaders())
    }

    addNews(news: News) {
        let body = JSON.stringify(news);
        return this._http.post("http://localhost:8899/api/news", body, this.getHeaders());
    }

    deleteNews(newsId: number) {
        return this._http.delete("http://localhost:8899/api/news/" + newsId, this.getHeaders());
    }

    updateNews(news: News): Observable<any> {
        let body = JSON.stringify(news);
        return this._http.put("http://localhost:8899/api/news/" + news.id, body, this.getHeaders());
    }



    //USERS

    requestUserRoles(user: User): Observable<any>{
        let body = JSON.stringify(user);
        return this._http.get("http://localhost:8899/api/users/"+user.username+"/roles", this.getHeaders());
    }

    getAllUsers(): Observable<any>{
        return this._http.get("http://localhost:8899/api/users", this.getHeaders());
    }

    addUser(user: User){
        let body = JSON.stringify(user);
        return this._http.post("http://localhost:8899/api/users", body, this.getHeaders());
    }


    //ROLES

    getRoles(): Observable<any> {
        return this._http.get("http://localhost:8899/api/roles", this.getHeaders());
    }

    addRole(role: Role) {
        let body = JSON.stringify(role);
        return this._http.post("http://localhost:8899/api/roles", body, this.getHeaders());
    }



    // PRIVILEGES

    getPrivileges(): Observable<any> {
        return this._http.get("http://localhost:8899/api/privileges", this.getHeaders());
    }

    addPrivilege(privilege: Privilege) {
        let body = JSON.stringify(privilege);
        return this._http.post("http://localhost:8899/api/privileges", body, this.getHeaders());
    }

    //COMMENT

    addComment(comment: Comment) {
        const user =this.getUser();
        return this._http.post("http://localhost:8899/api/comments/"+user.username, comment, this.getHeaders());
    }

    getCommentsByNewsId(newsId,page){
        return this._http.get("http://localhost:8899/api/news"+newsId+"/comments/"+page, this.getHeaders());
    }
   
    getNewsWithTwoRecentComments(newsId, page): Observable<any> {
        return this._http.get("http://localhost:8899/api/news/"+newsId+"/comments/"+page, this.getHeaders());
    }


}