import { ProfileModel } from "./profile.model";

export class UserModel {

	private  idUsuario : number;
	
	private  nombreUsuario : String;
	
	private  password : String;
	
	private  fechaCreacion : Date;
	
    private estatus : boolean;
    
    private  perfil : ProfileModel;
    
} 