package com.aalvarezg.ms_publications.infrastructure.input.rest;

import com.aalvarezg.ms_publications.application.dto.LikeRequestDTO;
import com.aalvarezg.ms_publications.application.dto.PostRequestDTO;
import com.aalvarezg.ms_publications.application.dto.PostResponseDTO;
import com.aalvarezg.ms_publications.application.dto.PostResponsePaginationDTO;
import com.aalvarezg.ms_publications.application.handler.ILikeHandler;
import com.aalvarezg.ms_publications.application.handler.IPostHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publications/")
@RequiredArgsConstructor
public class PostRestController {

    private final IPostHandler postHandler;
    private final ILikeHandler likeHandler;

    @Operation(
            summary = "Crear una publicación",
            description = "Este endpoint permite crear una nueva publicación de un usuario."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Publicación creada exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PostResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Solicitud inválida (error de validación)",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content
            )
    })
    @PostMapping("post-created")
    public ResponseEntity<PostResponseDTO> postCreated(
            @RequestBody PostRequestDTO postRequestDTO) {
        PostResponseDTO postResponseDTO = postHandler.postCreated(postRequestDTO);
        return ResponseEntity.ok(postResponseDTO);
    }

    @Operation(
            summary = "Agregar un 'Like' a una publicación",
            description = "Este endpoint permite que un usuario agregue un 'Like' a una publicación existente."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Like agregado exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Boolean.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Solicitud inválida (error de validación)",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content
            )
    })
    @PostMapping("add-like")
    public ResponseEntity<Boolean> addLikeToPost(@RequestBody LikeRequestDTO likeRequestDTO ) {
        boolean addLike = likeHandler.addLikeToPost(likeRequestDTO);
        return ResponseEntity.ok(addLike);
    }

    @Operation(
            summary = "Obtener todas las publicaciones",
            description = "Este endpoint permite obtener una lista paginada de todas las publicaciones."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Publicaciones obtenidas exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PostResponsePaginationDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Solicitud inválida",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content
            )
    })
    @GetMapping("all")
    public ResponseEntity<PostResponsePaginationDTO> getPost(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(postHandler.getPost(page, size));
    }
}
