package com.javabom.cafe.repository;

import com.javabom.cafe.entity.CafeTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TablesRepository extends JpaRepository<CafeTable, Integer> {

    List<CafeTable> findAll();

    void deleteTableById(int tableId);

}
