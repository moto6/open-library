package io.openlibrary.connect;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(BookController.class)
class BookControllerTest {

    private final String BASE_URL = "http://localhost/api/v0/book";
    @Autowired
    private MockMvc mockMvc;

    @DisplayName("default검색")
    @Test
    public void defaultfff() throws Exception {
        //todo #201 - bookMaster 리턴해줘야함. 왜냐하면 고객들은 장서를 찾는게 아니라 책의 종류(book-master)로 인식하기 떄문
        mockMvc.perform(
                        post(BASE_URL + "/default")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @DisplayName("searchTitle")
    @Test
    public void searchTitle() throws Exception {
        //todo #202 - bookMaster 리턴해줘야함. 제목기준으로 full-text-scan
        mockMvc.perform(
                        post(BASE_URL + "/search/title")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }


    @DisplayName("")
    @Test
    public void searchAuthor() throws Exception {
        //todo #202 - bookMaster 리턴해줘야함. 저자기준으로 검색
        mockMvc.perform(
                        post(BASE_URL + "/search/author")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }


    @DisplayName("")
    @Test
    public void bookMasterCode어쩌고조회() throws Exception {
        //todo #203 - bookMasterCode 기준으로 책 세부내용 조회
        mockMvc.perform(
                        post(BASE_URL + "")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @GetMapping("")
    @DisplayName("")
    @Test
    public void isbnCode() throws Exception {
        //todo #204 - isbnCode 기준으로 책 세부내용 조회
        mockMvc.perform(
                        post(BASE_URL + "/{isbnCode}")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}