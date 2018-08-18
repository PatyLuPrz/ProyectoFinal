<html>
        <head>
            <title>Cerrar Sesi&oacute;n</title>
        </head>
        <body>
            <?php
                # session_start - Iniciar una nueva sesion o reanudar la existente
                session_start();
                # session_destroy - Destruye toda la informacin registrada de una sesion
                session_destroy();
                header("Location:login.php");
            ?>
        </body>
</html>