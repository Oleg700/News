import { Component, OnInit } from '@angular/core';
import { User } from '../user/user';
import { Role } from '../role/role';
import { NewsService } from '../news/news.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-add',
  templateUrl: './user-add.component.html',
  styleUrls: ['./user-add.component.css']
})
export class UserAddComponent implements OnInit {
  private user: User;

  private listRoles: Role[];

  private listCheckedRoles: Role[];

  constructor(private _newsService: NewsService, private _router: Router) {
    this.user = new User();
    this.listCheckedRoles = [];

  }

  ngOnInit() {
    this.getRoles();
  }

  selectionChange(input: HTMLInputElement) {
    var role: Role;
    if (input.checked === true) {
      role = this.search(input.value, this.listRoles)
      this.listCheckedRoles.push(role);
    } else {
      this.removeFromCheckedArray(input.value, this.listCheckedRoles)
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

  getRoles() {
    this._newsService.getRoles()
      .subscribe((newsData) => {
      this.listRoles = newsData,
        console.log(newsData)
      }, (error) => {
        console.log(error);
      });
  }


  addUser(user: User) {
    user.roles = this.listCheckedRoles;
    this._newsService.addUser(user)
      .subscribe((response) => {
        console.log(response);
        this.navigateToUsers();
      });
  }

  private navigateToUsers() {
    this._router.navigate(["users"]);
  }

}
