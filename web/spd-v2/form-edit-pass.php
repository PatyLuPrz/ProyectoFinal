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
              <form action="edit-pass.php" method="post">
                <div class="form-group">
                <h1>Datos del usuario</h1>
                  <label>Contraseña actual</label>
                  <input class="form-control" placeholder="ingresa tu contraseña actual" name="CONTRASENA_CL" required="required" type="text"> 
                  <label>Contraseña nueva</label>
                  <input class="form-control" placeholder="ingresa tu nueva contraseña" name="CONTRASENA_nueva" required="required" type="text"> 
                </div>                
                <button type="submit" class="btn mt-2 btn-outline-dark">Guardar</button>
                <a href='panel-control.php' class='btn btn-dark'>Volver sin guardar</a>
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