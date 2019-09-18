import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NewsComponent } from './news/news.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { SingleNewsComponent } from './single-news/single-news.component';
import { AdminComponent } from './admin/admin.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { PrivilegeComponent } from './privilege/privilege.component';
import { PrivilegeAddComponent } from './privilege-add/privilege-add.component';
import { RoleComponent } from './role/role.component';
import { RoleAddComponent } from './role-add/role-add.component';
import { EditorComponent } from './editor/editor.component';
import { EditorAddComponent } from './editor-add/editor-add.component';
import { EditorUpdateComponent } from './editor-update/editor-update.component';
import { UserComponent } from './user/user.component';
import { UserAddComponent } from './user-add/user-add.component';


const routes: Routes = [
  {path : '', redirectTo: '/news', pathMatch: 'full'},
  {path : 'news', component: NewsComponent},
  {path : 'admin', component: AdminComponent},
  {path : 'login', component: LoginComponent},
  {path : 'register', component: RegistrationComponent},
  {path : 'news/:id', component: SingleNewsComponent},
  {path : 'privileges', component: PrivilegeComponent},
  {path : 'privileges/add', component: PrivilegeAddComponent},
  {path : 'roles', component: RoleComponent},
  {path : 'roles/add', component: RoleAddComponent},
  {path : 'editor', component: EditorComponent},
  {path : 'editor/add', component: EditorAddComponent},
  {path : 'editor/update/:id', component: EditorUpdateComponent},
  {path : 'users', component: UserComponent},
  {path : 'users/add', component: UserAddComponent},
  {path : '**', component: PageNotFoundComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
