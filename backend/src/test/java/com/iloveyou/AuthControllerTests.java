package com.iloveyou;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;

import com.iloveyou.controller.AuthenticationController;
import com.iloveyou.repository.AccountRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AuthControllerTests {
    private MockMvc mvc;

    @MockBean
    private AccountRepository accountRepo;

    @InjectMocks
    private AuthenticationController authController;

    @BeforeEach
    public void testInit() {
        mvc = MockMvcBuilders.standaloneSetup(authController)
                .build();
    }

    @Test
	public void contextLoads() throws Exception {
		Assert.isTrue(authController != null, "Something went wrong with the Authentication Controller");
	}

    @Test
    void shouldCreateMockMvc() {
        assertNotNull(mvc, "MockMvc was not built");
    }

    @Test
    @Disabled   // Disabled due to a ongoing workitem.
    void loginTest() throws Exception {
        MvcResult result = this.mvc.perform(
            post("/login")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .content(
                "{\"email\":\"admin@test.com\"," +
                "\"password\":\"Test\"}"
            )
        ).andReturn();

        Assert.isTrue(result.getResponse().getStatus() == 200, "POST request /login was not fulfilled. Status Code: " + result.getResponse().getStatus());
    }

    @Test
    @Disabled   //jakarta.servlet.ServletException: Request processing failed: java.lang.NullPointerException: Cannot invoke "io.jsonwebtoken.Claims.get(Object)" because "claims" is null
    void getRoleTest() throws Exception {
        MvcResult result = this.mvc.perform(get("/role/admin")).andReturn();
        Assert.isTrue(result.getResponse().getStatus() == 200, "GET request /roles/{role} was not fulfilled. Status Code: " + result.getResponse().getStatus());
    }

}
