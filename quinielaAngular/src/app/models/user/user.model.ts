import { ProfileModel } from "./profile.model";

export class UserModel {


	public  idUsuario : number;

	public  username : String;

	public  password : String;

	public  fechaCreacion : Date;

    public estatus : boolean;

	public  perfil : ProfileModel;



}
