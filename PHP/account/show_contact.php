<?php 
include('../db_helper.php');
$idd=$_GET['cid'];
  $query = "SELECT * FROM `client_contact` WHERE `cid`='$idd'";  
      $result = mysqli_query($cn, $query);  
      	  $row = $result->fetch_all(MYSQLI_ASSOC);
echo json_encode($row);
?>
