<?php 
	require "init.php";

	$sql_query = "SELECT * FROM newsurls WHERE visitada = 1;";

	if($result = mysqli_query($con,$sql_query)){
	   $posts = array();
	   while ($post = mysqli_fetch_assoc($result)) {
	       $posts[] = $post;
	   }
		echo json_encode($posts, JSON_PRETTY_PRINT); // get result in json format.    
	   	json_last_error_msg();
	}
	else{
		echo "Data insertion error." .mysqli_error($con);
	}

?>
