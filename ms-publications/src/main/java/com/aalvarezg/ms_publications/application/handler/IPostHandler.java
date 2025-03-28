package com.aalvarezg.ms_publications.application.handler;

import com.aalvarezg.ms_publications.application.dto.PostRequestDTO;
import com.aalvarezg.ms_publications.application.dto.PostResponseDTO;
import com.aalvarezg.ms_publications.application.dto.PostResponsePaginationDTO;

public interface IPostHandler {
    PostResponseDTO postCreated(PostRequestDTO postRequestDTO);
    PostResponsePaginationDTO getPost(int page, int size);
}
