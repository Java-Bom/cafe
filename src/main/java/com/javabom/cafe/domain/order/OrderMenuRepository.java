package com.javabom.cafe.domain.order;

import com.javabom.cafe.domain.table.Table;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderMenuRepository extends JpaRepository<OrderMenu, Long> {
    List<OrderMenu> findAllByTable(final Table table);

    boolean existsByTable(final Table table);
}
