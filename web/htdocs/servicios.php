<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="main.css" type="text/css"> 
  <style>
    #img{
    height: 14em;
    widows: 14em;
    box-shadow: #000;
    margin: auto 2px;
    border-radius: 5px;
  }
  </style>
</head>
<body class="" style="background-image: url('img/FondoPrograma.png');background-repeat:no-repeat;">
  <?php include("includes/nav.php"); ?>
  <div class="p-5">
      <br><br><br>
    <div class="container">
      <div class="row">
        <div class="p-3 align-self-center col-md-6">
          <div class="card">
            <div class="card-block p-5">
              <center><h3>Soporte técnico a <br> Celulares</h3></center>
              <hr>
              <p>
                <ul>
                  <li> Actualización y reinstalacion del sistema operativo </li>
                  <li> Instalación de aplicaciones </li>
                  <li> Cambio de display </li>
                  <li> Cambio del centro de carga </li>
                  <li> Etc... </li>
                </ul>
                <br><br>
                <center><img id="img" src="img/celulares.jpg"></center>
              </p>
            </div>
          </div>
        </div>
        <div class="p-3 align-self-center col-md-6">
          <div class="card">
            <div class="card-block p-5">
            <center><h3>Soporte técnico a Computadoras</h3></center>
            <hr>
            <p>
              <ul>
                <li> Actualización y reinstalacion del sistema operativo </li>
                <li> Instalación de aplicaciones </li>
                <li> Cambio de piezas </li>
                <li> Limpieza general </li>
                <li> Etc... </li>
              </ul>
              <br><br>
                <center><img id="img" src="img/computadoras.jpg"></center>
            </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
    <?php include("includes/foot.php"); ?>
</body>

</html>