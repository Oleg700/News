import { Component, OnInit } from '@angular/core';
import { User } from '../user/user';
import { NewsService } from '../news/news.service';
import { Router } from '@angular/router';
import { FormControl, Validators, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private user: User = new User();;
  myForm: FormGroup;
  username: FormControl;
  password: FormControl;


  constructor(private _newsService: NewsService, private _router: Router) {
  }

  createControls() {
    this.username = new FormControl('', [
      Validators.required,
      Validators.minLength(4),
      Validators.maxLength(8),
    ]);
    this.password = new FormControl('', [
      Validators.required,
      Validators.minLength(4),
      Validators.maxLength(8),
    ]);
  }

  createForm() {
    this.myForm = new FormGroup({
      username: this.username,
      password: this.password
    });
  }


  ngOnInit() {
    this.createControls();
    this.createForm();
  }

  login(user: User) {
    user.enable = true;
    this._newsService.login(user);
    this.checkUserRole();
  }

  navigateByUserRole() {
    const role = this.user.roles[0];

    switch (role.name) {
      case "ROLE_ADMIN":
        this._router.navigate(["admin"]);
        break;
      case "ROLE_EDITOR":
        this._router.navigate(["editor"]);
        break;
      case "ROLE_USER":
        this._router.navigate(["news"]);
        break;
    }
  }


   private checkUserRole() {
    this._newsService.requestUserRoles(this.user)
      .subscribe((roles) => {
        this.user.roles = roles,
        this.navigateByUserRole();
      }, (error) => {
        console.log(error);
      });
    return this.user.roles;
  }

  //   this._router.navigate(["admin"]);



}
