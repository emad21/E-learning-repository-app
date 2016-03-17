<?php
$host="10.101.237.101"; 
$username="mad"; //replace with database mad
$password="mad71"; //replace with database password mad71
$db_name="mad_tr"; //replace with database name mad_urname
$con=mysql_connect("$host", "$username", "$password")or die("cannot connect");
mysql_select_db("$db_name")or die("cannot select DB");

$staff_id  =  $_POST["login_name"];
$p_pass =$_POST["login_pass"];

$sql = "select courseid from Student_info
WHERE staffid like '$staff_id' 
AND password like '$p_pass';";

$result = mysql_query($sql);
$json = array();
if(mysql_num_rows($result)){
while($row=mysql_fetch_assoc($result)){
$json['User_info'][]=$row;
}
}
mysql_close($con);
echo json_encode($json);
?>
