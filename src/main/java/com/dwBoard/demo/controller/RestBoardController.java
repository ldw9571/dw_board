package com.dwBoard.demo.controller;

import com.dwBoard.demo.dto.BoardRequestDTO;
import com.dwBoard.demo.dto.BoardResponseDTO;
import com.dwBoard.demo.entity.BoardEntity;
import com.dwBoard.demo.service.BoardService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/board")
public class RestBoardController {

    private final BoardService boardService;

    @Autowired
    public RestBoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 게시글 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> boardFindOne(@PathVariable @NotNull @Min(1) Long id) {
        Optional<BoardEntity> board = boardService.findById(id);
        if (board.isPresent()) {
            BoardResponseDTO responseDTO = new BoardResponseDTO(board.get());
            return ResponseEntity.ok(responseDTO);
        } else {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("code", "404");
            errorResponse.put("message", "게시물을 찾을 수 없습니다.");
            errorResponse.put("details", Collections.singletonList("게시물이 없습니다."));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    // 게시글 작성
    @PostMapping("/write")
    public ResponseEntity<?> boardWrite(@RequestBody @Validated BoardRequestDTO boardRequestDTO,
                                        BindingResult bindingResult) {
        // 검증 오류가 있을 경우
        if (bindingResult.hasErrors()) {
            System.out.print("오류=="+bindingResult.getAllErrors());

            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("code", "400");
            errorResponse.put("message", "입력값에 오류가 있습니다.");

            // 검증 오류 메시지를 리스트로 모음
            List<String> errorMessages = new ArrayList<>();
            bindingResult.getAllErrors().forEach(error -> {
                errorMessages.add(error.getDefaultMessage());
            });

            errorResponse.put("details", errorMessages);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        // 게시글 저장처리
        BoardEntity boardEntity = boardService.write(boardRequestDTO);

        // 게시글 작성 후 작성된 게시물 반환
        if (boardEntity.getId() != null) {
            BoardResponseDTO responseDTO = new BoardResponseDTO(boardEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } else {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("code", "500");
            errorResponse.put("message", "게시글 작성에 실패했습니다.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

}
