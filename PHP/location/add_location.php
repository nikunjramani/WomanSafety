<?php
	include('../db_helper.php');
	$cid=$_POST['cid'];
	$latitude=$_POST['latitude'];
	$longitude=$_POST['longitude'];
	$date=date("Y-m-d H:i:s");
	$sql = "INSERT INTO `location` (`id`, `cid`, `latitude`, `longitute`,`datetime`) VALUES (NULL, '$cid', '$latitude', '$longitude','$date')";
	mysqli_query($cn,$sql);
	if(mysqli_affected_rows($cn)>0)
	{
	    echo "1";   
	}
	else
	{
	    echo "-1";
	}
	mysqli_close($cn);
?>