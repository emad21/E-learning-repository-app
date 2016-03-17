<?php

$host="10.101.237.101"; 
$db_name = "mad_tr";
$username="mad"; //replace with database mad
$password="mad71"; //replace with database password mad71
$con= mysqli_connect($host,$username,$password,$db_name)or die("cannot connect");



$staffid = $_POST["staffid"];

$courseid = $_POST["courseid"];

$email = $_POST["email"];

$password = $_POST["pass"];

$sql_query = "INSERT into Student_info values('$staffid','$courseid','$email','$password');";

if(mysqli_query($con,$sql_query)){
//echo"insert Succss";
}
else{
//echo"error".mysqli_error($con);
}

?>