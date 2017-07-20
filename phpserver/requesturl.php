<?php 
	require "init.php";

	$sql_query = "SELECT * FROM newsurls WHERE visitada = 0 limit 1;";

	if($result = mysqli_query($con,$sql_query)){
		$post = mysqli_fetch_assoc($result);
		echo json_encode($post, JSON_PRETTY_PRINT); // get result in json format.    
	   	json_last_error_msg();
	}
	else{
		echo "Data insertion error." .mysqli_error($con);
	}

?>
