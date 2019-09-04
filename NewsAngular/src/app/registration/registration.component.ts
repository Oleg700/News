import { Component, OnInit } from '@angular/core';
import { NewsService } from '../news/news.service';
import { User } from '../user/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  private user: User = new User();

  constructor(private _newsService: NewsService, private _router: Router) {
   }

  ngOnInit() {
    
  }

  registerUser(user: User){
      this._newsService.registerUser(user) .subscribe((response) =>
      {console.log(response);});
      this.navigateToNews();
  }

  navigateToNews(){
    this._router.navigate(["news"]);
  }


}
