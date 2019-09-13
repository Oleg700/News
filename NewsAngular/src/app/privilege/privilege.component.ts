import { Component, OnInit } from '@angular/core';
import { Privilege } from './privilege';
import { NewsService } from '../news/news.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-privilege',
  templateUrl: './privilege.component.html',
  styleUrls: ['./privilege.component.css']
})
export class PrivilegeComponent implements OnInit {

listPrivileges: Privilege[];

  constructor(private _newsService: NewsService, private _router: Router, private _route: ActivatedRoute) { }

  ngOnInit() {
    this.getPrivileges();
  }

  navigateToAddPrivilege(){
    this._router.navigate(["add"], {relativeTo: this._route});
  }

  getPrivileges(): void{
    this._newsService.getPrivileges()
    .subscribe((newsData) =>
     {this.listPrivileges = newsData,
         console.log(newsData)
      }, (error) =>{
        console.log(error);
    });
}

}
