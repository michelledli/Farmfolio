package com.iloveyou;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;

import com.iloveyou.controller.AccountController;
import com.iloveyou.repository.AccountRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AccountControllerTests {
    
    private MockMvc mvc;

    @Mock
    private AccountRepository accountRepo;

    @InjectMocks
    private AccountController accController;

    @BeforeEach
    public void testInit() {
        mvc = MockMvcBuilders.standaloneSetup(accController)
                .build();
    }

    @Test
	public void contextLoads() throws Exception {
		Assert.isTrue(accController != null, "Something went wrong with the Account Controller");
	}

    @Test
    void shouldCreateMockMvc() {
        assertNotNull(mvc, "MockMvc was not built");
    }

    @Test
    public void CreateAccountTest() throws Exception {
        MvcResult result = this.mvc.perform(
            post("/accounts/")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .content(
                "{\"id\":23," +
                "\"fname\":\"Test\"," +
                "\"lname\":\"Test2\"," +
                "\"email\":\"Test@gmail.com\"," +
                "\"password\":\"Test222\"," +
                "\"admin\":false}"
            )
        ).andReturn();

        Assert.isTrue(result.getResponse().getStatus() == 200, "POST request /add was not fulfilled. Status Code: " + result.getResponse().getStatus());
    }

    @Test
    public void accountsearchTest() throws Exception {
        MvcResult result = this.mvc.perform(get("/accounts/search").param("query", "test")).andReturn();
        Assert.isTrue(result.getResponse().getStatus() == 200, "GET request /accounts/search was not fulfilled. Status Code: " + result.getResponse().getStatus());
    }

    @Test
    public void getById() throws Exception {
        MvcResult result = this.mvc.perform(get("/accounts/23")).andReturn();
        // If the account is not found, it will return a bad request error 400.
        Assert.isTrue(result.getResponse().getStatus() == 400, "GET request /accounts/{id} was not fulfilled. Status Code: " + result.getResponse().getStatus());
    }

    @Test
    public void deleteById() throws Exception {
        MvcResult result = this.mvc.perform(delete("/accounts/23")).andReturn();
        Assert.isTrue(result.getResponse().getStatus() == 200, "Delete request /accounts/{Id} was not fulfilled. Status Code: " + result.getResponse().getStatus());
    }
}
