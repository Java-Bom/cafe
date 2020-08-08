package com.javabom.cafe.domain.table;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public interface TableRepository extends JpaRepository<Table, Long> {
}
