<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="main.css" type="text/css"> 
<style>
            .pro_1{
                margin: 10px;
				padding: 5px;
            }
            #img_p{
                height: 100px;
                width: 100px;
            }
            #tabla_p{
                align-content: center;
                border: solid 1px silver;
            }
			.PRODUCTO{
                background-color:cornsilk;
				margin: 10px;
				padding: 5px;
				float: left;
				height: 280px;
				width: 200px;
				border: solid silver 1px;
				border-radius: 5px;
				text-align: center;
			}
			#nombre_p{
				font-size: 20px;
				text-align: center;
			}
				
        </style>    
</head>

<body class="" style="background-image: url('img/FondoPrograma.png');background-repeat:no-repeat;">
  <div class="p-4">
    <div class="container">
      <div class="row"></div>
    </div>
  </div>
  <nav class="navbar navbar-expand-md fixed-top navbar-dark bg-dark">
    <div class="container-fluid">
      <a class="navbar-brand align-items-center align-self-center" href="#">
        <img src="C:/Users/TeddyBear/Documents/TIC32/Proyecto/storephonedoctor/img/logo (2).png" width="50
30
30" height="80" class="d-inline-block align-top float-left img-fluid" alt="">
        <b class="h1 - align-items-center align-self-center justify-content-center">&nbsp;&nbsp;StorePhone Doctor&nbsp;
          <br> </b>
      </a>
      <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbar2SupportedContent">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse text-center justify-content-end" id="navbar2SupportedContent">
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link" href="#">
              <i class="fa d-inline fa-lg fa-bookmark-o"></i> Acerca de...</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              <i class="fa d-inline fa-lg fa-envelope-o"></i> Contactanos</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              <i class="fa fa-cart-arrow-down fa-lg d-inline"></i>&nbsp;Productos</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              <i class="fa fa-home fa-lg d-inline"></i>&nbsp;Inicio</a>
          </li>
        </ul>
        <a class="btn navbar-btn ml-2 text-white btn-secondary">
          <i class="fa d-inline fa-lg fa-user-circle-o"></i> Cuenta</a>
      </div>
    </div>
  </nav>
  <div class="py-5">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
            <div class="py-5">
                
                
                <?php

 $host_db = "localhost";
 $user_db = "root";
 $pass_db = "";
 $db_name = "storephonedoctor";
 $tbl_name = "clientes";
 

 $USERNAME_CL = htmlspecialchars($_POST['USERNAME_CL']);
 $EMAIL_CL = htmlspecialchars($_POST['EMAIL_CL']);
 $form_pass = htmlspecialchars($_POST['CONTRASENA_CL']);
 $hash = sha1($form_pass); 
 $NOMBRE_CL = htmlspecialchars($_POST['NOMBRE_CL']);
 $AP_CL = htmlspecialchars($_POST['AP_CL']);
 $AM_CL = htmlspecialchars($_POST['AM_CL']);
 $TEL_CL = htmlspecialchars($_POST['TEL_CL']);
 $MUN_CL = htmlspecialchars($_POST['MUN_CL']);
 

 $conexion = new mysqli($host_db, $user_db, $pass_db, $db_name);

 if ($conexion->connect_error) {
 die("La conexion falló: " . $conexion->connect_error);
}

 $buscarUsuario = "SELECT * FROM $tbl_name
 WHERE USERNAME_CL = '$USERNAME_CL'";
$buscarEmail = "SELECT * FROM $tbl_name
 WHERE EMAIL_CL = '$EMAIL_CL'";

 $result = $conexion->query($buscarUsuario);
 $result1 = $conexion->query($buscarEmail);

 $count = mysqli_num_rows($result);
 $count1 = mysqli_num_rows($result1);

 if ($count == 1 || $count1 == 1) {
     
 echo "<br />". "El Nombre de Usuario o Correo electronico ya a sido tomado." . "<br />";

 echo "<a href='index.html'>Por favor escoga otro Nombre</a>";
 }
 else{

 $query = "INSERT INTO clientes (USERNAME_CL,EMAIL_CL,CONTRASENA_CL,NOMBRE_CL,AP_CL,AM_CL,TEL_CL,MUN_CL)
           VALUES ('$USERNAME_CL','$EMAIL_CL', '$hash','$NOMBRE_CL','$AP_CL','$AM_CL','$TEL_CL','$MUN_CL')";

 if ($conexion->query($query) === TRUE) {
 
 echo "<br />" . "<h2>" . "Usuario Creado Exitosamente!" . "</h2>";
 echo "<h4>" . "Bienvenido: " . $USERNAME_CL . "</h4>" . "\n\n";
 echo "<h5>" . "Hacer Login: " . "<a href='login.html'>Login</a>" . "</h5>"; 
 }

 else {
 echo "Error al crear el usuario." . $query . "<br>" . $conexion->error; 
   }
 }
 mysqli_close($conexion);
?>

                
                
                
            </div>
        </div>
      </div>
    </div>
  </div>
  <div class="py-5 bg-dark text-white">
    <div class="container">
      <div class="row">
        <div class="col-md-9">
          <p class="lead">Registrate para tener acceso a nuestras ofertas especiales</p>
          <form class="form-inline">
            <div class="form-group">
              <input type="email" class="form-control" placeholder="Your e-mail here"> </div>
            <button type="submit" class="btn btn-primary ml-3">Subscribe</button>
          </form>
        </div>
        <div class="col-4 col-md-1 align-self-center">
          <a href="https://www.facebook.com" target="_blank">
            <i class="fa fa-fw fa-facebook fa-3x text-white"></i>
          </a>
        </div>
        <div class="col-4 col-md-1 align-self-center">
          <a href="https://twitter.com" target="_blank">
            <i class="fa fa-fw fa-twitter fa-3x text-white"></i>
          </a>
        </div>
        <div class="col-4 col-md-1 align-self-center">
          <a href="https://www.instagram.com" target="_blank">
            <i class="fa fa-fw fa-instagram text-white fa-3x"></i>
          </a>
        </div>
      </div>
      <div class="row">
        <div class="col-md-12 mt-3 text-center">
          <p>© Copyright 2017 StorePhone Doctor - Todos los derechos reservados.</p>
        </div>
      </div>
    </div>
  </div>
  
</body>

</html>