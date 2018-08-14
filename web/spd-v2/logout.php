<?php
    $_SESSION['loggedin'] = false;
    session_start();
    session_destroy();
    header('Location: http://localhost/storephonedoctor/index.html');
?>