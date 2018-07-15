import { ProfileModel } from "./profile.model";

export class UserModel {

  constructor(usario: String){
    this.nombreUsuario = usario
  }

  private  idUsuario : number;

  private  nombreUsuario : String;

  private  password : String;

  //private  fechaCreacion : Date;

  private estatus : boolean;

  private  perfil : ProfileModel;



}
