package com.varunarl.hello;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.varunarl.auth.Application;

@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class HelloWorlControllerTest {

    private MockMvc mockMvc;
    
    @Autowired
    private WebApplicationContext webAppCtx;
    
    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppCtx).build();
    }

    @Test
    public void testNoUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello").param("name", ""))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Invalid username"))
                .andDo(print());
    }

    @Test
    public void testUserSujeewa() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello").param("name", "Sujeewa"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello Sujeewa. You've been assigned with role Product Owner"))
                .andDo(print());
    }

    @Test
    public void testUserVaruna() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello").param("name", "Varuna"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello Varuna. You've been assigned with role admin"))
                .andDo(print());
    }

    @Test
    public void testDeliberateCrash() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello").param("name", "crash"))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("$.message").value("deliberate app crash"))
                .andDo(print());
    }

}
