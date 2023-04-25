package com.example.paging.board.dto;

import com.example.paging.board.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class BoardListDto {
    private List<BoardDto> boardDtoList = new ArrayList<>();
    private Long totalPages;
    private Long totalCount;

    @Builder
    public BoardListDto(List<Board> boardList, Long totalPages, Long totalCount) {
        // stream은 컬렉션, 배열에 저장되어 있는 요소들 하나씩 참조하며 반복적 처리 가능하게 함.
        // stream과 람다 표현식 사용하면 for문 사용
        this.boardDtoList = boardList.stream()
                // 이중 콜론 연산자 (double colon operator)
                // [인스턴스]::[메소드명(or new)]
                // User::getId와 같이 사용
                // 람다 표햔식 () -> {} 에서만 사용 가능
                // 람다식에서 파라미터 중복해서 쓰기 싫을 때
                .map(BoardDto::new).collect(Collectors.toList());
        this.totalPages = totalPages;
        this.totalCount = totalCount;
    }
}