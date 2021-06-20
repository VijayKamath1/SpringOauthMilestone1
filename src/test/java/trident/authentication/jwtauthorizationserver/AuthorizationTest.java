package trident.authentication.jwtauthorizationserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthorizationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void loginWithWrongAuthority() throws Exception {
        mvc.perform(formLogin().user("Harry").password("12345"))
                .andExpect(redirectedUrl("/"))
                .andExpect(status().isFound())
                .andExpect(authenticated());
    }


    @Test
    void generateTokenValidUserAndClientTest() throws Exception {

        mvc.perform(
                post("/oauth/token")
                        .with(httpBasic("client2", "secret2"))
                        .queryParam("grant_type", "password")
                        .queryParam("username", "Harry")
                        .queryParam("password", "12345")
                        .queryParam("scope", "read")
        )
                .andExpect(jsonPath("$.access_token").exists())
                .andExpect(status().isOk());

    }



}
