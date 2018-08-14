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
    <a href=login.html>Inicie sesion nuevamente</a>";
    exit;
}
?>



<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="main.css" type="text/css"> </head>

<body class="" style="background-image: url('img/FondoPrograma.png');background-repeat:no-repeat;">
<?php include("includes/nav.php"); ?>
<div class="col-md-6 p-0">
          <div class="card">
            <div class="card-body p-5">
              <h3 class="pb-3">Registrate</h3>
              <form action="edit-pass.php" method="post">
                <div class="form-group">
                  <label>Contraseña actual</label>
                  <input class="form-control" placeholder="ingresa tu contraseña actual" name="CONTRASENA_CL" required="required" type="text"> 
                  <label>Contraseña nueva</label>
                  <input class="form-control" placeholder="ingresa tu nueva contraseña" name="CONTRASENA_nueva" required="required" type="text"> 
                </div>                
                <button type="submit" class="btn mt-2 btn-outline-dark">Guardar</button>
              </form>
            </div>
          </div>
        </div>
<?php include("includes/foot.php"); ?>
</body>

</html>