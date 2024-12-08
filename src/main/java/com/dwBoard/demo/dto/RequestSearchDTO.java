package com.dwBoard.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestSearchDTO {

    private int page;             // 현재 페이지 번호 (사용자에게 보여지는 값은 1부터 시작)
    private int recordSize;       // 페이지당 출력할 데이터 개수
    private int pageSize;         // 화면 하단에 출력할 페이지 사이즈
    private String searchText;    // 검색 키워드
    private String searchType;    // 검색 유형

    public RequestSearchDTO() {
        this.page = 1;
        this.recordSize = 5;
        this.pageSize = 5;
    }

    // 페이지 번호를 0부터 시작하도록 변환
    public int getOffset() {
        return (page - 1) * recordSize;  // 1페이지는 0번 페이지로, 2페이지는 1번 페이지로 처리
    }

    // 내부적으로 페이지 번호는 0부터 시작해야 하므로 보정
    public void setPage(int page) {
        if (page < 1) {
            this.page = 1;  // 페이지 번호가 1보다 작으면 1로 설정
        } else {
            this.page = page;
        }
    }

}