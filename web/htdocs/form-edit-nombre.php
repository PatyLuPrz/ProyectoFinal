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
              <form action="edit-nombre.php" method="post">
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
                    $consultar = "SELECT NOMBRE_CL, AP_CL, AM_CL FROM clientes WHERE USERNAME_CL LIKE '$nombre'";
                        
                        # my sqli_query - Realiza una consulta a a base de datos
                        mysqli_set_charset($conectar,'utf-8');
                        if ($resultado = mysqli_query($conectar, $consultar))
                        {
                            # mysqli_fetch_row - Obtener una fila de resultados como un array enumerado
                           
                            while($fila=mysqli_fetch_row($resultado)){
                                printf("
                                
                            <h1>Datos del usuario</h1>
                              <div class='form-group'>
                              
                                <label class='title'>Nombre actual</label>
                                <br><p id='data'> %s %s %s </p>
                                <label>Nombre nuevo</label>
                                <input class='form-control' placeholder='ingresa tu nombre' name='NOMBRE_CL' required='required' type='text' required> 
                                <label>Apellido paterno nuevo</label>
                                <input class='form-control' placeholder='ingresa tu apellido' name='AP_CL' required='required' type='text' required> 
                                <label>Apellido materno nuevo</label>
                                <input class='form-control' placeholder='ingresa tu apellido' name='AM_CL' required='required' type='text' required> 
                                </div>
                                <button type='submit' class='btn mt-2 btn-outline-dark'>Guardar</button>
                                <a href='panel-control.php' class='btn btn-dark'>Volver sin guardar</a>
                             ",
                              $fila[0],
                              $fila[1],
                              $fila[2]);
                            }
                            mysqli_free_result($resultado);
                        }
                    
                    mysqli_close($conectar);
                                 
                    ?>
              </form>
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