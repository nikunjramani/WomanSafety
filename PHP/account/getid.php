<?php 
include('../db_helper.php');
$email=$_GET['email'];
$id=mysqli_query($cn,"SELECT id FROM `client_details` WHERE `email`='$email'");
$row = mysqli_fetch_array($id);
$cid=$row["id"];
$sql = "SELECT * FROM `client_data` WHERE `cid`='$cid'";
$result = mysqli_query($cn, $sql);  
$row = $result->fetch_all(MYSQLI_ASSOC);
echo json_encode($row);
mysqli_close($cn);