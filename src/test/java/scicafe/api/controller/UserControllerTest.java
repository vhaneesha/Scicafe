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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:scicafe-servlet.xml",
    "classpath:applicationContext.xml" })
class UserControllerTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeClass
    void setup()
    {
        this.mockMvc = MockMvcBuilders.webAppContextSetup( wac ).build();
    }

    @Test
    void getUsers() throws Exception
    {
        this.mockMvc.perform( get( "/users/" ) ).andDo(MockMvcResultHandlers.print())
            .andExpect( status().isOk() );
    }
    
    @Test
    void addUser() throws Exception
    {
        this.mockMvc
            .perform( post( "/users" ).contentType( "application/json" )
                .content( "{\"fname\":\"Mia\",\"lname\":\"Rose\", \"position\":\"student\", \"organisation\":\"Undeclared\", \"username\":\"mrose\", \"password\":\"xyz123\"}" ) ).andDo(MockMvcResultHandlers.print()).andExpect( status().is4xxClientError() );
    }
    
    @Test
    @Rollback(false)
    void addUser2() throws Exception
    {
        this.mockMvc
            .perform( post( "/users" ).contentType( "application/json" )
                .content( "{\"fname\":\"Haneesha\", \"lname\":\"Vallamsetti\", \"position\":\"student\", \"organisation\":\"Computer Science\",\"username\":\"hvallam\", \"password\":\"password\", \"email\":\"scula@gmail.com\"}" ) ).andDo(MockMvcResultHandlers.print()).andExpect( status().is2xxSuccessful() );
    }
}
