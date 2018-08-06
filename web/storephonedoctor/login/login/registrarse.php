<!DOCTYPE html>
<html>
<head>
	<title>Registrarse</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
</head>
<body>
<header>
	<div class="w3-container w3-black">
		<h1>BIENVENIDO A ECODEUP</h1>
	</div>
</header>
		<div class="w3-container w3-green">
			<h1>Registrarse</h1>
		</div>
		<div>
			<form class="w3-container" action="controller_login.php" method="post">
				<p>
					<label class="w3-label">Nombre de usuario</label>
					<input class="w3-input w3-border" type="text" name="USERNAME_CL">
				</p>
                <p>
					<label class="w3-label">Correo</label>
					<input class="w3-input w3-border" type="text" name="EMAIL_CL">
				</p>
				<p>
					<label class="w3-label">Contraseña</label>
					<input class="w3-input w3-border" type="password" name="CONTRASENA_CL">
				</p>
                <p>
					<label class="w3-label">Nombre</label>
					<input class="w3-input w3-border" type="text" name="NOMBRE_CL">
				</p>
                <p>
					<label class="w3-label">Apellido Paterno</label>
					<input class="w3-input w3-border" type="text" name="AP_CL">
				</p>
                <p>
					<label class="w3-label">Apellido Materno</label>
					<input class="w3-input w3-border" type="text" name="AM_CL">
				</p>
                <p>
					<label class="w3-label">Telefono </label>
					<input class="w3-input w3-border" type="text" name="TEL_CL">
				</p>
                <p>
					<label class="w3-label">Municipio</label>
					<input class="w3-input w3-border" type="text" name="MUN_CL">
				</p>
				<p>
					<input type="hidden" name="registrarse" value="registrarse">
					<button class="w3-btn w3-green">Registrarse</button>
				</p>
				<p><a href="index.php">Ahora no</a></p>
			</form>
		</div>
<footer>
	<div class="w3-container w3-black">
		<h4>STOREPHONE DOCTOR 2018</h4>
	</div>
</footer>

</body>
</html>