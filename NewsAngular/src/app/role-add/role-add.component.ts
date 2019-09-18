import { Component, OnInit } from '@angular/core';
import { Role } from '../role/role';
import { NewsService } from '../news/news.service';
import { Router } from '@angular/router';
import { Privilege } from '../privilege/privilege';
import { PrivilegeAddComponent } from '../privilege-add/privilege-add.component';

@Component({
  selector: 'app-role-add',
  templateUrl: './role-add.component.html',
  styleUrls: ['./role-add.component.css']
})
export class RoleAddComponent implements OnInit {

  private role: Role;

  private listPrivileges: Privilege[];

  private listCheckedPrivileges: Privilege[];

  constructor(private _newsService: NewsService, private _router: Router) {
    this.role = new Role();
    this.listCheckedPrivileges = [];

  }

  ngOnInit() {
    this.getPrivileges();
  }

  selectionChange(input: HTMLInputElement) {
    var privilege: Privilege;
    if (input.checked === true) {
      privilege = this.search(input.value, this.listPrivileges)
      this.listCheckedPrivileges.push(privilege);
    } else {
      this.removeFromCheckedArray(input.value, this.listCheckedPrivileges)
    }
  }

  private removeFromCheckedArray(name, myArray) {
    for (var i = 0; i < myArray.length; i++)
      if (myArray[i].name === name) {
        myArray.splice(i, 1);
        break;
      }
  }

  private search(name, myArray) {
    for (var i = 0; i < myArray.length; i++) {
      if (myArray[i].name === name) {
        return myArray[i];
      }
    }
  }

  getPrivileges() {
    this._newsService.getPrivileges()
      .subscribe((newsData) => {
      this.listPrivileges = newsData,
        console.log(newsData)
      }, (error) => {
        console.log(error);
      });
  }


  addRole(role: Role) {
    role.privileges = this.listCheckedPrivileges;
    this._newsService.addRole(role)
      .subscribe((response) => {
        console.log(response);
        this.navigateToRole();
      });
  }

  private navigateToRole() {
    this._router.navigate(["roles"]);
  }

}
