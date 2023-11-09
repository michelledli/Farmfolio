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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;

import com.iloveyou.controller.PostController;
import com.iloveyou.repository.PostRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PostControllerTests {
    private MockMvc mvc;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostController postController;

    @BeforeEach
    public void testInit() {
        mvc = MockMvcBuilders.standaloneSetup(postController)
                .build();
    }

    @Test
	public void contextLoads() throws Exception {
		Assert.isTrue(postController != null, "Something went wrong with the Post Controller");
	}

    @Test
    void shouldCreateMockMvc() {
        assertNotNull(mvc, "MockMvc was not built");
    }

    @Test
    void getAllPosts() throws Exception {
        MvcResult result = this.mvc.perform(get("/posts")).andReturn();
        Assert.isTrue(result.getResponse().getStatus() == 200, "GET request /posts was not fulfilled. Status Code: " + result.getResponse().getStatus());
    }

    // @Test
    // void getPostByIdTest() throws Exception {
    //     MvcResult result = this.mvc.perform(get("/posts/1")).andReturn();
    //     Assert.isTrue(result.getResponse().getStatus() == 200, "GET request /posts/{Id} was not fulfilled. Status Code: " + result.getResponse().getStatus());
    // }

    @Test
    public void deletePostTest() throws Exception {
        MvcResult result = this.mvc.perform(delete("/posts/23")).andReturn();
        Assert.isTrue(result.getResponse().getStatus() == 200, "GET request /posts/delete/{id} was not fulfilled. Status Code: " + result.getResponse().getStatus());
    }

    @Test
    void getAllAnnouncements() throws Exception {
        MvcResult result = this.mvc.perform(get("/posts/announcements")).andReturn();
        Assert.isTrue(result.getResponse().getStatus() == 200, "GET request /posts/announcements was not fulfilled. Status Code: " + result.getResponse().getStatus());
    }
}
