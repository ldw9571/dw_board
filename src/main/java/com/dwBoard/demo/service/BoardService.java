package com.dwBoard.demo.service;

import com.dwBoard.demo.dto.BoardResponseDTO;
import com.dwBoard.demo.dto.RequestSearchDTO;
import com.dwBoard.demo.entity.BoardEntity;
import com.dwBoard.demo.dto.BoardRequestDTO;
import com.dwBoard.demo.repository.BoardRepository;
import org.eclipse.jdt.internal.compiler.lookup.SourceTypeBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.lang.model.SourceVersion;
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
    public Page<BoardResponseDTO> findAll(RequestSearchDTO requestSearchDTO, String sort, String orderBy) {

        // 기본 정렬 기준: DESC, 기본 기준: dateTime
        Sort.Direction sortDirection = "asc".equalsIgnoreCase(orderBy) ? Sort.Direction.ASC : Sort.Direction.DESC;
        sort = (sort == null || sort.isEmpty()) ? "dateTime" : sort;

        // 페이징 및 정렬 설정
        Pageable pageable = PageRequest.of(requestSearchDTO.getPage() - 1, requestSearchDTO.getRecordSize(), Sort.by(sortDirection, sort));

        // 검색 조건이 없을 경우 findAll 호출
        Page<BoardEntity> boardAll;
        if (requestSearchDTO.getSearchType() == null || requestSearchDTO.getSearchText() == null ||
                requestSearchDTO.getSearchType().isEmpty() || requestSearchDTO.getSearchText().isEmpty()) {
            boardAll = boardRepository.findAll(pageable);
        } else {
            // 검색 조건이 있을 경우
            boardAll = boardRepository.findBySearchTextAndType(
                    requestSearchDTO.getSearchText(),
                    requestSearchDTO.getSearchType(),
                    pageable
            );
        }

        // BoardEntity -> BoardResponseDTO 변환
        return boardAll.map(boardEntity -> new BoardResponseDTO(boardEntity));
    }

}
