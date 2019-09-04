import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NewsComponent } from './news/news.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { SingleNewsComponent } from './single-news/single-news.component';
import { AdminComponent } from './admin/admin.component';
import { AdminAddComponent } from './admin-add/admin-add.component';
import { AdminUpdateComponent } from './admin-update/admin-update.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';


const routes: Routes = [
  {path : '', redirectTo: '/news', pathMatch: 'full'},
  {path : 'news', component: NewsComponent},
  {path : 'admin', component: AdminComponent},
  {path : 'login', component: LoginComponent},
  {path : 'register', component: RegistrationComponent},
  {path:  'admin/add', component: AdminAddComponent},
  {path:  'admin/update/:id', component: AdminUpdateComponent},
  {path : 'news/:id', component: SingleNewsComponent},
  {path : '**', component: PageNotFoundComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
