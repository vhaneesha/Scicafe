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
class EventControllerTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeClass
    void setup()
    {
        this.mockMvc = MockMvcBuilders.webAppContextSetup( wac ).build();
    }
    
    //if submitter is isEventOrganizer automatically approved when creating event
   @Test
    @Rollback(false)
    void createEvent() throws Exception{
    	this.mockMvc.perform(post("/events/submitter/2").contentType("application/json").content("{\"name\":\"EPIC\",\"description\":\"Center for Engagement, Service, and the Public Good Volunteer\",\"location\":\"Ground floor Career center\",\"starttime\": \"12-03-2018\", \"endtime\":\"14-05-2018\"}")).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
    }
    
    @Test
    @Rollback(false)
    void arEvent() throws Exception{
    	this.mockMvc.perform(MockMvcRequestBuilders.patch("/events/3/approvedby/4")).andDo(MockMvcResultHandlers.print()).andExpect( status().is4xxClientError() );
    }
    
    @Test
    @Rollback(false)
    void arEvent2() throws Exception{
    	this.mockMvc.perform(MockMvcRequestBuilders.patch("/events/2/approvedby/2")).andDo(MockMvcResultHandlers.print()).andExpect( status().isOk() );
    }
    
    @Test
    @Rollback(false)
    void addAttEvent() throws Exception{
    	this.mockMvc.perform(post("/events/2/addattendee/3").contentType("application/json")).andDo(MockMvcResultHandlers.print()).andExpect(status().is4xxClientError());
    }
   @Test
    void getAttEvent() throws Exception{
	   
    	this.mockMvc.perform(get("/events/1/attendees").contentType("application/json")).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
    }
    
}
    