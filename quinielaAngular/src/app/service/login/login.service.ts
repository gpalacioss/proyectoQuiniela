import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { UserModel } from './../../models/user/user.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class LoginService {


  constructor(private http: HttpClient) { }

  public validaCamposObligatorios(usuario : UserModel): boolean{

    let isValido : boolean = true;

    console.log("Valor Usario private :: " + usuario['nombreUsuario'])
    //console.log("Valor Usario publico :: " + usuario.nombreUsuario)

    if(!usuario['nombreUsuario']){
      isValido = false;
    }

    if(!usuario.password){
      isValido = false;
    }

    return isValido;

  }


  public saveOrUpdate(usuario : UserModel): Observable <UserModel>{

    let jsonUsuario = JSON.stringify(usuario);

    console.log(usuario);

    console.log("funciona y entro al servicio");

    return this.http.post<UserModel>("http://localhost:8080/existeUsuario", jsonUsuario);
  }

}