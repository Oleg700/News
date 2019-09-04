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
    this.user = this._newsService.getUser();
  }

  navigateToLogin(){
    this._router.navigate(["login"]);
  }

  logout(){
    this._newsService.logout();
  }

}
