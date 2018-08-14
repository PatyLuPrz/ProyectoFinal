<!DOCTYPE html>
<html>

<head>
    <style>
            .pro_1{
                margin: 10px;
				padding: 5px;
            }
            #img_p{
                height: 350px;
                width: 350px;
                box-shadow: 0 0 20px 2px gray;
                border-radius: 5px;
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
        .img{
            float: left;
            margin-right: 2em;
            margin-bottom: 2em;
        }
				
        </style>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="../main.css" type="text/css">
        

    
    </head>

<body class="" style="background-image: url('../img/FondoPrograma.png');background-repeat:no-repeat;">
  <?php include("includes/nav.php"); ?>
  <div class="py-5 m-0">
      <br><br><br>
    <div class="container">
      <div class="row">
        <div class="col-md-12 bg-light">
            <div class="py-5 bg-light">
               
        <?php
        $bd_host="localhost";
        $bd_user="root";
        $bd_pass="";
        $bd_name="storephonedoctor";
        $nombre = $_GET['buscar'];
        $conectar=mysqli_connect($bd_host,$bd_user,$bd_pass,$bd_name);
        if (mysqli_connect_errno())
        {
            # mysqli_connect_error - Devuelve una cadena con la descripcion del ultimo error de conexion
            printf("Fallo la contexion: %s/n", mysqli_connect_error());
            exit();
        }
        $consultar = "SELECT NOMBRE_P,MARCA_P,PRECIO_VENTA_P,IMG_P, DESCRIPCION_P FROM productos WHERE CODIGO_P LIKE '$nombre'";
            
            # my sqli_query - Realiza una consulta a a base de datos
            mysqli_set_charset($conectar,'utf-8');
            if ($resultado = mysqli_query($conectar, $consultar))
            {
                # mysqli_fetch_row - Obtener una fila de resultados como un array enumerado
               
                while($fila=mysqli_fetch_row($resultado)){
                    printf("
                                <div class='view'>
                                    
                                    <h3> %s </h3><br>
                                    <div class='img'>
                                        <center> <img id='img_p' src='../%s'/> </center>
                                    </div>
                                    <h4>    Marca: %s </h4> <br>
                                    <h4>    Precio: $%s.00 </h4> <br>
                                    <h4>    Descripcion: <h4> <br>
                                    <p>     %s </p>
                                    <a href='../productos.php' class='btn btn-dark'>Volver...</a>
                                </div>
							",
						  $fila[0],
						  $fila[3],
						  $fila[1],            
						  $fila[2],
                          $fila[4]);
						}
                mysqli_free_result($resultado);
            }
        
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