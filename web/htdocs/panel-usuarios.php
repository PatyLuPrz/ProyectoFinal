<?php
session_start();

if (isset($_SESSION['loggedin']) && $_SESSION['loggedin'] == true && $_SESSION['tipo'] == "admin"){
    
}
else{
    header("location:error-04.php");

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
  <link rel="stylesheet" href="main.css" type="text/css"> </head>

<body class="" style="background-image: url('img/FondoPrograma.png');background-repeat:no-repeat;">
<?php include("includes/nav-us.php"); ?>
    <div class="container">
      <div class="row">
        <div class="col-md-12">
            <div class="py-5">
            <br><br><br><br>
            <table method="GET"  bgcolor="white"  align="center" border="4" bordercolor="pink" cellpadding="5" cellspacing="10">
                <tr>
                    <th><center>Codigo</center></th>
                    <th><center>Nombre</center></th>
                    <th><center>Marca</center></th>
                    <th><center>Precio</center></th>
                    <th><center>Imagen</center></th>
                    <th><center>Descripción</center></th>
                    <th><center>Opciones</center></th>
                </tr>
                <?php
					$bd_host="localhost";
					$bd_user="root";
					$bd_pass="";
					$bd_name="storephonedoctor";
					$conectar=mysqli_connect($bd_host,$bd_user,$bd_pass,$bd_name);
					if (mysqli_connect_errno())
					{
						# mysqli_connect_error - Devuelve una cadena con la descripcion del ultimo error de conexion
						printf("Fallo la contexion: %s/n", mysqli_connect_error());
						exit();
					}

					# mysqli_set_chatser - Establece el conjunto de caracteres prederterminado del cliente


					$consultar = 'SELECT CODIGO_P,NOMBRE_P,MARCA_P,PRECIO_VENTA_P,IMG_P,DESCRIPCION_P  FROM productos';
					# my sqli_query - Realiza una consulta a a base de datos
                    mysqli_set_charset($conectar,'utf-8');
                    if ($resultado = mysqli_query($conectar, $consultar))
					{
						# mysqli_fetch_row - Obtener una fila de resultados como un array enumerado
                        while($fila=mysqli_fetch_row($resultado))
						{
							printf("
                                <tr>
                                <td><center>%s</center></td>
                                <td><center>%s</center></td>
                                <td><center>%s</center></td>
                                <td><center>%s</center></td>
                                <td><center>%s</td>
                                <td><center>%s</td>
                                <td><a href='form-edit-img.php/?buscar=%s'>Editar</a></center></td>
                                </tr>						
							",
						  $fila[0],
						  $fila[1],
						  $fila[2],            
						  $fila[3],
                          $fila[4],
                          $fila[5],
                          $fila[0],
                          $fila[0]);
						}
					mysqli_free_result($resultado);
					}

					# mysqli_close - Cierra una conexión previamente abierta a una base de datos
					mysqli_close($conectar);
				?>
                </table>     
            </div>
        </div>
      </div>
    </div>
  </div>
<?php include("includes/foot-us.php"); ?>
</body>

</html>