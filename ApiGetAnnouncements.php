<?php

$msg = "";

if (!isset($_GET['key']) || $_GET['key'] != 'Uggk45hcn98'){
  $msg = "key";
}

if (!empty($msg)){
  printResult(array("result_code" => 100, "result_message" => "{$msg} is requered"));
}



$res = array(
  'result_code' => 0,
  'result_message' => 'OK',
  'announcement' => array()
);

for($i = 0; $i < 10; $i++) {
  $res['announcement'][] = array('title' => "title_{$i}", 'content' => "content_{$i}", 'date' => date("Y-m-d", strtotime( $i." day")));
}

printResult($res);

function printResult($res){
  header('Content-Type: application/json');
  echo json_encode($res);
  exit;
}
