<?php 
    define('USER', 'kos003');
    define('PWD', 'wuoc');
    define('DB', 'dk205_u4z17');

    try {
        $dbh = new PDO("mysql:host=$localhost;dbname=dk205_u4z17", USER, PWD);
        $dbh-> setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        echo "Connected";
    }catch (Exception $e){
        echo "Unable to connect: " . $e->getMessage() . "<br/>";
    }
?>