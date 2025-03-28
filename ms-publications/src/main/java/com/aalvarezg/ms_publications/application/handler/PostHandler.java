package com.aalvarezg.ms_publications.application.handler;

import com.aalvarezg.ms_publications.application.dto.PostRequestDTO;
import com.aalvarezg.ms_publications.application.dto.PostResponseDTO;
import com.aalvarezg.ms_publications.application.dto.PostResponsePaginationDTO;
import com.aalvarezg.ms_publications.application.mapper.PostMapper;
import com.aalvarezg.ms_publications.domain.api.ILikeServicePort;
import com.aalvarezg.ms_publications.domain.api.IPostServicePort;
import com.aalvarezg.ms_publications.domain.model.Like;
import com.aalvarezg.ms_publications.domain.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PostHandler implements IPostHandler {
    private final IPostServicePort postServicePort;
    private final ILikeServicePort likeServicePort;

    @Override
    public PostResponseDTO postCreated(PostRequestDTO postRequestDTO) {
        return PostMapper.postToPostRequest(postServicePort.savePost(PostMapper.postRequestToPost(postRequestDTO)), new ArrayList<>());
    }

    @Override
    public PostResponsePaginationDTO getPost(int page, int size) {
        PostResponsePaginationDTO postResponsePaginationDTO = new PostResponsePaginationDTO();
        Page<Post> postPage = this.postServicePort.getPost(page, size);
        postResponsePaginationDTO.setTotalPages(postPage.getTotalPages());
        postResponsePaginationDTO.setData(postPage.getContent().stream().map(post -> {
            List<Like> likes = likeServicePort.getLikesByPost(post.getId());
            return PostMapper.postToPostRequest(post, likes);
        }).collect(Collectors.toList()));
        return postResponsePaginationDTO;
    }
}
