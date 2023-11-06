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

import com.iloveyou.controller.AnimalParentController;
import com.iloveyou.repository.AnimalRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AnimalParentControllerTests {
    
    private MockMvc mvc;

    @MockBean
    private AnimalRepository animalRepo;

    @InjectMocks
    private AnimalParentController annController;

    @BeforeEach
    public void testInit() {
        mvc = MockMvcBuilders.standaloneSetup(annController)
                .build();
    }

    @Test
	public void contextLoads() throws Exception {
		Assert.isTrue(annController != null, "Something went wrong with the Animal Parent Controller");
	}

    @Test
    void shouldCreateMockMvc() {
        assertNotNull(mvc, "MockMvc was not built");
    }

    @Test
    void createAnimalWithParentsTest() throws Exception {
        MvcResult result = this.mvc.perform(
            post("/parents/add")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .content(
                "{\r\n" + 
                        "  \"child\": {\r\n" + 
                        "    \"name\": \"Fluffy\",\r\n" + 
                        "    \"sex\": \"Female\",\r\n" + 
                        "    \"dob\": \"2020-01-15\",\r\n" +
                        "    \"weight\": 25,\r\n" +
                        "    \"tag\": \"1234\",\r\n" +
                        "    \"breed\": \"Goat\",\r\n" +
                        "    \"notes\": \"child\"\r\n" +
                        "  },\r\n" +
                        "  \"father\": {\r\n" +
                        "    \"name\": \"Max\",\r\n" +
                        "    \"sex\": \"Male\",\r\n" +
                        "    \"dob\": \"2018-05-10\",\r\n" +
                        "    \"weight\": 30,\r\n" +
                        "    \"tag\": \"5678\",\r\n" +
                        "    \"breed\": \"Goat\",\r\n" +
                        "    \"notes\": \"father\"\r\n" +
                        "  },\r\n" +
                        "  \"mother\": {\r\n" +
                        "    \"name\": \"Lucy\",\r\n" +
                        "    \"sex\": \"Female\",\r\n" +
                        "    \"dob\": \"2019-07-20\",\r\n" +
                        "    \"weight\": 28,\r\n" +
                        "    \"tag\": \"9876\",\r\n" +
                        "    \"breed\": \"Labrador\",\r\n" +
                        "    \"notes\": \"mother\"\r\n" +
                        "  }\r\n" +
                        "}"
            )
        ).andReturn();

        Assert.isTrue(result.getResponse().getStatus() == 200, "POST request /add was not fulfilled. Status Code: " + result.getResponse().getStatus());
    }
}