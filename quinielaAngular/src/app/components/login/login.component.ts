import { UserModel } from './../../models/user/user.model';
import { UserComponent } from './../user/user.component';
import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../service/login/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [LoginService]
})
export class LoginComponent implements OnInit {

  private usuario : UserModel;

  constructor( private loginService: LoginService) { }

  ngOnInit() {
    this.validaUsuarioLogin();
  }

  private validaUsuarioLogin(): void{


    this.loginService.validaUsuarioLogin("gpalacios","12345").subscribe(result => {

      console.log("despues de ir al servicio");

      this.usuario = result;

      if(this.usuario["nombreUsuario"] = "gpalacios"){
        console.log("usario correcto");
      }else{
        console.log("usario incorrecto");
      }


      if(this.usuario["password"] = "12345"){
        console.log("password correcto");
      }else{
        console.log("password incorrecto");
      }

    });

  }

}
