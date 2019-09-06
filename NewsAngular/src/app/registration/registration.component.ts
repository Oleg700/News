import { Component, OnInit } from '@angular/core';
import { NewsService } from '../news/news.service';
import { User } from '../user/user';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  private user: User = new User();
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

  registerUser(user: User) {
    this._newsService.registerUser(user).subscribe((response) => { console.log(response); });
    this.navigateToNews();
  }

  navigateToNews() {
    this._router.navigate(["news"]);
  }




}
