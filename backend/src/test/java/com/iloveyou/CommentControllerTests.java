package com.iloveyou;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;

import com.iloveyou.controller.CommentController;
import com.iloveyou.repository.CommentRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CommentControllerTests {
    private MockMvc mvc;

    @MockBean
    private CommentRepository imageRepo;

    @InjectMocks
    private CommentController commentController;

    @BeforeEach
    public void testInit() {
        mvc = MockMvcBuilders.standaloneSetup(commentController)
                .build();
    }

    @Test
	public void contextLoads() throws Exception {
		Assert.isTrue(commentController != null, "Something went wrong with the Image Controller");
	}

    @Test
    void shouldCreateMockMvc() {
        assertNotNull(mvc, "MockMvc was not built");
    }

    @Test
    public void getAllCommentsTest() throws Exception {
        MvcResult result = this.mvc.perform(get("/comments")).andReturn();
        Assert.isTrue(result.getResponse().getStatus() == 200, "GET request /comments was not fulfilled. Status Code: " + result.getResponse().getStatus());
        assertNotNull(result, "No content was returned");
    }

    @Test
    public void getCommentsById() throws Exception {
        MvcResult result = this.mvc.perform(get("/comments/23")).andReturn();
        Assert.isTrue(result.getResponse().getStatus() == 200, "GET request /comments/{id} was not fulfilled. Status Code: " + result.getResponse().getStatus());
    }

    @Test
    public void deleteCommentById() throws Exception {
        MvcResult result = this.mvc.perform(delete("/comments/23")).andReturn();
        Assert.isTrue(result.getResponse().getStatus() == 200, "Delete request /comments/delete/{Id} was not fulfilled. Status Code: " + result.getResponse().getStatus());
    }
}
