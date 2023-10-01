package com.iloveyou;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;

import com.iloveyou.controller.AccountController;
import com.iloveyou.entity.Account;
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
		Assert.isTrue(accController != null, "Something went wrong");
	}

    @Test
    public void CreateAccountTest() throws Exception {
        MvcResult result = this.mvc.perform(
            post("/accounts/add")
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
 
        ).andExpect(status().isOk()).andReturn();
        var temp2 = accController.getAccount(23);
        MvcResult result2 = this.mvc.perform(get("/accounts")).andExpect(status().isOk()).andReturn();
        String content = result2.getResponse().getContentAsString();
        boolean temp = true;
    }

    @Test
    public void getAllAccountsTest() throws Exception {
        accController.createAccount(new Account((long)123, "Test", "Test", "Test@email.com", "Password", false));
        MvcResult result = this.mvc.perform(get("/accounts/")).andExpect(status().isOk()).andReturn();
        String content = result.getResponse().getContentAsString();
        boolean temp = true;
    }
}
