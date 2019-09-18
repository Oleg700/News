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
import { HttpClientModule} from '@angular/common/http';
import { HeaderComponent } from './header/header.component';
import { LoginComponent } from './login/login.component';
import { UserComponent } from './user/user.component';
import { RegistrationComponent } from './registration/registration.component';
import { ReactiveFormsModule } from '@angular/forms';
import { PrivilegeComponent } from './privilege/privilege.component';
import { PrivilegeAddComponent } from './privilege-add/privilege-add.component';
import { RoleComponent } from './role/role.component';
import { RoleAddComponent } from './role-add/role-add.component';
import { EditorComponent } from './editor/editor.component';
import { EditorAddComponent } from './editor-add/editor-add.component';
import { EditorUpdateComponent } from './editor-update/editor-update.component';
import { UserAddComponent } from './user-add/user-add.component';
import { CommentComponent } from './comment/comment.component';


@NgModule({
  declarations: [
    AppComponent, NewsComponent, PageNotFoundComponent, SingleNewsComponent, AdminComponent, HeaderComponent, LoginComponent, UserComponent, RegistrationComponent, PrivilegeComponent, PrivilegeAddComponent, RoleComponent, RoleAddComponent, EditorComponent, EditorAddComponent, EditorUpdateComponent, UserAddComponent, CommentComponent,
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
