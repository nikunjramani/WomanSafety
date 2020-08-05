<?php
	include('../db_helper.php');
	$cid=$_POST["cid"];
	$password=$_POST["password"];
	$sql = "UPDATE `client_details` SET `password` = '$password' WHERE `client_details`.`id` = '$cid'";
	mysqli_query($cn,$sql);
	if(mysqli_affected_rows($cn)>0){
	    echo "1";   
	}
	else{
	    echo "-1";
	}
	mysqli_close($cn);
?>