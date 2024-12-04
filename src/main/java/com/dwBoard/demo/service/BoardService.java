package com.dwBoard.demo.service;

import com.dwBoard.demo.dto.BoardResponseDTO;
import com.dwBoard.demo.dto.SearchDTO;
import com.dwBoard.demo.entity.BoardEntity;
import com.dwBoard.demo.dto.BoardRequestDTO;
import com.dwBoard.demo.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }


    //게시글작성
    public BoardEntity write(BoardRequestDTO boardRequestDTO){

        //DTO => ENTITY
        BoardEntity dtoExchangeEntity = new BoardEntity();
        BoardEntity exchangeDtoToEntity = dtoExchangeEntity.exchange(boardRequestDTO);


        BoardEntity boardEntity = boardRepository.save(exchangeDtoToEntity);
        return boardEntity;
    }

    //게시글 단일조회
    public Optional<BoardEntity> findById(Long id) {
        Optional<BoardEntity> board = boardRepository.findById(id);
        return board;
    }

    //게시글 전체조회
    public Page<BoardResponseDTO> findAll(SearchDTO searchDTO, String sort, String orderBy) {

        //기본 오름차순 = DESC
        Sort.Direction defalutSort = Sort.Direction.DESC;

        // orderBy가 "asc"인 경우 오름차순으로 설정
        if ("asc".equalsIgnoreCase(orderBy)) {
            defalutSort = Sort.Direction.ASC;
        }


        // 기본 정렬기준 = 시간순
        if (sort == null || sort.isEmpty()) {
            sort = "dateTime";
        }

        //페이징
        //PageRequest(페이지수,한페이지갯수,Sort.by(차순,정렬기준)
        Pageable pageable = PageRequest.of(searchDTO.getPage() - 1, searchDTO.getRecordSize(), Sort.by(defalutSort,sort));




        Page<BoardEntity> boardAll = boardRepository.findBySearchTextAndType(searchDTO.getSearchType(),searchDTO.getSearchText(),pageable);

        // BoardEntity -> BoardResponseDTO 변환
        Page<BoardResponseDTO> boardResponseDTOPage = boardAll.map(boardEntity -> {
            BoardResponseDTO boardResponseDTO = new BoardResponseDTO();

            // 추후 build 로 변경 필요
            boardResponseDTO.setId(boardEntity.getId());
            boardResponseDTO.setTitle(boardEntity.getTitle());
            boardResponseDTO.setContent(boardEntity.getContent());
            boardResponseDTO.setWriter(boardEntity.getWriter());

            // LocalDateTime으로 보여지는 부분 date string으로 변경하여 전달
            boardResponseDTO.setDate(boardEntity.getDateTime());

            return boardResponseDTO;
        });

        return boardResponseDTOPage;
    }




}
