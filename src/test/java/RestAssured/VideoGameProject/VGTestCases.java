package RestAssured.VideoGameProject;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

public class VGTestCases {
	
    @Test(priority = 1)
	public void test_getAllVideoGames() {
		
		given()
		.when()
		   .get("http://localhost:8080/app/videogames")
		.then()
		.log().body()
		.statusCode(200);
		
	}
    
   @Test(priority = 2)
    public void test_addVideoGame() {
    	
    	HashMap data = new HashMap();
    	data.put("id", 100);
    	data.put("name", "Khalifa_Game");
    	data.put("releaseDate", "2022-09-25T12:10:30.107Z");
    	data.put("reviewScore", 5);
    	data.put("category", "FUN");
    	data.put("rating","Universal");
    	
    	Response res = 
    	given()
    	  .contentType("application/json")
    	  .body(data)
       .when()
            .post("http://localhost:8080/app/videogames")
       .then()
             .statusCode(200)
             .log().body()
             .extract().response();
    String jsonString = res.asString();
    assertEquals(jsonString.contains("Record Added Successfully"), true);
    	 	  
    	
    }

   @Test(priority = 3)
    public void test_getVideogame() {
    	// The APIs by default give the response in XML format
    	 
    	 given()
    	.when()
    	     .get("http://localhost:8080/app/videogames/100")
    	.then()
    	.statusCode(200)
    	.body("videoGame.id", equalTo("100"))
    	.body("videoGame.name", equalTo("Khalifa_Game"));
    	
    }
    
    
   @Test(priority = 4)
    public void test_updateVideoGame() {
    	
    	HashMap data = new HashMap();
    	data.put("id", 100);
    	data.put("name", "Khalifa_Game_updated");
    	data.put("releaseDate", "2022-09-25T12:10:30.107Z");
    	data.put("reviewScore", 4);
    	data.put("category", "FUN");
    	data.put("rating","Universal");
    	
    	
    	given()
    	  .contentType("application/json")
    	  .body(data)
       .when()
            .put("http://localhost:8080/app/videogames/100")
       .then()
            .statusCode(200)
            .log().body()
    	    .body("videoGame.id", equalTo("100"))
    	    .body("videoGame.name", equalTo("Khalifa_Game_updated"));
    	
             }
   
   @Test(priority = 5) 
   public void test_deleteVideoGame() {
	
	   Response res = 
	   given()
	   .when()
	         .delete("http://localhost:8080/app/videogames/100")
	   .then()
	   .log().body()
	   .extract().response();
	   
	 String jsonString = res.asString();
	 assertEquals(jsonString.contains("Record Deleted Successfully"), true);
  }
}
