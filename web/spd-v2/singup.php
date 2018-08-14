<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="main.css" type="text/css"> </head>

<body class="" style="background-image: url('img/FondoPrograma.png');background-repeat:no-repeat;">
<?php include("includes/nav.php"); ?>
  <div class="py-5" style="background-image: url(&quot;https://pingendo.github.io/templates/sections/assets/form_red.jpg&quot;);">
    <div class="container">
      <div class="row filter-light">
        <div class="align-self-center col-md-6 text-dark">
          <h1 class="text-center text-md-left display-4">¿Cliente frecuente?</h1>
          <p class="lead">Uno de los beneficios de ser un cliente frecuente es que podras tener acceso a precios especiales y ofertas unicas.</p>
        </div>
        <div class="col-md-6 p-0">
          <div class="card">
            <div class="card-body p-5">
              <h3 class="pb-3">Registrate</h3>
              <form action="registrar-usuario.php" method="post">
                <div class="form-group">
                  <label>Nombre de usuario</label>
                  <input class="form-control" placeholder="Gazpacho125" name="USERNAME_CL" required="required" type="text"> </div>
                <div class="form-group">
                  <label class="">Email</label>
                  <input class="form-control" placeholder="cosito@algo.com" type="email" name="EMAIL_CL" required="required"> </div>
                <div class="form-group">
                  <label>Contraseña</label>
                  <input class="form-control" placeholder="**********" type="password" required="required" name="CONTRASENA_CL"> </div>
                <div class="form-group">
                  <label>Nombre</label>
                  <input class="form-control" placeholder="Patricia" name="NOMBRE_CL" required="required" type="text">
                  <div class="form-group">
                    <label>Apellido Paterno</label>
                    <input class="form-control" placeholder="Pérez" name="AP_CL" required="required" type="text"> </div>
                </div>
                <div class="form-group">
                  <label>Apellido Materno</label>
                  <input class="form-control" placeholder="Martínez" required="required" name="AM_CL" type="text">
                  <div class="form-group">
                    <label>Telefono</label>
                    <input class="form-control" placeholder="775 132 6996" name="TEL_CL" required="required" type="number"> </div>
                  <div class="form-group">
                    <label>Municipio</label>
                    <input class="form-control" placeholder="Tulancingo" name="MUN_CL" type="text"> </div>
                </div>
                <button type="submit" class="btn mt-2 btn-outline-dark">Registrarse</button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <?php include("includes/foot.php"); ?>
</body>

</html>