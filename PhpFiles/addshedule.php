<?php
$host="10.101.237.101"; 
$db_name = "mad_tr";
$username="mad"; //replace with database mad
$password="mad71"; //replace with database password mad71
$con= mysqli_connect($host,$username,$password,$db_name)or die("cannot connect");

$courseid = $_POST["courseid"];

$task = $_POST["task"];

$chapter = $_POST["chapter"];

$date = $_POST["date"];

$sql_query = "INSERT into student_info values('$courseid','$task','$chapter','$date');";

if(mysqli_query($con,$sql_query)){
//echo"insert Succss";
}
else{
//echo"error".mysqli_error($con);
}



?>