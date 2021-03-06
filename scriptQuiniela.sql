CREATE DATABASE quiniela;
USE quiniela;

CREATE TABLE perfil(
	ID_PERFIL BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'IDENTIFICADOR DE LA TABLA FUNGE COMO LLAVE PRIMARIA Y ES AUTO INCRMENTABLE',
	NOMBRE_PERFIL VARCHAR(250) NOT NULL COMMENT 'NOMBRE QUE TENDRA EL PERFIL',
	FECHA_CREACION TIMESTAMP NOT NULL COMMENT 'FECHA EN LA QUE SE CREA EL PERFIL',
	PRIMARY KEY (ID_PERFIL)
) COMMENT 'TABLA QUE CONTENDRA LA INFORMACION DE LOS PERFILES QUE SE LE ASIGNARAN A LOS USUARIOS';

CREATE TABLE usuario(
	ID_USUARIO BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'IDENTIFICADOR DE LA TABLA FUNGE COMO LLAVE PRIMARIA Y ES AUTO INCRMENTABLE',
	NOMBRE_USUARIO VARCHAR(150) NOT NULL COMMENT 'NOMBRE DEL USUARIO CON EL QUE ACCEDERA AL SISTEMA',
	PASSWORD VARCHAR(250) NOT NULL COMMENT 'CONTRASEÑA DEL USUARIO PARA QUE PUEDA IDENTIFICARSE EN EL SISTEMA',
	FECHA_CREACION TIMESTAMP NOT NULL COMMENT 'FECHA DE CREACION DEL USUARIO',
	ESTATUS BIT NOT NULL COMMENT 'ESTATUS EN EL QUE SE ENCUENTA EL UUARIO ACTIVO O INACTIVO PARA QUE PUEDA SER CONSIDERADO EL ACCESO AL SISTEMA',
	ID_PERFIL BIGINT(20) NOT NULL COMMENT 'IDENTIFICADOR DE LA TABLA DE PERFILES QUE ESTARA LIGADA AL USUARIO FUNGE COMO LLAVE FORANEA',
	PRIMARY KEY (ID_USUARIO),
	FOREIGN KEY (ID_PERFIL) REFERENCES perfil(ID_PERFIL)
) COMMENT 'TABLA QUE CONTENDRA LA INFORMACION DE LOS USUARIOS Y A LOS PERFILES QUE ESTA LIGADO';

