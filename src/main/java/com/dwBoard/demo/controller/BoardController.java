package com.dwBoard.demo.controller;

import com.dwBoard.demo.Entity.BoardEntity;
import com.dwBoard.demo.dto.BoardRequestDTO;
import com.dwBoard.demo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

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



    //home
    @GetMapping("/board/home")
    public String boardHomeForm(){
        return "/board/home";
    }

    //게시글 작성 페이지 load
    @GetMapping("/board/write")
    public String boardWriteForm(Model model){
        return "/board/write";
    }

    //게시글 작성 페이지 load
    @GetMapping("/board/boardFindOne")
    public String boardFindOne(@RequestParam(value = "id",required = false) Long id,
                                 Model model){
        //결과에 따른 alert message
        String message = "";

        if(id != null){
            Optional<BoardEntity> board = boardService.findById(id);
            if(board.isPresent()){
                model.addAttribute("board",board.get());
                message = "게시글 작성에 성공하였습니다.";
            }else{
                message = "해당 게시물을 찾을 수 없습니다. 관리자에게 문의하세요";
            }
        }

        // model에 메시지 추가
        if (message != null && !message.isEmpty()) {
            model.addAttribute("message", message);
        }

        System.out.println("message = " + message);  // 모델에 값이 제대로 설정되었는지 확인
        return "/board/write";
    }


    //게시글 작성
    @PostMapping("/board/write")
    public String boardWrite(BoardRequestDTO boardRequestDTO, Model model){

        //게시글 저장처리
        BoardEntity boardEntity = boardService.write(boardRequestDTO);

        // 게시글 작성 후 alert 띄워주고 작성한 게시물 보여주기
        if (boardEntity.getId() != null) {
            return "redirect:/board/boardFindOne?id="+boardEntity.getId();
        } else {
            return "redirect:/board/boardFindOne?";
        }
    }

    //게시글 전체조회
    @GetMapping("/board/findAll")
    public void boardFindAll(Model model){

    }

}
