package com.example.paging.user.entity;

import com.example.paging.board.entity.Board;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
// 매핑 어노테이션
// 테이블의 unique 조건 정의
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "email", columnNames = {"email"}), // 이메일에 유니크 조건 부여
        @UniqueConstraint(name = "nickname", columnNames = {"nickname"}),
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 50, name = "email")
    private String email;

    @Column(nullable = false, length = 50, name = "nickname")
    private String nickname;

    @Column(nullable = false)
    private Long age;

    // 만약 양방향 아니라면 OneToMany 없어도 됨.
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Board> boards = new ArrayList<>();
}