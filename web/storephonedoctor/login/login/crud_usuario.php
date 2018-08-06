<?php 
	require_once('conexion.php');
	require_once('usuario.php');
	
	class CrudUsuario{

		public function __construct(){}

		//inserta los datos del usuario
		public function insertar($usuario){
            $Usuario=new Usuario();
            
            $USERNAME_CL = $Usuario->getNombre();
            $EMAIL_CL = $Usuario->getEmail();
            $CONTRASENA_CL = $CONTRASENA_CL->getClave();
            $NOMBRE_CL = $NOMBRE_CL->getNombrecl();
            $AP_CL = $AP_CL->getAp();
            $AM_CL = $AM_CL->getAm();
            $TEL_CL = $TEL_CL->getTel();
            $MUN_CL = $MUM_CL->getMun();
            
                
			$db=DB::conectar();
			$insert=$db->prepare('INSERT INTO clientes VALUES(:USERNAME_CL,:EMAIL_CL,:CONTRASENA_CL,:NOMBRE_CL,:AP_CL,:AM_CL,:TEL_CL,:MUN_CL)');
			$insert->bindValue(':USERNAME_CL',$USERNAME_CL, PDO::PARAM_STR);
            $insert->bindValue(':EMAIL_CL',$EMAIL_CL, PDO::PARAM_STR);
			//encripta la clave
			$CONTRASENA_CL=password_hash($USERNAME_CL,PASSWORD_DEFAULT);
			$insert->bindValue(':CONTRASENA_CL',$CONTRASENA_CL, PDO::PARAM_STR);
            $insert->bindValue(':NOMBRE_CL',$NOMBRE_CL, PDO::PARAM_STR);
            $insert->bindValue(':AP_CL',$AP_CL, PDO::PARAM_STR);
            $insert->bindValue(':AM_CL',$AM_CL, PDO::PARAM_STR);
            $insert->bindValue(':TEL_CL',$TEL_CL, PDO::PARAM_STR);
            $insert->bindValue(':MUN_CL',$MUM_CL, PDO::PARAM_STR);
			$insert->execute();
            
		}

		//obtiene el usuario para el login
		public function obtenerUsuario($USERNAME_CL, $CONTRASENA_CL){
			$db=Db::conectar();
			$select=$db->prepare('SELECT * FROM CLIENTES WHERE USERNAME_CL=:USERNAME_CL');//AND clave=:clave
			$select->bindValue('USERNAME_CL',$USERNAME_CL);
			$select->execute();
			$registro=$select->fetch();
			$usuario=new Usuario();
			//verifica si la clave es conrrecta
			if (password_verify($CONTRASENA_CL, $registro['CONTRASENA_CL'])) {				
				//si es correcta, asigna los valores que trae desde la base de datos
				$usuario->setNombre($registro['USERNAME_CL']);
				$usuario->setNombre($registro['NOMBRE_CL']);
				$usuario->setClave($registro['CONTRASENA_CL']);
			}			
			return $USERNAME_CL;
		}

		//busca el nombre del usuario si existe
		public function buscarUsuario($USERNAME_CL){
			$db=Db::conectar();
			$select=$db->prepare('SELECT * FROM CLIENTES WHERE USERNAME_CL=:USERNAME_CL');
			$select->bindValue('USERNAME_CL',$USERNAME_CL);
			$select->execute();
			$registro=$select->fetch();
			if($registro['USERNAME_CL']!=NULL){
				$usado=False;
			}else{
				$usado=True;
			}	
			return $usado;
		}
	}
?>