package io.openlibrary.connect;

import io.openlibrary.domain.entity.Accounts;
import io.openlibrary.service.AccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AccountController.class)
class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    private final String BASE_URL = "http://localhost/api/v0/account";

    @DisplayName("로그인 성공한다")
    @Test
    public void login() throws Exception {
        //given
        given(accountService.login("1234"))
                .willReturn(new Accounts("", "1234", ""));
        String json = "{\"iamCode\": \"1234\"}";

        //when
        //then
        mockMvc.perform(
                        post(BASE_URL + "/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                .andExpect(status().isOk())
                .andExpect(cookie().exists("AccessToken"))
                .andDo(print());
    }

    @DisplayName("로그아웃 성공한다")
    @Test
    public void logout() throws Exception {
        //given
        //when
        //then
        mockMvc.perform(
                        patch(BASE_URL + "/logout")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(cookie().maxAge("AccessToken", 0))
                .andDo(print());
    }
}