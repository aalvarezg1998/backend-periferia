package com.aalvarezg.ms_publications.application.handler;

import com.aalvarezg.ms_publications.application.dto.PostRequestDTO;
import com.aalvarezg.ms_publications.application.dto.PostResponseDTO;
import com.aalvarezg.ms_publications.application.dto.PostResponsePaginationDTO;
import com.aalvarezg.ms_publications.application.mapper.PostMapper;
import com.aalvarezg.ms_publications.domain.api.ILikeServicePort;
import com.aalvarezg.ms_publications.domain.api.IPostServicePort;
import com.aalvarezg.ms_publications.domain.model.Like;
import com.aalvarezg.ms_publications.domain.model.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class PostHandlerTest {

    @Mock
    private IPostServicePort postServicePort;

    @Mock
    private ILikeServicePort likeServicePort;

    @InjectMocks
    private PostHandler postHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void postCreated_ShouldReturnPostResponseDTO() {
        // Arrange
        PostRequestDTO postRequestDTO = new PostRequestDTO();
        Post post = new Post();
        PostResponseDTO postResponseDTO = new PostResponseDTO();

        when(postServicePort.savePost(any(Post.class))).thenReturn(post);
        when(likeServicePort.getLikesByPost(anyLong())).thenReturn(Collections.emptyList());

        // Act
        PostResponseDTO result = postHandler.postCreated(postRequestDTO);

        // Assert
        assertEquals(postResponseDTO.getPostId(), result.getPostId());
        verify(postServicePort, times(1)).savePost(any(Post.class));
    }

    @Test
    void getPost_ShouldReturnPostResponsePaginationDTO() {
        // Arrange
        Post post = new Post();
        post.setId(1L);

        List<Post> postList = Collections.singletonList(post);
        Page<Post> postPage = new PageImpl<>(postList, PageRequest.of(0, 10), 1);

        Like like = new Like();
        like.setPostId(1L);
        List<Like> likes = Collections.singletonList(like);

        when(postServicePort.getPost(anyInt(), anyInt())).thenReturn(postPage);
        when(likeServicePort.getLikesByPost(anyLong())).thenReturn(likes);

        // Act
        PostResponsePaginationDTO result = postHandler.getPost(0, 10);

        // Assert
        assertEquals(1, result.getTotalPages());
        assertEquals(1, result.getData().size());
        verify(postServicePort, times(1)).getPost(anyInt(), anyInt());
        verify(likeServicePort, times(1)).getLikesByPost(anyLong());
    }
}
