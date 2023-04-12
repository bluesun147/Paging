package com.example.paging.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @Column(nullable = false, length = 10000)
    private String content;

    @Column(nullable = false, length = 100)
    private String title;

    private Long views;

    private LocalDateTime createTime;

    // ManyToOne 있는 쪽이 다.
    // api 실행 시 could not initialize proxy [com.example.paging.entity.User#1] - no Session
    // 해당 오류 때문에 EAGER로 바꿈. LAZY에서 해결할 수 있는 방법 알아보자
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;
}