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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;

import com.iloveyou.controller.AnimalController;
import com.iloveyou.repository.AnimalRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AnimalControllerTests {
    
    private MockMvc mvc;

    @MockBean
    private AnimalRepository animalRepo;

    @InjectMocks
    private AnimalController annController;

    @BeforeEach
    public void testInit() {
        mvc = MockMvcBuilders.standaloneSetup(annController)
                .build();
    }

    @Test
	public void contextLoads() throws Exception {
		Assert.isTrue(annController != null, "Something went wrong with the Animal Controller");
	}

    @Test
    void shouldCreateMockMvc() {
        assertNotNull(mvc, "MockMvc was not built");
    }

    @Test
    public void CreateAnimalTest() throws Exception {
        MvcResult result = this.mvc.perform(
            post("/animals")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .content(
                "{\"id\":23}"
            )
        ).andReturn();

        Assert.isTrue(result.getResponse().getStatus() == 200, "POST request /animals was not fulfilled. Status Code: " + result.getResponse().getStatus());
    }

    @Test
    public void nonExistentAnimalSearchTest() throws Exception {
        MvcResult result = this.mvc.perform(get("/animals/search").param("query", "test")).andReturn();
        Assert.isTrue(result.getResponse().getStatus() == 404, "GET request /animals/search was not fulfilled. Status Code: " + result.getResponse().getStatus());
    }

    @Test
    public void getById() throws Exception {
        MvcResult result = this.mvc.perform(get("/animals/23")).andReturn();
        Assert.isTrue(result.getResponse().getStatus() == 200, "GET request /animals/{id} was not fulfilled. Status Code: " + result.getResponse().getStatus());
    }

    @Test
    public void deleteById() throws Exception {
        MvcResult result = this.mvc.perform(delete("/animals/23")).andReturn();
        Assert.isTrue(result.getResponse().getStatus() == 200, "GET request /animals/delete/{id} was not fulfilled. Status Code: " + result.getResponse().getStatus());
    }
}
