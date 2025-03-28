package com.aalvarezg.ms_auth.infrastructure.input.rest;

import com.aalvarezg.ms_auth.application.dto.UserRequestDTO;
import com.aalvarezg.ms_auth.application.dto.UserResponseDTO;
import com.aalvarezg.ms_auth.application.handler.IAuthHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/")
@RequiredArgsConstructor
public class AuthRestController {

    private final IAuthHandler authHandler;

    @Operation(
            summary = "Logear un usuario",
            description = "Este endpoint permite realizar un login"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuario logeado creado exitosamente",
                    content = @Content(
                            mediaType = "application/json"
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
    @PostMapping("login")
    public ResponseEntity<UserResponseDTO> crearPropietario(
            @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO token = authHandler.login(userRequestDTO);
        return ResponseEntity.ok(token);
    }
}
