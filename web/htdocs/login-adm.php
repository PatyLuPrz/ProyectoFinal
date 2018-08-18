<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="main.css" type="text/css"> </head>

<body class="" style="background-image: url('img/FondoPrograma.png');background-repeat:no-repeat;">
  <?php include("includes/nav.php"); ?>
  <br><br><br><br><br>
  <body class="" style="background-image: url('img/FondoPrograma.png');background-repeat:no-repeat;">
  <div class="py-5 text-white opaque-overlay">
    <div class="container">
      <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6 p-3 bg-primary" style="background-position:center top;background-size:auto;">
          <h1 class="text-gray-dark">Acceso para administradores</h1>
          <form class="" method="post" action="checklogin-adm.php">
            <div class="form-group">
              <label>Nombre de usuario</label>
              <input type="text" name="USERNAME_US" class="form-control" placeholder="Ingresa tu nombre de usuario"> </div>
            <div class="form-group">
              <label>Contraseña</label>
              <input type="password" name="CONTRASENA_US" class="form-control" placeholder="********"> </div>
            <button type="submit" class="btn btn-secondary">Ingresar</button>
          </form>
        </div>
      </div>
    </div>
  </div>


  <?php include("includes/foot.php"); ?>
</body>

</html>