<?php 
	session_start();
	unset($_SESSION['usuario']);
?>
<!DOCTYPE html>
<html>
<head>
	<title></title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
</head>
<body>
<header>
	<div class="w3-container w3-black w3-center">
		<h1>BIENVENIDO AL SERVICIO EN LINEA DE STOREPHONE DOCTOR</h1>
	</div>
</header>

	
	<div class="w3-container w3-green">
		<h2>Login</h2>
	</div>

	<form class="w3-container" action="controller_login.php" method="post">
		<p>
			<label class="w3-label">
				Usuario
			</label>
			<input class="w3-input w3-border " type="text" name="USERNAME_CL">
		</p>
		<p>
			<label class="w3-label">Contraseña</label>
			<input class="w3-input w3-border" type="CONTRASENA_CL" name="pas">
		</p>
		<p>
			<input type="hidden" name="entrar" value="entrar">
			<button class="w3-btn w3-green">Aceptar</button>
		</p>
		<p>Si aún no tienes cuenta ve al siguiente link <a href="registrarse.php">Registrarse</a></p>
	</form>
<footer>
	<div class="w3-container w3-black">
		<h4>STOREPHONE DOCTOR 2018</h4>
	</div>
</footer>
</body>
</html>