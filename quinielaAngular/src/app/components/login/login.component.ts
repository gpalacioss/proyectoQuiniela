import { UserModel } from './../../models/user/user.model';
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
  private isValido : boolean = true;
  private message: String;

  constructor( private loginService: LoginService) { 
    this.usuario = new UserModel();
  }

  ngOnInit() {
    
  }


  private saveOrUpdateUsuario(): void{

    if(this.isValido = this.loginService.validaCamposObligatorios(this.usuario)){

      this.loginService.saveOrUpdate(this.usuario).subscribe(result => {

        if(result != null){
  
          this.usuario = result;
  
          if(this.usuario.nombreUsuario != result.nombreUsuario){
            this.message = "usario incorrecto";
            this.isValido = false;
          }
    
          if(this.usuario.password != result.password){
            this.message = "password incorrecto";
            this.isValido = false;
          }
  
        }else{
            this.message = "El usario no existe";
            this.isValido = false;
        }
      
      });
    }else{
      this.message = "Los campos no pueden estar vacios";
    }

  }

}
