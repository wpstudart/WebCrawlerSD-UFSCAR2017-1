<?php 
	require "init.php";

	$url = $_POST["url"];
	$keyword  = $_POST["keyword"];


	$sql_query = "update newsurls set keyword = '$keyword', visitada = 1 WHERE url = '$url';";

	if(mysqli_query($con,$sql_query)){
		echo "Success";
	}
	else{
		echo "Data insertion error." .mysqli_error($con);
	}

	

?>