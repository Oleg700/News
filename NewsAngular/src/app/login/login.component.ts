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

private user:User= new User();;



  constructor(private _newsService: NewsService, private _router: Router) {
   }

  ngOnInit() {
  }

  login(user:User){
      user.enable=true;
      this._newsService.login(user);
      this.navigateToHomePage();
  }

  navigateToHomePage(){
    this._router.navigate(["admin"]);
  }


}
