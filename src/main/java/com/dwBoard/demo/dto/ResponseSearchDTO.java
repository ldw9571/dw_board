package com.dwBoard.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
//객체 전체 초기화
@AllArgsConstructor
//Builder 패턴 사용하여 객체 생성(불변객체), 넣지 않은 필드는 기본값
@Builder
public class ResponseSearchDTO {

    private int page;             // 현재 페이지 번호
    private int recordSize;       // 페이지당 출력할 데이터 개수
    private int pageSize;         // 화면 하단에 출력할 페이지 사이즈
    private String searchType;    // 검색 유형 (title, content, writer 등)
    private String searchText;    // 검색 키워드
    private String sort;          // 정렬 기준 (title, dateTime 등)
    private String orderBy;       // 정렬 방향 (asc, desc)
}
