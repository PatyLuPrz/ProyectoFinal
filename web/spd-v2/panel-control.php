<?php
session_start();

if (isset($_SESSION['loggedin']) && $_SESSION['loggedin'] == true){
    
}
else{
    echo "Esta pagina es solo para usuarios registrados.<br>";
    echo "<br><a href='login.html'>Login</a>";
    echo "<br><br><a href='index.html'>Registrarme</a>";

    exit;
}

$now = time();

if($now > $_SESSION['expire']){
    session_destroy();
    echo "Su sesion a caducado,
    <a href=login.php>Inicie sesion nuevamente</a>";
    exit;
}
?>
<!DOCTYPE html>
<html lang="en">
    <head>
        
        <link rel="stylesheet" type="text/css" href="styles.css">
        <link rel="stylesheet" type="text/css" href="main.css">
        <link href="https://fonts.googleapis.com/css?family=Annie+Use+Your+Telescope|Bigelow+Rules|Boogaloo|Indie+Flower|Love+Ya+Like+A+Sister|Marcellus+SC|Nanum+Gothic|Pompiere|Rancho|Roboto+Mono|Six+Caps|Special+Elite|Squada+One|Sue+Ellen+Francisco|Tenor+Sans" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Amatic+SC|Handlee|Open+Sans+Condensed:300" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Encode+Sans+Condensed" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="your_website_domain/css_root/flaticon.css"> 
        <meta charset="UTF-8">
        <title> Perfil de Usuario </title>
        <style>
          .title{
            font-size: 20px;
            
          }
          #data{
            font-size: 15px;
            color: crimson;
          }
          #ed{
            font-size: 10px;
            color: darkred;
            text-decoration: underline;
          }
        </style>
    </head>
    <body class="" style="background-image: url('img/FondoPrograma.png');background-repeat:no-repeat;">
<?php include("includes/nav.php"); ?>
<?php
        $bd_host="localhost";
        $bd_user="root";
        $bd_pass="";
        $bd_name="storephonedoctor";
        $nombre = $_SESSION['USERNAME_CL'];
        $conectar=mysqli_connect($bd_host,$bd_user,$bd_pass,$bd_name);
        if (mysqli_connect_errno())
        {
            # mysqli_connect_error - Devuelve una cadena con la descripcion del ultimo error de conexion
            printf("Fallo la contexion: %s/n", mysqli_connect_error());
            exit();
        }
        $consultar = "SELECT * FROM clientes WHERE USERNAME_CL LIKE '$nombre'";
            
            # my sqli_query - Realiza una consulta a a base de datos
            mysqli_set_charset($conectar,'utf-8');
            if ($resultado = mysqli_query($conectar, $consultar))
            {
                # mysqli_fetch_row - Obtener una fila de resultados como un array enumerado
               
                while($fila=mysqli_fetch_row($resultado)){
                    printf("
                    <br><br><br><br><br><br>
        <div class='col-md-6 p-0'>
          <div class='card'>
            <div class='card-body p-5>
                <h1>Datos del usuario</h1>
                  <div class='form-group'>
                    <label class='title'>Nombre de usuario</label>
                    <br> <p id='data'> %s </p>  
                  </div>
                  <div class='form-group'>
                    <label class='title'>Email</label> <a id='ed' href='form-edit-email.php'>Editar</a>
                    <br><p id='data'> %s </p>
                  </div>
                  <div class='form-group'>
                    <label>Contraseña</label> <a id='ed' href='form-edit-pass.php'>Editar</a>
                    <br> <p id='data'> %s </p>
                  </div>
                  <div class='form-group'>
                    <label class='title'>Nombre</label> <a id='ed' href='form-edit-nombre.php'>Editar</a>
                    <br><p id='data'> %s %s %s </p>
                  </div>
                  <div class='form-group'>
                    <label class='title'>Telefono</label> <a id='ed' href='form-edit-telefono.php'>Editar</a>
                    <br><p id='data'> %s </p>
                  </div>
                  <div class='form-group'>
                    <label class='title'>Municipio</label> <a id='ed' href='form-edit-municipio.php'>Editar</a>
                    <br><p id='data'> %s </p>
                  </div>
            </div>
          </div>
        </div>",
                  $fila[0],
                  $fila[1],
                  $fila[2],            
                  $fila[3],
                  $fila[4],
                  $fila[5],
                  $fila[6],
                  $fila[7]);
                }
                mysqli_free_result($resultado);
            }
        
        mysqli_close($conectar);
                     
        ?>
    


<div class="col-md-6 p-0">
          

</div>
    