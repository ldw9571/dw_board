package com.dwBoard.demo.controller;

import com.dwBoard.demo.Entity.BoardEntity;
import com.dwBoard.demo.dto.BoardRequestDTO;
import com.dwBoard.demo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {


    // spring 컨테이너가 뜰 때 controller 생성 => 생성자 호출
    // => new로 계속 생성자를 만들지 않고  어디서든 BoardService에 연결
    // BoardService에 @Service 설정해줘야함
    // 생성자 단축키 Alt + insert
    private final BoardService boardService;

    // @Autowired : spring이 spring 컨테이너에 있는 BoardService에 연결 시켜준다
    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }



    //게시글 작성 페이지 load
    @GetMapping("/board/write")
    public String boardWriteForm(){
        return "/board/write";
    }

    //게시글 작성
    @PostMapping("/board/write")
    public String boardWrite(BoardRequestDTO boardRequestDTO, Model model){

        //게시글 저장처리
        BoardEntity boardEntity = boardService.write(boardRequestDTO);

        // id가 존재하면 성공, 없으면 실패로 판단
        // @Param: writeValue = 작성여부 [1:성공]
        if (boardEntity.getId() != null) {
            model.addAttribute("message", "저장에 성공했습니다.");
            model.addAttribute("writeValue", "1");
        } else {
            model.addAttribute("message", "저장에 실패했습니다.");
            model.addAttribute("writeValue", "0");
        }

        // 모델에 담긴 값 확인
        System.out.println("Message: " + model.getAttribute("message"));
        System.out.println("WriteValue: " + model.getAttribute("writeValue"));

        return "redirect:/board/write";
    }

}
