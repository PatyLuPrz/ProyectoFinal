<html>
    <head>
        
        <link rel="stylesheet" type="text/css" href="styles.css">
        <link rel="stylesheet" type="text/css" href="main.css">
        <link href="https://fonts.googleapis.com/css?family=Annie+Use+Your+Telescope|Bigelow+Rules|Boogaloo|Indie+Flower|Love+Ya+Like+A+Sister|Marcellus+SC|Nanum+Gothic|Pompiere|Rancho|Roboto+Mono|Six+Caps|Special+Elite|Squada+One|Sue+Ellen+Francisco|Tenor+Sans" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Amatic+SC|Handlee|Open+Sans+Condensed:300" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Encode+Sans+Condensed" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="your_website_domain/css_root/flaticon.css"> 
        <meta charset="UTF-8">
        <title> Productos </title>
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
    <body>
        <div class="header">
            <div class="header-2">
                <div id="h4">
                    <table>
                        <tr>
                            <td><img id="logo" src="img/logo.png" /></td>
                            <td>
                                <h1> StorePhone Doctor &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </h1>
                                <hr/>
                            </td>
                        </tr>
                    </table>
                </div>
                <div id="h5">
                    <form method="post" action="buscar.php">
                        <input id="buscar" type="Text" name="buscar" placeholder="Search" /> 
                        <a id="login" href="login.html">LOGIN</a>&nbsp;|&nbsp;<a id="login" href="singup.html">SING UP</a>
                        <br/>
                        <table id="menu">
                            <tr>
                                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="index.html"> Inicio </a>&nbsp;&nbsp;&nbsp;&nbsp; </td>
                                <td> <a href="Productos.php"> Productos </a>&nbsp;&nbsp;&nbsp;&nbsp; </td>
                                <td> <a href="AcercaDe.html"> Acerca de... </a>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                <td> <a href="Contactanos.html"> Contactanos </a></td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
        <div class="body-p">
			<div class="body-1">
                <div id="b1">
                    
                    <fieldset id="categorias">
                        <legend> Tenemos diversos tipos de accesorios para tu dispositivo </legend>
                        <ul>
                            <li> <a href=""> Micas </a>  </li>
                            <li> <a href=""> Fundas </a>  </li>
                            <li> <a href=""> Accesorios </a>  </li>
                            <li> <a href=""> Audifonos </a>  </li>
                            <li> <a href=""> Cargadores </a>  </li>
                            <li> <a href=""> Adaptadores </a>  </li>
                        </ul>
                    </fieldset>
                    <br/>
                    <fieldset id="categorias">
                        <legend>  Manejamos las marcas más reconocidas </legend>
                        <center>
                                
                                <a href=""> <img id="img-marcas" src="img/samsung-1.png"> </a>
                                <a href=""> <img id="img-marcas" src="img/apple.png"> </a>
                                <a href=""> <img id="img-marcas" src="img/htc.png"> </a>
                                <a href=""> <img id="img-marcas" src="img/lg-1.png"> </a>
                                <a href=""> <img id="img-marcas" src="img/nokia-2.png"> </a>
                                <a href=""> <img id="img-marcas" src="img/Alcatel.png"> </a>
                                <a href=""> <img id="img-marcas" src="img/huawei.png"> </a>
                                <a href=""> <img id="img-marcas" src="img/sony.png"> </a>
                                <a href=""> <img id="img-marcas" src="img/motorola.png"> </a>
                            </center>
                    </fieldset>
                </div>
            </div>
			<div class="pro_1">
				<p>
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


					$consultar = 'SELECT NOMBRE_P,MARCA_P,PRECIO_VENTA_P,IMG_P FROM productos';

					# my sqli_query - Realiza una consulta a a base de datos
					mysqli_set_charset($conectar,'utf-8');
					if ($resultado = mysqli_query($conectar, $consultar))
					{
						# mysqli_fetch_row - Obtener una fila de resultados como un array enumerado
						while($fila=mysqli_fetch_row($resultado))
						{
							printf("
								<div class='PRODUCTO'>
									<h3 id='nombre_p'> %s </h3>
									<center> <img id='img_p' src='%s'/> </center>
									Marca: %s <br/>
									Precio: $%s.00 <br/> <br/>
									<a id='SeeMore' href=''>Ver más...<a/>
								</div>
							",
						  $fila[0],
						  $fila[3],
						  $fila[1],            
						  $fila[2]);
						}
					mysqli_free_result($resultado);
					}

					# mysqli_close - Cierra una conexión previamente abierta a una base de datos
					mysqli_close($conectar);
				?>
				</p>
			</div>
		</div>
    </body>
</html>
