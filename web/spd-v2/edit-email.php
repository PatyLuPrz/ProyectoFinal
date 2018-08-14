<?php
session_start();

if (isset($_SESSION['loggedin']) && $_SESSION['loggedin'] == true){
    
}
else{
    echo "Esta pagina es solo para usuarios registrados.<br>";
    echo "<br><a href='login.php'>Login</a>";
    echo "<br><br><a href='index.php'>Registrarme</a>";

    exit;
}

$now = time();

if($now > $_SESSION['expire']){
    session_destroy();
    echo "Su sesion a caducado,
    <a href=login.php>Inicie sesion nuevamente</a>";
    exit;
}
?>

<?php
try{

      $nombre = $_SESSION['USERNAME_CL'];
      #Las conexiones se establecen creando instancias de la clase base PDO. El constructr acepta parametros para especificar el origen de la base de datos (concido como DSN) y, opcionalmente, el nombre de usuario y la contraseña (si la hubiera).
      $conMySQL = new PDO("mysql:host=localhost; port=3306; dbname=storephonedoctor","root","");
      # PDO::setAttribute - Establece un atributo.
      $conMySQL->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
      $conMySQL->exec("SET CHARACTER SET UTF8");
      
                    $sentenciaSQL = "UPDATE clientes SET EMAIL_CL= :a WHERE USERNAME_CL = :b";
                    # PDO::prepare - Prepara una sentencia para su ejecucion y devuelve un objeto sentencia.
                    $sentenciaPrep = $conMySQL->prepare($sentenciaSQL);
                    # htmlspecialchars - convierte caracteres especiales en  entidades HTML.
                    # addslashes - Escapa un string con barras invertidas.
                    $username = $_SESSION['USERNAME_CL'];
                    $email = $_POST['EMAIL_CL'];
                    # PDOStatement:execute - Ejecuta una sentencia preparada.
                    $sentenciaPrep->execute(array(":a"=>$email, "b"=>$username));
                    # PDOStatement::rowCount - Devuelve el numero de filas afectadas por la ultima sentencia SQL.
                    $numRegistros = $sentenciaPrep->rowCount();  
                    if ($numRegistros !=0)
                    { 
                        echo "datos guardados";
                    }
                    else{
                        echo "error en el update";
                    }
            }
                catch(Exception $e)
        {
            #die o exit - Imprime un mensaje y termina el script anual
            die("Error: " . $e->getMessage());
        }
        finally{    $conMySQL = null;   }
        ?>
