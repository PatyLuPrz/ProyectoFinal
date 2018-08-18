<?php
session_start();

if (isset($_SESSION['loggedin']) && $_SESSION['loggedin'] == true && $_SESSION['tipo'] == "admin"){
    
}
else{
    header("location:../error-04.php");

    exit;
}
$now = time();

if($now > $_SESSION['expire']){
    session_destroy();
    header("location:../error-02.php");
    exit;
}
?>

<?php
//Recibiremos el formulario

if( isset($_POST['btn']) ){
    
    //Recuperar los datos del archivo
    $nombre = $_FILES[ 'txtImg' ][ 'name' ];
    $tmp = $_FILES[ 'txtImg' ][ 'tmp_name' ];
    $folder = 'img';

    //movera el archivo desde el folder temporal, a una nueva ruta

    move_uploaded_file( $tmp, $folder.'/'.$nombre);

    //insertar en la base de datos
    try{
    #Las conexiones se establecen creando instancias de la clase base PDO. El constructr acepta parametros para especificar el origen de la base de datos (concido como DSN) y, opcionalmente, el nombre de usuario y la contraseña (si la hubiera).
    $conMySQL = new PDO("mysql:host=localhost; port=3306; dbname=storephonedoctor","root","");
    # PDO::setAttribute - Establece un atributo.
    $conMySQL->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $conMySQL->exec("SET CHARACTER SET UTF8");
    
                  $sentenciaSQL = "UPDATE productos SET IMG_P= :a, DESCRIPCION_P= :c WHERE CODIGO_P = :b";
                  # PDO::prepare - Prepara una sentencia para su ejecucion y devuelve un objeto sentencia.
                  $sentenciaPrep = $conMySQL->prepare($sentenciaSQL);
                  # htmlspecialchars - convierte caracteres especiales en  entidades HTML.
                  # addslashes - Escapa un string con barras invertidas.
                  $codigo = $_GET['buscar'];
                  $desc = $_POST['txtComent'];
                  $ruta = $folder."/".$nombre;
                  # PDOStatement:execute - Ejecuta una sentencia preparada.
                  $sentenciaPrep->execute(array(":a"=>$ruta,":c"=>$desc, ":b"=>$codigo));
                  # PDOStatement::rowCount - Devuelve el numero de filas afectadas por la ultima sentencia SQL.
                  $numRegistros = $sentenciaPrep->rowCount();  
                  if ($numRegistros !=0)
                  { 
                      header("location:../registro-exitoso-us.php");
                  }
                  else{
                      header("location:../error-03.php");
                  }
          }
              catch(Exception $e)
      {
          #die o exit - Imprime un mensaje y termina el script anual
          die("Error: " . $e->getMessage());
      }
      finally{    $conMySQL = null;   }



}

?>


<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="../main.css" type="text/css">
    </head>

<body class="" style="background-image: url('img/FondoPrograma.png');background-repeat:no-repeat;">
  <?php include("includes/nav-us.php"); ?>
  <div class="py-5 m-0">
      <br><br><br>
    <div class="container">
      <div class="row">
        <div class="col-md-12 bg-light">
            <div class="py-5 bg-light">
                <br>
                <center>
                
                <form method='POST' action="" enctype="multipart/form-data">
                <h1>Datos del usuario</h1>
                    <label>Ingresar comentario</label>
                    <input type='text' name='txtComent' />
                    <br><br>
                    <label>Ingresar imagen</label>
                    <input type='file' name='txtImg' />
                    <br>
                    <button type='submit' name='btn'>Agregar</button>
                </form>                
                </center>
            </div>
        </div>
      </div>
    </div>
  </div>
    <?php include("includes/foot-us.php"); ?>
</body>

</html>


