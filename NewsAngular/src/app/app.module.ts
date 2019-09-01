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

@NgModule({
  declarations: [
    AppComponent, NewsComponent, PageNotFoundComponent, SingleNewsComponent, AdminComponent, AdminAddComponent, AdminUpdateComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpModule,
    FormsModule
  ],
  providers: [NewsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
