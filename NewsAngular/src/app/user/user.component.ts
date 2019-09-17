import { Component, OnInit } from '@angular/core';
import { User } from './user';
import { NewsService } from '../news/news.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  private listUsers: User[];  
  private user: User; 
  constructor(private _newsService: NewsService, private _router: Router, private _route: ActivatedRoute){
    this.user = new User();
  }
  
  ngOnInit(): void{
      this.getUsers();
  }
  
  getUsers(): void{
      this._newsService.getAllUsers()
      .subscribe((newsData) =>
       {this.listUsers = newsData,
           console.log(newsData)
        }, (error) =>{
          console.log(error);
      });
  }

  navigateToAddUser(){
   this._router.navigate(["add"],{relativeTo: this._route}); 
  }
  }