import { RouterModule, Routes} from '@angular/router';
import { NgModule, Component } from '@angular/core';

import { CommonModule } from '@angular/common';
import { AppComponent } from '../../app.component';


const routes: Routes = [
  { path: '', redirectTo: '/appComponent', pathMatch: 'full'},
  { path: 'appComponent', component: AppComponent}

];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
