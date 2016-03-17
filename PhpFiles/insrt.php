<?php
require"s.php";

$ques1 = $_POST["question1"];
$ques2 = $_POST["question2"];
$ques3 = $_POST["question3"];

$sql_query = "INSERT into Poster_ques values('$ques1','$ques2','$ques3');";

if(mysqli_query($con,$sql_query)){
//echo"insert Succss";
}
else{
//echo"error".mysqli_error($con);
}

?>