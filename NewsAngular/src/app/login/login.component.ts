import { Component, OnInit } from '@angular/core';
import { User } from '../user/user';
import { NewsService } from '../news/news.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

private user:User;

  constructor(private _newsService: NewsService, private _router: Router) {
    this.user = new User();
   }

  ngOnInit() {
  }

  login(user:User){
      this._newsService.login(user);
      this.navigateToHomePage();
  }

  navigateToHomePage(){
    this._router.navigate(["news"]);
  }


}
