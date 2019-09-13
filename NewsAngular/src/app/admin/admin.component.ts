import { Component, OnInit } from '@angular/core';
import { NewsService } from '../news/news.service';
import { News } from '../news/news';
import { Router, ActivatedRoute } from '@angular/router';



@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {



  constructor(private _newsService: NewsService, private _router: Router, private _route: ActivatedRoute) { }

  ngOnInit() {
   
  }

  private navigateToUserPage(){
    this._router.navigate(["users"]);
  }

  private navigateToRolePage(){
    this._router.navigate(["roles"]);
  }

  private navigateToPrivilegePage(){
    this._router.navigate(["privileges"]);
  }
 

}
