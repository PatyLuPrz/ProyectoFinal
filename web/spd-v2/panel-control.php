<?php
session_start();

if (isset($_SESSION['loggedin']) && $_SESSION['loggedin'] == true){
    
}
else{
    echo "Esta pagina es solo para usuarios registrados.<br>";
    echo "<br><a href='login.html'>Login</a>";
    echo "<br><br><a href='index.html'>Registrarme</a>";

    exit;
}

$now = time();

if($now > $_SESSION['expire']){
    session_destroy();
    echo "Su sesion a caducado,
    <a href=login.html>Inicie sesion nuevamente</a>";
    exit;
}
?>
<!DOCTYPE html>
<html lang="en">
    <head>
        
        <link rel="stylesheet" type="text/css" href="styles.css">
        <link rel="stylesheet" type="text/css" href="main.css">
        <link href="https://fonts.googleapis.com/css?family=Annie+Use+Your+Telescope|Bigelow+Rules|Boogaloo|Indie+Flower|Love+Ya+Like+A+Sister|Marcellus+SC|Nanum+Gothic|Pompiere|Rancho|Roboto+Mono|Six+Caps|Special+Elite|Squada+One|Sue+Ellen+Francisco|Tenor+Sans" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Amatic+SC|Handlee|Open+Sans+Condensed:300" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Encode+Sans+Condensed" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="your_website_domain/css_root/flaticon.css"> 
        <meta charset="UTF-8">
        <title> Perfil de Usuario </title>
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
                        <a id="login" href="login.html">Cerrar sesión</a>
                        <br/>
                        <table id="menu">
                            <tr>
                                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="indexx.php"> Inicio </a>&nbsp;&nbsp;&nbsp;&nbsp; </td>
                                <td> <a href="ProductosV.php"> Productos </a>&nbsp;&nbsp;&nbsp;&nbsp; </td>
                                <td> <a href="AcercaDeV.html"> Acerca de... </a>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                <td> <a href="Contactanos.html"> Contactanos </a></td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
        <div class="controlPanel">
                        <?php
                        echo time();
                        echo '<br>';
                        echo $_SESSION['start'];
                        echo '<br>';
                        echo $_SESSION['expire'];
                        echo '<br>';
            ?>
        </div>
    