package com.backoven.catdogshelter.domain.donation.command.application.controller;

import com.backoven.catdogshelter.domain.donation.command.application.dto.CreateDonationPostRequest;
import com.backoven.catdogshelter.domain.donation.command.application.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DonationExController.class)
@AutoConfigureMockMvc(addFilters = false)
class DonationExControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DonationPostCommandService donationPostService;

    @MockitoBean
    private DonationPostCommentCommandService donationCommentService;

    @MockitoBean
    private DonationPostLikeCommandService donationLikeService;

    @MockitoBean
    private DonationPostReportCommentCommandService donationPostCommentReportService;

    @MockitoBean
    private DonationPostFileService donationPostFileService;

    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    void createDonationPost() throws Exception {
        // given
        given(donationPostService.createDonationPost(any(CreateDonationPostRequest.class), anyList()))
                .willReturn(1); // postId 가 1이라고 가정
        // when & then
        mockMvc.perform(multipart("/donation-posts")
                        .file("files", "dummy-data".getBytes()) // optional
                        .param("title", "사료 기부합니다")
                        .param("content", "강아지 사료 10kg 기부")
                        .param("headId", "1")
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk());
    }

}