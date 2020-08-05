<?php
	include('../db_helper.php');
	$email=$_GET["email"];
	$pass=$_GET["password"];
	$sql = "SELECT * FROM `client_details` WHERE `email`='$email'AND `password`='$pass'";
	mysqli_query($cn,$sql);
	if(mysqli_affected_rows($cn)>0){
	    echo "1";   
	}
	else{
	    echo "-1";
	}

	mysqli_close($cn);
?>