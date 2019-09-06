import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NewsService } from '../news/news.service';
import { User } from '../user/user';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  private user:User;

  constructor(private _newsService: NewsService, private _router: Router) { }

  ngOnInit() {
     this._newsService.getUser().subscribe(user=>this.user =user);
  }

  navigateToLogin(){
    this._router.navigate(["login"]);
  }

  navigateToRegister(){
    this._router.navigate(["register"]);
  }

  logout(){
    this._newsService.logout();
  }

}
