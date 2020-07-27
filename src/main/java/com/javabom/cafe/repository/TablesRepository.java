package com.javabom.cafe.repository;

import com.javabom.cafe.domain.Table;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TablesRepository extends JpaRepository<Table, Integer> {

    List<Table> findAll();

    void deleteTableByNumber(int tableNumber);

}
