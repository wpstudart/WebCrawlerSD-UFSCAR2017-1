<?php 
	require "init.php";

	$url = $_POST["url"];
	$newstitle  = $_POST["newstitle"];
	$categoria = $_POST["categoria"];


	$sql_query = "update newsurls set newstitle = '$newstitle', visitada = 1 WHERE url = '$url';";

	if(mysqli_query($con,$sql_query)){
		$sql_query = "insert into categorias (url, categoria) values('$url', '$categoria');";
		if(mysqli_query($con,$sql_query)){
			echo "Success";
		}
	}
	else{
		echo "Data insertion error." .mysqli_error($con);
	}

	

?>
