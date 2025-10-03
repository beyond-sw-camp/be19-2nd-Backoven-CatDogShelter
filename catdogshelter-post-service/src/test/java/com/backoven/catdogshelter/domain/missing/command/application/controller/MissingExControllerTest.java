package com.backoven.catdogshelter.domain.missing.command.application.controller;

import com.backoven.catdogshelter.domain.missing.command.application.dto.CreateMissingCommentRequest;
import com.backoven.catdogshelter.domain.missing.command.application.service.*;
import com.backoven.catdogshelter.common.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(MissingExController.class)  // 컨트롤러 단위 테스트
@AutoConfigureMockMvc(addFilters = false)
class MissingExControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private MissingPostCommandService missingPostService;

    @MockitoBean
    private MissingPostCommentCommandService missingCommentService;

    @MockitoBean
    private MissingPostLikeCommandService missingLikeService;

    @MockitoBean
    private MissingPostReportCommentCommandService missingPostCommentReportService;

    @MockitoBean
    private MissingPostFileService missingPostFileService;

    @MockitoBean
    private MissingPostReportCommandService missingPostReportService;

    @MockitoBean
    private UserRepository userRepository;

    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    void createMissingPostComment() throws Exception {
        CreateMissingCommentRequest request = new CreateMissingCommentRequest();
        request.setContent("테스트 댓글");

        when(missingCommentService.createMissingPostComment(any(CreateMissingCommentRequest.class)))
                .thenReturn(1);

        mockMvc.perform(post("/missing-posts/{id}/comments", 100)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(1));
    }
}