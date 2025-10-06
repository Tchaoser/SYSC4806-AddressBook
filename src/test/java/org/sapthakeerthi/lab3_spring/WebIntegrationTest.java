package org.sapthakeerthi.lab3_spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    //test get all buddies in addressbook 1
    @Test
    void testGetAllBuddies() {
        ResponseEntity<String> response = this.restTemplate.getForEntity("/addressBooks/1/buddyList", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("Sam") && response.getBody().contains("Steve Rogers"), "Response should have sam and steve rogers");
    }

    //check if existing addressbooks exists
    @Test
    void testGetAddressBooks() {
        ResponseEntity<String> response = this.restTemplate.getForEntity("/addressBooks", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("addressBooks"), "Response should have addressBooks");
        assertTrue(response.getBody().contains("addressBooks/1"), "Response should have addressBook 1");
    }

    //adds new buddy
    @Test
    void testAddBuddy() {
        String newBuddy = """
                {
                  "buddyName": "Pikachu",
                  "phoneNumber": "1997545"
                }
                """;


//        ### new buddy
//        POST http://localhost:8080/buddyInfoes
//        Content-Type: application/json
//
//        {
//            "buddyName": "Pikachu",
//                "phoneNumber": "1997545"
//        }

        // basically saying this: Content-Type: application/json
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(newBuddy, headers);

        //post it to buddyinfoes repo
        ResponseEntity<String> response = restTemplate.postForEntity("/buddyInfoes", request, String.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode(), "Buddy should be created successfully");
        assertTrue(response.getBody().contains("Pikachu"), "Response should contain Pikachu");
        //then you attach it
//        ### attaching it
//        POST http://localhost:8080/addressBooks/1/buddyList
//        Content-Type: text/uri-list
        //get location url of new buddy http://localhost:8080/buddyInfoes/3
        String buddyUri = response.getHeaders().getLocation().toString();
        assertNotNull(buddyUri, "Created buddy should return a Location header");
        //Content-Type: text/uri-list
        HttpHeaders uriHeaders = new HttpHeaders();
        uriHeaders.setContentType(MediaType.parseMediaType("text/uri-list"));
        HttpEntity<String> attachRequest = new HttpEntity<>(buddyUri, uriHeaders);
        //attach it
        ResponseEntity<String> attachResponse = restTemplate.postForEntity("/addressBooks/1/buddyList", attachRequest, String.class);
        assertEquals(HttpStatus.NO_CONTENT, attachResponse.getStatusCode(), "Buddy pikachu seems now to be attached");

        //verify
        ResponseEntity<String> verifyResponse = restTemplate.getForEntity("/addressBooks/1/buddyList", String.class);
        assertEquals(HttpStatus.OK, verifyResponse.getStatusCode());
        assertTrue(verifyResponse.getBody().contains("Pikachu"), "AddressBook #1 should now contain Pikachu");
    }

    //adds new buddy and deletes it
    @Test
    void testDeleteBuddy() {
        String newBuddy = """
                {
                  "buddyName": "Charmander",
                  "phoneNumber": "199683734"
                }
                """;


        // basically saying this: Content-Type: application/json
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(newBuddy, headers);

        //post it to buddyinfoes repo
        ResponseEntity<String> response = restTemplate.postForEntity("/buddyInfoes", request, String.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode(), "Buddy should be created successfully");
        assertTrue(response.getBody().contains("Charmander"), "Response should contain Charmander");
        //then you attach it
//        ### attaching it
//        POST http://localhost:8080/addressBooks/1/buddyList
//        Content-Type: text/uri-list
        //get location url of new buddy http://localhost:8080/buddyInfoes/3
        String buddyUri = response.getHeaders().getLocation().toString();
        assertNotNull(buddyUri, "Created buddy should return a Location header");
        //Content-Type: text/uri-list
        HttpHeaders uriHeaders = new HttpHeaders();
        uriHeaders.setContentType(MediaType.parseMediaType("text/uri-list"));
        HttpEntity<String> attachRequest = new HttpEntity<>(buddyUri, uriHeaders);
        //attach it
        ResponseEntity<String> attachResponse = restTemplate.postForEntity("/addressBooks/1/buddyList", attachRequest, String.class);
        assertEquals(HttpStatus.NO_CONTENT, attachResponse.getStatusCode(), "Buddy Charmander seems now to be attached");

        //verify
        ResponseEntity<String> verifyResponse = restTemplate.getForEntity("/addressBooks/1/buddyList", String.class);
        assertEquals(HttpStatus.OK, verifyResponse.getStatusCode());
        assertTrue(verifyResponse.getBody().contains("Charmander"), "AddressBook #1 should now contain Charmander");

        //now to delete charmander
        restTemplate.delete("/addressBooks/1/buddyList/3");

        //check if gotten rid fo charmander
        ResponseEntity<String> verifyAfterDelete = restTemplate.getForEntity("/addressBooks/1/buddyList", String.class);
        assertFalse(verifyAfterDelete.getBody().contains("Charmander"), "Charmander should be deleted from AddressBook #1");
    }
}
