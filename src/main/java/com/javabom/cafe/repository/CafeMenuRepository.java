package com.javabom.cafe.repository;

import com.javabom.cafe.domain.menu.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CafeMenuRepository extends JpaRepository<Menu, Long> {
}
