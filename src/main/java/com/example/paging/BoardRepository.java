package com.example.paging;

import com.example.paging.entity.Board;
import com.example.paging.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

// paging2

public interface BoardRepository extends JpaRepository<Board, Long> {
     Page<Board> findByUserOrderByBoardId(User user, Pageable pageable);
}