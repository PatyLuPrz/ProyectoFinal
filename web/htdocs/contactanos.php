<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="main.css" type="text/css"> </head>

<body class="" style="background-image: url('img/FondoPrograma.png');background-repeat:no-repeat;">
  <div class="p-4">
    <div class="container">
      <div class="row"></div>
    </div>
  </div>
  <?php include("includes/nav.php") ?>
  <div class="py-5 text-white">
    <div class="container">
      <div class="row">
        <div class="col-md-6">
          <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3747.2881410071614!2d-98.37047388573907!3d20.08023842485676!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x85d0579b7c6b2579%3A0x924f2b003c23d5c7!2sLa+Fayuca!5e0!3m2!1ses-419!2smx!4v1533953552874"
            width="540" height="460" frameborder="0" style="border:0" allowfullscreen=""></iframe>
        </div>
        <div class="col-md-6">
          <h1>Contactanos</h1>
          <p>Estaremos encantados de escuchar tus opiniones, pedidos y sugerencias</p>
          <form method="post" action="https://formspree.io/storephonedoctor@gmail.com">
            <div class="form-group">
              <label for="InputName">¿Cuál es tu nombre?</label>
              <input type="text" name="asunto" class="form-control" id="InputName" placeholder="Tu nombre"> </div>
            <div class="form-group">
              <label for="InputEmail1">Correo electronico</label>
              <input type="email" name="correo" class="form-control" id="InputEmail1" placeholder="Ingresa tu correo"> </div>
            <div class="form-group">
              <label for="Textarea">Mensaje</label>
              <textarea class="form-control" name="mensaje" id="Textarea" rows="3" placeholder="Escribe aquí tu mensaje"></textarea>
            </div>
            <button type="submit" class="btn btn-secondary">Enviar</button>
          </form>
        </div>
      </div>
    </div>
  </div>
  <?php include("includes/foot.php"); ?>
</body>

</html>