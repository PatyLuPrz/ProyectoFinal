<?php
session_start();
?>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="main.css" type="text/css"> 
  <style>
        #titulo{
            color: white;
        }
    </style>
</head>

<body class="" style="background-image: url('img/FondoPrograma.png');background-repeat:no-repeat;">
<?php include("includes/nav.php"); ?>
<?php
            try
                {
                #Las conexiones se establecen creando instancias de la clase base PDO. El constructr acepta parametros para especificar el origen de la base de datos (concido como DSN) y, opcionalmente, el nombre de usuario y la contraseña (si la hubiera).
                $conMySQL = new PDO("mysql:host=localhost; port=3306; dbname=storephonedoctor","root","");
                # PDO::setAttribute - Establece un atributo.
                $conMySQL->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
                $conMySQL->exec("SET CHARACTER SET UTF8");
                $sentenciaSQL = "SELECT * FROM clientes WHERE USERNAME_CL = :login AND CONTRASENA_CL = :passw";
                # PDO::prepare - Prepara una sentencia para su ejecucion y devuelve un objeto sentencia.
                $sentenciaPrep = $conMySQL->prepare($sentenciaSQL);
                # htmlspecialchars - convierte caracteres especiales en  entidades HTML.
                # addslashes - Escapa un string con barras invertidas.
                $username = $_POST['USERNAME_CL'];
                $password = $_POST['CONTRASENA_CL'];
                $hash = sha1($password);
                # PDOStatement:execute - Ejecuta una sentencia preparada.
                $sentenciaPrep->execute(array(":login"=>$username, "passw"=>$hash));
                # PDOStatement::rowCount - Devuelve el numero de filas afectadas por la ultima sentencia SQL.
                $numRegistros = $sentenciaPrep->rowCount();
                if ($numRegistros !=0)
                {
                    session_start();
                    $_SESSION['loggedin'] = true;
                    $_SESSION['USERNAME_CL'] = $username;
                    $_SESSION['start'] = time();
                    $_SESSION['expire'] = $_SESSION['start'] + (20 * 5);
                    echo "<br><br><br><br><br><br>";
                    echo "<h1 id='titulo'>Bienvenido! " . $_SESSION['USERNAME_CL']."</h1>";
                    echo "<br><br><a href='panel-control.php'>Panel de Control</a>"; 

                }
                else
                {
                    #header - Enviar sin format HTTP.
                    header("Location:login.php");
                }
            }
        catch(Exception $e)
        {
            #die o exit - Imprime un mensaje y termina el script anual
            die("Error: " . $e->getMessage());
        }
        finally{    $conMySQL = null;   }
        ?>



<?php include("includes/foot.php"); ?>

</body>
</html>