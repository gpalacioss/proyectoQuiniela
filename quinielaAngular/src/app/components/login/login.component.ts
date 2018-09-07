import { AuthService } from './../../core/security/auth.service';
import { UserModel } from './../../models/user/user.model';
import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../service/login/login.service';
import { TokenStorage } from '../../core/security/token.storage';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {

  private usuario : UserModel;
  private isValido : boolean = true;
  private message: String;

  constructor( private loginService: LoginService, private authenticationService: AuthService, private token: TokenStorage, private router: Router) { 
    this.usuario = new UserModel();
  }

  ngOnInit() {
    
  }

  login(): void {
    this.authenticationService.attemptAuth(this.usuario).subscribe(result => {
      this.token.saveToken(result.token);
      this.router.navigate(['/user'])
    });
  }


  private saveOrUpdateUsuario(): void{

    if(this.isValido = this.loginService.validaCamposObligatorios(this.usuario)){

      this.loginService.saveOrUpdate(this.usuario).subscribe(result => {

        if(result != null && this.validaDatosCorrectos(result)){
            //aqui tendria que regresar el token
        }else{
            this.message = "El usario no existe";
            this.isValido = false;
        }
      
      });
    }else{
      this.message = "Los campos no pueden estar vacios";
    }

  }

  private validaDatosCorrectos(result : UserModel): boolean{

    if(this.usuario.username != result.username){
      this.message = "usario incorrecto";
      this.isValido = false;
    }

    if(this.usuario.password != result.password){
      this.message = "password incorrecto";
      this.isValido = false;
    }

    return this.isValido;
  }

}
