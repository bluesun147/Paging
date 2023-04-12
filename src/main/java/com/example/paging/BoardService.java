package com.example.paging;

import com.example.paging.dto.BoardListDto;
import com.example.paging.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardQueryRepository boardQueryRepository;

    public ResponseEntity<BoardListDto> getBoardList(Pageable pageable) {
        // 리포에서 정의한 메소드 결과값 dto로 변환
        // Page<> 형식으로 db에서 꺼낸 후 dto로 빌더에 넣음
        Page<Board> results = boardQueryRepository.getBoardList(pageable);

        return new ResponseEntity<>(BoardListDto.builder()
                .boardList(results.getContent()) // List<Board> 꺼내기
                .totalCount(results.getTotalElements()) // 총 게시물 수
                .totalPages((long)results.getTotalPages()) // 총 페이지 수
                .build(), HttpStatus.OK);
    }
}