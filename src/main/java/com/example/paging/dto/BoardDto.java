package com.example.paging.dto;

import com.example.paging.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardDto {
    private Long boardId;
    private String title;
    private Long views;
    private LocalDateTime createTime;
    private Long userId;
    private String nickname;

    @Builder
    public BoardDto(Long boardId, String title, Long views, LocalDateTime createTime, Long userId, String nickname) {
        this.boardId = boardId;
        this.title = title;
        this.views = views;
        this.createTime = createTime;
        this.userId = userId;
        this.nickname = nickname;
    }

    public BoardDto(Board board) {
        this.boardId = board.getBoardId();
        this.title = board.getTitle();
        this.views = board.getViews();
        this.createTime = board.getCreateTime();
        this.userId = board.getUser().getUserId();
        this.nickname = board.getUser().getNickname();
    }
}