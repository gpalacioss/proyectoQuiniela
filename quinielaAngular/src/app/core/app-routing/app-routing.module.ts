import { UserComponent } from './../../components/user/user.component';
import { RouterModule, Routes} from '@angular/router';
import { NgModule, Component } from '@angular/core';

import { AppComponent } from '../../app.component';


const routes: Routes = [
  /**
   * redirecciona al appComponent que es la ruta principal cuando le ponen un espacio
   */
  { path: '', redirectTo: '/appComponent', pathMatch: 'full'},

  /**
   * redirecciona al appComponent que es la ruta principal
   */
  { path: 'appComponent', component: AppComponent},
  { path: 'user', component: UserComponent}

];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
