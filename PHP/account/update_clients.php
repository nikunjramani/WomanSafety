<?php 
 include('../db_helper.php');
 
$id=$_POST['id']; 
$email=$_POST['email'];
$name=$_POST['name'];
$password=$_POST['password'];
$mobileno=$_POST['mobileno'];
$qry="UPDATE `client_details` SET `name` = '$name', `password` = '$password', `email` = '$email', `mobileno` = '$mobileno' WHERE `client_details`.`id` = '$id'";
mysqli_query($cn,$qry);
if(mysqli_affected_rows($cn)){
    echo "1";   
}
else{
    echo "-1";
}
mysqli_close($cn);
?>
