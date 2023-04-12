package com.example.paging;

import com.example.paging.dto.BoardDto;
import com.example.paging.dto.BoardListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    // paging1 - 모든 게시물 반환
    // http://localhost:8080/api/board?size=5&page=0
    // https://velog.io/@stbpiza/Spring-Boot-JPA-페이징-처리
    @GetMapping("/paging1")
    // Pageable 인터페이스 타입을 파라미터로 받음
    // 가진 메소드 보면 getPageNumber(), getPageSize(), getOffset()
    // 처럼 페이징을 구현할 때 필요한 값들을 편하게 구할 수 있는 메소드들 추상화 되어있음.
    public ResponseEntity<BoardListDto> getBoardList(Pageable pageable) {
        return boardService.getBoardList(pageable);
    }

    // paging2 - 해당 유저의 게시물 반환
    // https://devlog-wjdrbs96.tistory.com/414
    @GetMapping("/paging2")
    public List<BoardDto> getBoardListByUserId(Pageable pageable) {

        Long userId = 1L;

        return boardService.getBoardListByUserId(userId, pageable);
    }
}