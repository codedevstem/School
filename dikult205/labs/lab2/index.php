<!DOCTYPE>
<html>
    <head>
        <meta charset='UTF-8'>
        <title>TestLab2</title>
    </head>
    
    <body>
        <header>
                    
        </header>
        <main>
            <?php
                include_once 'included/connect.php';
                if(!empty($_POST['username']) && !empty($_POST['age'])){
                    $name = $_POST['username'];
                    $age = $_POST['age'];
                    $query = "INSERT INTO users (user,age) VALUES ('$name', '$age')";
                    try {
                        $dbh->exec($query);
                        echo "User added";
                    }catch(Exception $e){
                        echo "User was not added" . $e->getMessage() . "<br/>";
                    }
                }

            ?>
           <form action="" method="post">
                <input type="text" name="username" placeholder="What is your name" id="usernameInput" maxlength="30">
                <input type="number" name="age" min="1" max"100" id="ageInput"><label for="ageInput">How old are you: </label>
                <input type="submit" value="Enter">
           </form> 
           <?php
            try {
                $query = "SELECT * FROM users";
                $result = $dbh->query($query);

                foreach( $result as $row ) {
                    echo "<p>";
                    echo $row['user'];
                    echo $row['age'];
                    echo "</p>";
                }

            }catch(Exception $e){
                echo "User was not added" . $e->getMessage() . "<br/>";
            }
           ?>
        </main>
        <footer>
                    
        </footer>
    </body>
</html>