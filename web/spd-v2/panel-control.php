<?php
session_start();

if (isset($_SESSION['loggedin']) && $_SESSION['loggedin'] == true && $_SESSION['tipo'] != "admin"){
    
}
else{
    header("location:error-01.php");

    exit;
}

$now = time();

if($now > $_SESSION['expire']){
    session_destroy();
    header("location:error-02.php");
    exit;
}
?>



    <!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="main.css" type="text/css">
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
  <div class="py-5 m-0">
    <div class="container">
    <br><br><br><br><br><br>
      <div class="row">
        <div class="col-md-12 bg-light">
            <div class="py-5 bg-light">
                <div class="form-group">
    
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
        $consultar = "SELECT USERNAME_CL, EMAIL_CL, NOMBRE_CL,AP_CL,AM_CL,TEL_CL,MUN_CL FROM clientes WHERE USERNAME_CL LIKE '$nombre'";
            
            # my sqli_query - Realiza una consulta a a base de datos
            mysqli_set_charset($conectar,'utf-8');
            if ($resultado = mysqli_query($conectar, $consultar))
            {
                # mysqli_fetch_row - Obtener una fila de resultados como un array enumerado
               
                while($fila=mysqli_fetch_row($resultado)){
                    printf("
                    <center>
                
                  <div class='form-group'>
                  <h1>Datos del usuario</h1>
                    <h1 class='title'>Nombre de usuario: %s</h1>
                    
                  </div>
                  
                  <div class='form-group'>
                    <label class='title'>Email</label> <a id='ed' href='form-edit-email.php'>Editar</a>
                    <br><p id='data'> %s </p>
                  </div>
                  <div class='form-group'>
                    <label class='title'>Contraseña</label> <a id='ed' href='form-edit-pass.php'>Editar</a>
                    <br> <p id='data'> *********** </p>
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
                    </center>
                 ",
                  $fila[0],
                  $fila[1],
                  $fila[2],            
                  $fila[3],
                  $fila[4],
                  $fila[5],
                  $fila[6]);
                }
                mysqli_free_result($resultado);
            }
        
        mysqli_close($conectar);
                     
        ?>
    </div>
    </div>
  </div>
</div>
</div>
</div>
</div>
<?php include("includes/foot.php"); ?>
</body>

</html>
    