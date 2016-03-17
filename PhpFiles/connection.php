<?php
$host="10.101.237.101"; 
$username="mad"; //replace with database mad
$password="mad71"; //replace with database password mad71
$db_name="mad_tr"; //replace with database name mad_urname
$con=mysql_connect("$host", "$username", "$password")or die("cannot connect");



mysql_select_db("$db_name")or die("cannot select DB");


$staffid  = "11FU0K";
$password="kutta";

$sql = "select courseid from Admin_info where staffid like '$staffid' and password like '$password';";
$result = mysql_query($con,$sql);

if(mysql_num_rows($result)> 0){


$row = mysql_fetch_assoc($result);
$courseid = $row["courseid"];
echo"<h3>HELLO".$courseid."</h3>";

}
else{
echo"NOOO";
}

?>
