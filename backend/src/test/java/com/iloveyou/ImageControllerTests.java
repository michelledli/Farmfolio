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

import com.iloveyou.controller.ImageController;
import com.iloveyou.repository.ImageRepository;
import com.iloveyou.service.ImageService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ImageControllerTests {
    private MockMvc mvc;

    @MockBean
    private ImageRepository imageRepo;

    @MockBean
    ImageService imageService;

    @InjectMocks
    private ImageController imageController;

    @BeforeEach
    public void testInit() {
        mvc = MockMvcBuilders.standaloneSetup(imageController)
                .build();
    }

    @Test
	public void contextLoads() throws Exception {
		Assert.isTrue(imageController != null, "Something went wrong with the Image Controller");
	}

    @Test
    void shouldCreateMockMvc() {
        assertNotNull(mvc, "MockMvc was not built");
    }

    @Test
    void getImageTest() throws Exception {
        MvcResult result = this.mvc.perform(get("/images/2")).andReturn();
        Assert.isTrue(result.getResponse().getStatus() == 200, "GET request /images was not fulfilled. Status Code: " + result.getResponse().getStatus());
    }
}
