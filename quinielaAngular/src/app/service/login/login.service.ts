import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { UserModel } from './../../models/user/user.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class LoginService {

  private usuario: UserModel;

  constructor(private http: HttpClient) { }


  public validaUsuarioLogin(nombreUsuario: String, password: String): Observable <UserModel>{

    let user = new UserModel(nombreUsuario);
    let jsonUsuario = JSON.stringify(user);

    console.log(user);

    console.log("funciona y entro al servicio");

    return this.http.post<UserModel>("http://localhost:8080/existeUsuario", jsonUsuario);
  }

}
