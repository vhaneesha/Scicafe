package scicafe.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:scicafe-servlet.xml",
    "classpath:applicationContext.xml" })
class ProgramControllerTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeClass
    void setup()
    {
        this.mockMvc = MockMvcBuilders.webAppContextSetup( wac ).build();
    }
    
    @Test
	void getProgams() throws Exception{
		this.mockMvc.perform(get("/programs/")).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
	}
    
    @Test
	void getProgram() throws Exception{
		this.mockMvc.perform(get("/programs/1")).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
	}
    
    
    @Test
    @Rollback(false)
    void createProgram() throws Exception{
    	this.mockMvc.perform(post("/programs").contentType("application/json")
    			.content("{\"name\":\"LSAMP\", \"fullname\":\"Louis Stocks Alliance for Minority Participation\" ,\"description\": \"description\"}") ).andDo(MockMvcResultHandlers.print()).andExpect( status().is2xxSuccessful()); 
    }
  @Test
    @Rollback(false)
    void editProgram() throws Exception{
    	this.mockMvc.perform(MockMvcRequestBuilders.put("/programs").contentType("application/json")
    			.content("{\"id\":\"4\",\"name\":\"Some New Program\", \"fullname\":\"XYZ\" ,\"description\": \"some description\"}") ).andDo(MockMvcResultHandlers.print()).andExpect( status().is2xxSuccessful());
    	
    }
    @Test
    @Rollback(false)
    void deleteProgram() throws Exception{
    	this.mockMvc.perform(MockMvcRequestBuilders.delete("/programs/5").contentType("application/json")).andExpect(status().isOk());
    }





}