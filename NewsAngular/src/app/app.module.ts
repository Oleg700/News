import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {NewsComponent} from './news/news.component';
import { NewsService } from './news/news.service';
import { HttpModule } from '@angular/http';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { SingleNewsComponent } from './single-news/single-news.component';
import { AdminComponent } from './admin/admin.component';
import { AdminAddComponent } from './admin-add/admin-add.component';
import { AdminUpdateComponent } from './admin-update/admin-update.component';
import { HttpClientModule} from '@angular/common/http';
import { HeaderComponent } from './header/header.component';
import { LoginComponent } from './login/login.component';
import { UserComponent } from './user/user.component';
import { RegistrationComponent } from './registration/registration.component';
import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    AppComponent, NewsComponent, PageNotFoundComponent, SingleNewsComponent, AdminComponent, AdminAddComponent, AdminUpdateComponent, HeaderComponent, LoginComponent, UserComponent, RegistrationComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [NewsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
