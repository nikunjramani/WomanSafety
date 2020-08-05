<?php
	include('../db_helper.php');
	$cid=$_GET['cid'];
	$contact1=$_GET['contact1'];
	$contact2=$_GET['contact2'];
	$contact3=$_GET['contact3'];
	$contact4=$_GET['contact4'];
	
	$sql = "INSERT INTO `client_contact` (`id`, `cid`, `contact1`, `contact2`,`contact3`, `contact4`) VALUES (NULL, '$cid', '$contact1', '$contact2', '$contact3', '$contact4');";
	mysqli_query($cn,$sql);
	 $sql1="UPDATE `client_data` SET `value` = 'true' WHERE `client_data`.`cid` = '$cid'";
	 mysqli_query($cn,$sql1);
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
