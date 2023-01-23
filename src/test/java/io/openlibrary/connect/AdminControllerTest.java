package io.openlibrary.connect;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AdminController.class)
class AdminControllerTest {
    private static final String BASE_URL = "http://localhost/api/v0/admin";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("")
    void accountList() throws Exception {
        //todo #101 회원목록
        mockMvc.perform(
                        post(BASE_URL + "/default")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("")
    void accountDetail() throws Exception {
        //todo #102 회원상세조회 : 파라미터 받지말고 쿠키에 있는 토큰으로 찾아오기
        mockMvc.perform(
                        post(BASE_URL + "/default")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("")
    void books() throws Exception {
        //todo #103  //장서조회list >> 이거는 일반이용자도 쓸수있는 API 이므로 Book 에 가라
        mockMvc.perform(
                        post(BASE_URL + "/default")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("")
    void bookMasterList() throws Exception {
        //todo #104 //북마스터 조회 list
        mockMvc.perform(
                        post(BASE_URL + "/default")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("")
    void bookMasterAdd() throws Exception {
        //todo #105 //북마스터 추가
        mockMvc.perform(
                        post(BASE_URL + "/default")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("")
    void apiName() throws Exception {
        //todo #106 //북마스터 변경
        mockMvc.perform(
                        post(BASE_URL + "/default")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("")
    void importCsv() throws Exception {
        //todo #107 CSV 파일 임포트
        mockMvc.perform(
                        post(BASE_URL + "/default")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("")
    void exportCsv() throws Exception {
        //todo #108 데이터베이스 내용을 CSV 로 내려주는 기능
        mockMvc.perform(
                        post(BASE_URL + "/default")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}