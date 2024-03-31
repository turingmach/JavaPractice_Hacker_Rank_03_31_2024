package com.example.demo.controller;

import com.example.demo.data.User;
import com.example.demo.data.UserV2;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @MockBean
    private UserController userController;

    @Test
    public void testAddShouldReturn201Created() throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        User newUser = new User();
        UserV2 newUserV2 = new UserV2.UserBuilder().name("").surname("").build();
//        newUser.setName("Duke");
//        newUser.setSurname(null);
         RequestEntity<User> request = RequestEntity.post(new URI("http://localhost:" + "8080"+"/users")).body(newUser);
        ResponseEntity<User> response = restTemplate.exchange(request, User.class);
        System.out.println(response.getBody());
//        assertNotNull(response.getBody());
//        assertEquals(RESPONSE_SIZE,
//                response.getHeaders().getContentLength());
//        assertEquals(RESPONSE_SIZE, response.getBody().length);
    }
}
