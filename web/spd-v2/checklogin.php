<?php
session_start();
?>

<?php
 $host_db = "localhost";
 $user_db = "root";
 $pass_db = "";
 $db_name = "storephonedoctor";
 $tbl_name = "clientes";

$conexion = new
    mysqli($host_db, $user_db, $pass_db, $db_name);

if(mysqli_connect_errno()){
    printf("La conexion fallo: " . $conexion->connect_error);
}

$username = $_POST['USERNAME_CL'];
$password = $_POST['CONTRASENA_CL'];

$consultar = "SELECT * FROM $tbl_name WHERE USERNAME_CL = '$username'";

mysqli_set_charset($conexion,'utf-8');
if ($result = mysqli_query($conexion, $consultar))
            {
                # mysqli_fetch_row - Obtener una fila de resultados como un array enumerado
                while($fila=mysqli_fetch_row($result)){
                    $hash = sha1($password); 
                    if ($hash == $fila[2]){
                        $_SESSION['loggedin'] = true;
                        $_SESSION['USERNAME_CL'] = $username;
                        $_SESSION['start'] = time();
                        $_SESSION['expire'] = $_SESSION['start'] + (5 * 5);
                        
                        echo "Bienvenido! " . $_SESSION['USERNAME_CL'];
                        echo "<br><br><a href='panel-control.php'>Panel de Control</a>"; 

                    }
                    else{
                        
                        heading()
                    }
                }
                printf("</table>");
                printf("<center><a href='formulario.html'> Volver </a></center>");
                mysqli_free_result($result);
            }
 mysqli_close($conexion); 
 ?>