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
  <div class="py-5" style="background-image: url('../img/FondoPrograma.png');">
    <div class="container">
      <div class="row">
        <div class="col-md-3"> </div>
        <div class="col-md-6">
          <div class="card text-white p-5 m-0 bg-primary">
            <div class="card-body">
              <h1 class="mb-4">Iniciar sesión</h1>
              <form action="checklogin.php" method="post">
                <div class="form-group">
                  <label>Nombre de usuario</label>
                  <input type="text" class="form-control" placeholder="ej. Gazpacho16" name="USERNAME_CL"> </div>
                <div class="form-group">
                  <label>Contraseña</label>
                  <input type="password" class="form-control" placeholder="********" name="CONTRASENA_CL"> </div>
                <button type="submit" class="btn btn-secondary">Entrar</button>
                <a href="singup.php" class="btn btn-dark">Registrarse</a>
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