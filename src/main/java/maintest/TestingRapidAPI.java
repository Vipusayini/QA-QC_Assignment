package maintest;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

/*
given ()
	content type, set cookies, add auth, add param, set headers info etc....
	
when ()
	get, post, put, delete

then()
	validate status code, extract response, extract headers cookies & response body....
 */

public class TestingRapidAPI {
	
	String key = "1c54e51301msh60f7fd5e52e4e29p11cf81jsndc7e7c7bc3d3";
	String host =  "fresh-linkedin-profile-data.p.rapidapi.com";
	
	String rootURLPath = "https://fresh-linkedin-profile-data.p.rapidapi.com/get-linkedin-profile";

	@Test
	void test_response_status_code_200_TC_01() 
	{
		given().
			//Parameters
			param("linkedin_url", "https://www.linkedin.com/in/cjfollini/").
			// Headers
			header("x-rapidapi-key", key).
			header("x-rapidapi-host",host).
        when().
        	get(rootURLPath).
        then().
        	statusCode(200);	
	}
	
	@Test
	void test_API_return_skills_if_set_true_TC_02() 
	{
		given().
			//Parameters
			param("linkedin_url", "https://www.linkedin.com/in/cjfollini/").
			param("include_skills", "true").
			// Headers
			header("x-rapidapi-key", key).
			header("x-rapidapi-host", host).
        when().
        	get(rootURLPath).
        then().
        	statusCode(200).
        	body("data.skills", notNullValue());
	}
	
	@Test
	void test_API_Not_return_skills_if_set_false_TC_02() 
	{
		given().
			//Parameters
			param("linkedin_url", "https://www.linkedin.com/in/cjfollini/").
			param("include_skills", "false").
			// Headers
			header("x-rapidapi-key", key).
			header("x-rapidapi-host", host).
        when().
        	get(rootURLPath).
        then().
        	statusCode(200).
        	body("data.skills", nullValue());
	}
	
	@Test
	void test_profile_id_match() 
	{
		given().
			//Parameters
			param("linkedin_url", "https://www.linkedin.com/in/cjfollini/").
			param("include_skills", "true").
			// Headers
			header("x-rapidapi-key", key).
			header("x-rapidapi-host", host).
        when().
        	get(rootURLPath).
        then().
        	statusCode(200).
        	body("data.profile_id", equalTo("26902278"));
	}
}
