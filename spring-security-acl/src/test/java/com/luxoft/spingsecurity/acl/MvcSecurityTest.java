package com.luxoft.spingsecurity.acl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MvcSecurityTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void test() throws Exception {
        mvc.perform(get("/company/1001")
                        .with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk());
    }

    @Test
    public void testUnauthenticated() throws Exception {
        mvc.perform(get("/company/1001")
                        .with(anonymous()))
                .andExpect(status().is(401));
    }
    @Test
    public void testForbidden() throws Exception {
        mvc.perform(get("/company/1001")
                        .with(user("user1").roles("USER")))
                .andExpect(status().is(403));
    }
}
