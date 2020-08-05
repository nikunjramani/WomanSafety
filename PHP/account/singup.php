<?php
	include('../db_helper.php');
	$name=$_POST['name'];
	$email=$_POST['email'];
	$pass=$_POST['password'];
	$mobileno=$_POST['mobileno'];
	
	$sql = "INSERT INTO `client_details` (`id`, `name`, `email`, `mobileno`, `password`) VALUES (NULL, '$name', '$email', '$mobileno', '$pass');";
	mysqli_query($cn,$sql);
	if(mysqli_affected_rows($cn)>0)
	{
	 	$id=mysqli_query($cn,"SELECT id FROM `client_details` WHERE `email`='$email'");
        $row = mysqli_fetch_array($id);
        $id=$row["id"];
        $sql="INSERT INTO `client_data` (`id`, `cid`, `name`, `value`) VALUES (NULL, $id, 'view', 'false')";
	    mysqli_query($cn,$sql);
	    echo "1";   
	}
	else
	{
	    echo "-1";
	}
	mysqli_close($cn);
?>