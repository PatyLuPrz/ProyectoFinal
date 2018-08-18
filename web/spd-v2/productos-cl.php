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
<style>
            .pro_1{
                margin: 10px;
				padding: 5px;
            }
            #img_p{
                height: 100px;
                width: 100px;
            }
            #tabla_p{
                align-content: center;
                border: solid 1px silver;
            }
			.PRODUCTO{
                background-color:cornsilk;
				margin: 10px;
				padding: 5px;
				float: left;
				height: 280px;
				width: 200px;
				border: solid silver 1px;
				border-radius: 5px;
				text-align: center;
			}
			#nombre_p{
				font-size: 20px;
				text-align: center;
			}
				
        </style>    
</head>

<body class="" style="background-image: url('img/FondoPrograma.png');background-repeat:no-repeat;">
  <?php include("includes/nav-us.php"); ?>
  <div class="py-5">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
            <div class="py-5">
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


					$consultar = 'SELECT NOMBRE_P,MARCA_P,PRECIO_VENTA_P,IMG_P, CODIGO_P FROM productos';

					# my sqli_query - Realiza una consulta a a base de datos
					mysqli_set_charset($conectar,'utf-8');
					if ($resultado = mysqli_query($conectar, $consultar))
					{
						# mysqli_fetch_row - Obtener una fila de resultados como un array enumerado
						while($fila=mysqli_fetch_row($resultado))
						{
							printf("
								<div class='PRODUCTO' method='get'>
									<h3 id='nombre_p'> %s </h3>
									<center> <img id='img_p' src='%s'/> </center>
									Marca: %s <br/>
									Precio: $%s.00  <br/> <br/>
									<a id='SeeMore' href='view.php/?buscar=%s'>Ver más...<a/>
								</div>
							",
						  $fila[0],
						  $fila[3],
						  $fila[1],            
						  $precio=$fila[2]-($fila[2]*0.20),
              $fila[4]);
						}
					mysqli_free_result($resultado);
					}

					# mysqli_close - Cierra una conexión previamente abierta a una base de datos
					mysqli_close($conectar);
				?>            
            </div>
        </div>
      </div>
    </div>
  </div>
  <?php include("includes/foot.php"); ?>
  
</body>

</html>
