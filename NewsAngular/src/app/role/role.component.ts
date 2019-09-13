import { Component, OnInit } from '@angular/core';
import { NewsService } from '../news/news.service';
import { Role } from './role';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-role',
  templateUrl: './role.component.html',
  styleUrls: ['./role.component.css']
})
export class RoleComponent implements OnInit {

private listRoles: Role[];

constructor(private _newsService: NewsService, private _router: Router, private _route: ActivatedRoute) { }

  ngOnInit() {
    this.getAllRoles();
  }

  getAllRoles(){
    this._newsService.getRoles()
    .subscribe((newsData) =>
    {this.listRoles = newsData,
        console.log(newsData)
     }, (error) =>{
       console.log(error);
   });
  }

  private navigateToAddRole(){
    this._router.navigate(["add"], {relativeTo: this._route});
  }

}
