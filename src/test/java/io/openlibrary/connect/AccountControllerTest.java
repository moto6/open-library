package io.openlibrary.connect;

import io.openlibrary.domain.Accounts;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AccountController.class)
class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    private final String BASE_URL = "http://localhost/api/v0/account";

    @DisplayName("wef")
    @Test
    public void run() throws Exception {
        //given
        given(accountService.login("1234"))
                .willReturn(new Accounts("","1234",""));
        String json = "{\"iamCode\": \"1234\"}";

        //when
        //then
        mockMvc.perform(
                        post(BASE_URL + "/login") //해당 url로 요청을 한다.
                                .contentType(MediaType.APPLICATION_JSON) // Json 타입으로 지정
                                .content(json))
                .andExpect(status().isOk()) // 응답 status를 ok로 테스트
                .andDo(print()); //
    }
}