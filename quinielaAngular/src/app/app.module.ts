import { LoginService } from './service/login/login.service';
import { UserService } from './service/user.service';
import { TokenStorage } from './core/security/token.storage';
import { LoginComponent } from './components/login/login.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';


import { AppComponent } from './app.component';
import { AppRoutingModule } from './core/app-routing/app-routing.module'
import { UserComponent } from './components/user/user.component';

import { FormsModule } from '@angular/forms';
import { Inteceptor } from './core/security/inteceptor';
import { AuthService } from './core/security/auth.service';


@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [AuthService, UserService, LoginService, TokenStorage,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: Inteceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})

export class AppModule { }
