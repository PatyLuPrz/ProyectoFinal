<html>
    <head>
        
        <title> Inicio </title>
        <link rel="stylesheet" type="text/css" href="styles.css">
        <link rel="stylesheet" type="text/css" href="main.css">
        <link href="https://fonts.googleapis.com/css?family=Annie+Use+Your+Telescope|Bigelow+Rules|Boogaloo|Indie+Flower|Love+Ya+Like+A+Sister|Marcellus+SC|Nanum+Gothic|Pompiere|Rancho|Roboto+Mono|Six+Caps|Special+Elite|Squada+One|Sue+Ellen+Francisco|Tenor+Sans" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Amatic+SC|Handlee|Open+Sans+Condensed:300" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Encode+Sans+Condensed" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="your_website_domain/css_root/flaticon.css"> 
        <title> StorePhone Doctor </title>
        <meta charset="UTF-8">
        <style>
            #img_p{
                height: 50px;
                width: 50px;
            }
        </style>
 </head>
        <body>
        <a name="INICIO"></a>
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
        <?php
        $bd_host="localhost";
        $bd_user="root";
        $bd_pass="";
        $bd_name="storephonedoctor";
        $nombre = htmlspecialchars($_POST['buscar']);
        $conectar=mysqli_connect($bd_host,$bd_user,$bd_pass,$bd_name);
        if (mysqli_connect_errno())
        {
            # mysqli_connect_error - Devuelve una cadena con la descripcion del ultimo error de conexion
            printf("Fallo la contexion: %s/n", mysqli_connect_error());
            exit();
        }
        $consultar = "SELECT NOMBRE_P,MARCA_P,PRECIO_VENTA_P,IMG_P FROM productos WHERE NOMBRE_P LIKE '%$nombre%' or MARCA_P LIKE '%$nombre%'";
            
            # my sqli_query - Realiza una consulta a a base de datos
            mysqli_set_charset($conectar,'utf-8');
            if ($resultado = mysqli_query($conectar, $consultar))
            {
                # mysqli_fetch_row - Obtener una fila de resultados como un array enumerado
                printf("
                <table id='hola' align='center' border='4' bordercolor='pink' cellpadding='10' cellspacing='20'>
                <tr>
                    <td>Nombre</td>
                    <td>Marca</td>
                    <td>Precio</td>
                    <td>Imagen</td> 
                </tr>");
                while($fila=mysqli_fetch_row($resultado)){
                    printf("
                <tr>
                    <td>%s</td>
                    <td>%s</td>
                    <td>%s</td>
                    <td> <img id='img_p' src='%s'/> </td> 
                </tr>",
                      $fila[0],
                      $fila[1],
                      $fila[2],
                      $fila[3]);
                }
                printf("</table>");
                mysqli_free_result($resultado);
            }
        
        mysqli_close($conectar);
                     
        ?>
    </body>
</html>