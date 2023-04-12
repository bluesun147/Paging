package com.example.paging;

import com.example.paging.entity.Board;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;

import java.util.List;

/*
 https://velog.io/@soyeon207/QueryDSL-이란
 entity 클래스와 매핑되는 querydsl Q클래스 객체
 querydsl은 컴파일 단계에서 엔티티를 기반으로 Q클래스를 생성하는데 JPAAnnotationProcessor가
 컴파일 시점에 작동해서 @Entity 어노테이션을 찾아 해당 파일 분석해 QClass 만든다.
 QClass는 entity와 형태 똑같은 static class 이다.
 querydsl은 쿼리 작성 시 QClass 기반으로 쿼리 실행함.

 회원에서 포인트 조인해 가져와야 하는 경우

 // JPQL의 경우 문자열
 String jpql = "select * from Member m join Point p on p.member_id = m.id"
 List<Member> result = em.createQuery(jpql, Member.class).getresultList();

 // QeuryDSL의 경우 코드 사용 가능! 객체지향적
 return jpaQueryFactory
    .from(member)
    .join(member.point, point)
    .fetch();
 */

// paging1

import static com.example.paging.entity.QBoard.board;
import static com.example.paging.entity.QUser.user;

@Repository
public class BoardQueryRepository {
    private final EntityManager em;
    private final JPAQueryFactory query;

    public BoardQueryRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    // 반환타입 Page 인터페이스도 getTotalPages(), getTotalElements()와 같은 메소드 갖고있음.
    public Page<Board> getBoardList(Pageable pageable) {
        QueryResults<Board> results = query
                .selectFrom(board)
                // fetchjoin으로 유저 정보까지 한번에 조회
                .leftJoin(board.user, user).fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(board.boardId.asc())
                .fetchResults();

        List<Board> content = results.getResults();
        Long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }
}