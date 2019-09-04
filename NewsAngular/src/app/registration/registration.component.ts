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


  constructor(private _newsService: NewsService, private _router: Router) {
   }

   createForm() {
     this.myForm = new FormGroup({
      username: new FormControl('', Validators.required),
      password: new FormControl('', [
        Validators.required,
        Validators.minLength(4),
        Validators.maxLength(8),
      ])
     });
   }

  ngOnInit() {
    this.createForm();
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
