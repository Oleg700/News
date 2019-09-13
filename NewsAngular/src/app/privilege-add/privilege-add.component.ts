import { Component, OnInit } from '@angular/core';
import { NewsService } from '../news/news.service';
import { Privilege } from '../privilege/privilege';
import { Router } from '@angular/router';

@Component({
  selector: 'app-privilege-add',
  templateUrl: './privilege-add.component.html',
  styleUrls: ['./privilege-add.component.css']
})
export class PrivilegeAddComponent implements OnInit {

  private privilege: Privilege;

  constructor(private _newsService: NewsService, private _router: Router) {
    this.privilege = new Privilege();
   }

  ngOnInit() {
  }

  addPrivilege(privilege: Privilege){
      this._newsService.addPrivilege(privilege)
      .subscribe((response) =>
      {console.log(response);
       this.navigateToPrivilege();
        });
  }

private navigateToPrivilege(){
  this._router.navigate(["privileges"]);
}

}
