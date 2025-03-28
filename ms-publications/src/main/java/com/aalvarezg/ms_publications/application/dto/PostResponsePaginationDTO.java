package com.aalvarezg.ms_publications.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostResponsePaginationDTO {
    private int totalPages;
    private List<PostResponseDTO> data;
}
