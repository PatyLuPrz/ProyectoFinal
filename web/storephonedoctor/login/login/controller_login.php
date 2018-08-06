<?php 
	require_once('usuario.php');
	require_once('crud_usuario.php');
	require_once('conexion.php');

	//inicio de sesion
	session_start();

	$USERNAME_CL=new Usuario();
	$crud=new CrudUsuario();
	//verifica si la variable registrarse está definida
	//se da que está definicda cuando el usuario se loguea, ya que la envía en la petición
	if (isset($_POST['registrarse'])) {
		$USERNAME_CL->setNombre($_POST['USERNAME_CL']);
		$USERNAME_CL->setClave($_POST['CONTRASENA_CL']);
		if ($crud->buscarUsuario($_POST['USERNAME_CL'])) {
			$crud->insertar($USERNAME_CL);
			header('Location: index.php');
		}else{
			header('Location: error.php?mensaje=El nombre de usuario ya existe');
		}		
		
	}elseif (isset($_POST['entrar'])) { //verifica si la variable entrar está definida
		$USERNAME_CL=$crud->obtenerUsuario($_POST['USERNAME_CL'],$_POST['CONTRASENA_CL']);
		// si el id del objeto retornado no es null, quiere decir que encontro un registro en la base
		if ($USERNAME_CL->getNombre()!=NULL) {
			$_SESSION['USERNAME_CL']=$USERNAME_CL; //si el usuario se encuentra, crea la sesión de usuario
			header('Location: cuenta.php'); //envia a la página que simula la cuenta
		}else{
			header('Location: error.php?mensaje=Tus nombre de usuario o clave son incorrectos'); // cuando los datos son incorrectos envia a la página de error
		}
	}elseif(isset($_POST['salir'])){ // cuando presiona el botòn salir
		header('Location: index.php');
		unset($_SESSION['USERNAME_CL']); //destruye la sesión
	}
?>