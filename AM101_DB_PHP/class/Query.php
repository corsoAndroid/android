<?php
/*
* Mys
*/
require(__DIR__ . "/DB.php");
//require(__DIR__ . "/JSON.php");

class Query extends DB {
    
    public static function run($sql) {
        // reset json response
        JSON::$response = array();
        return parent::getInstance()->getConnection()->query($sql);
    }
    
    public static function run_json($sql) {
        
        $rows = array();
        $result = parent::getInstance()->getConnection()->query($sql);
        
        // reset json response
        JSON::$response = array();

        // If query failed, return `false`
        if($result === false) {
            JSON::$response[] = array("sql"=>"nok");
            return;
        }
        // If query was successful, retrieve all the rows into an array
        JSON::$response[] = array("sql"=>"ok");
        while ($row = $result->fetch_assoc()) {
            // echoing JSON response
            JSON::$response[] = $row;
        }
    return;
    }
}

// TEST
// Query::run_json("SELECT name, description FROM Products;");
// print json_encode(JSON::$response);

?>
