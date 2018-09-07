import { UserModel } from './../../models/user/user.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class AuthService {

  constructor(private http : HttpClient) { }

  attemptAuth(usuario: UserModel){

    //const credentials = JSON.stringify(usuario);
    const credentials = {username: usuario.username, password: usuario.password};
    return this.http.post<any>('http://localhost:8080/token/genera-token', credentials);

  }

}
