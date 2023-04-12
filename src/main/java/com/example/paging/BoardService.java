package com.example.paging;

import com.example.paging.dto.BoardDto;
import com.example.paging.dto.BoardListDto;
import com.example.paging.entity.Board;
import com.example.paging.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardQueryRepository boardQueryRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;


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

    public List<BoardDto> getBoardListByUserId(Long userId, Pageable pageable) {
        // api 실행 시 could not initialize proxy [com.example.paging.entity.User#1] - no Session
        // https://cantcoding.tistory.com/78
        // User를 단건조회 했는데 lazy loading 으로 연관된 board는 바로 초기화 되지 않고
        // 필요한 정보 채워지는 프록시 객체로 채워짐.
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        return boardRepository.findByUserOrderByBoardId(user, pageable)
                .map(BoardDto::from).getContent();
    }
}