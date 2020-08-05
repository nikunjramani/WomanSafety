<?php 
include('../db_helper.php');
$idd=$_GET['id'];
  $query = "SELECT * FROM `client_details` WHERE `id`='$idd'";  
      $result = mysqli_query($cn, $query);  
      	  $row = $result->fetch_all(MYSQLI_ASSOC);
echo json_encode($row);
?>
