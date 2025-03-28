package com.aalvarezg.ms_publications.domain.usecase;

import com.aalvarezg.ms_publications.domain.model.Post;
import com.aalvarezg.ms_publications.domain.spi.IPostPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class PostUseCaseTest {

    @Mock
    private IPostPersistencePort postPersistencePort;

    @InjectMocks
    private PostUseCase postUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void savePost_ShouldReturnSavedPost() {
        // Arrange
        Post post = new Post();
        post.setId(1L);
        post.setContent("Test Title");
        post.setUserId(1L);
        post.setUsername("test");

        when(postPersistencePort.savePost(any(Post.class))).thenReturn(post);

        // Act
        Post result = postUseCase.savePost(post);

        // Assert
        assertEquals(post, result);
        verify(postPersistencePort, times(1)).savePost(any(Post.class));
    }

    @Test
    void getById_ShouldReturnPost_WhenFound() {
        // Arrange
        Post post = new Post();
        post.setId(1L);
        post.setContent("Test Title");
        post.setUserId(1L);
        post.setUsername("test");

        when(postPersistencePort.getById(anyLong())).thenReturn(post);

        // Act
        Post result = postUseCase.getById(1L);

        // Assert
        assertEquals(post, result);
        verify(postPersistencePort, times(1)).getById(1L);
    }

    @Test
    void getPost_ShouldReturnPagedPosts() {
        // Arrange
        Post post = new Post();
        post.setId(1L);
        post.setContent("Test Title");
        post.setUserId(1L);
        post.setUsername("test");

        Page<Post> page = new PageImpl<>(Collections.singletonList(post));
        when(postPersistencePort.getPost(anyInt(), anyInt())).thenReturn(page);

        // Act
        Page<Post> result = postUseCase.getPost(0, 10);

        // Assert
        assertEquals(1, result.getTotalElements());
        assertEquals(post, result.getContent().get(0));
        verify(postPersistencePort, times(1)).getPost(0, 10);
    }
}
