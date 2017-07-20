<?php 
	require "init.php";

	$url = $_POST["url"];

	$sql_query = "insert into newsurls (url, visitada) values('$url', 0);";

	if(mysqli_query($con,$sql_query)){
		echo "Success";
	}
	else{
		echo "Data insertion error." .mysqli_error($con);
	}

	

?>
