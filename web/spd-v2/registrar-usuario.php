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
    .txt{
        color: white;
    }
        </style>    
</head>
<?php include("includes/nav.php"); ?>
<body class="" style="background-image: url('img/FondoPrograma.png');background-repeat:no-repeat;">
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

 echo "<a href='singup.php'>Por favor escoga otro Nombre</a>";
 }
 else{

 $query = "INSERT INTO clientes (USERNAME_CL,EMAIL_CL,CONTRASENA_CL,NOMBRE_CL,AP_CL,AM_CL,TEL_CL,MUN_CL)
           VALUES ('$USERNAME_CL','$EMAIL_CL', '$hash','$NOMBRE_CL','$AP_CL','$AM_CL','$TEL_CL','$MUN_CL')";

 if ($conexion->query($query) === TRUE) {
 
 echo "<br />" . "<h2 class='txt'>" . "Usuario Creado Exitosamente!" . "</h2>";
 echo "<h4 class='txt'>" . "Bienvenido: " . $USERNAME_CL . "</h4>" . "\n\n";
 echo "<h5 class='txt'>" . "Hacer Login: " . "<a href='login.php'>Login</a>" . "</h5>"; 
 }

 else {
    header("location:error-03.php");
   }
 }
 mysqli_close($conexion);
?>

       </div>
       </div>
       </div>
       </div>
       </div>         
<?php include("includes/foot.php"); ?>  
</body>

</html>