<?php
	include('../db_helper.php');
	$name=$_POST['name'];
	$email=$_POST['email'];
	$cid=$_POST['cid'];
	$mobileno=$_POST['mobileno'];
	$sql ="UPDATE `client_details` SET `name` = '$name', `email` = '$email', `mobileno` = '$mobileno' WHERE `client_details`.`id` = '$cid'";
	mysqli_query($cn,$sql);
	if(mysqli_affected_rows($cn)){
	    echo "1";   
	}
	else{
	    echo "-1";
	}
	mysqli_close($cn);
?>