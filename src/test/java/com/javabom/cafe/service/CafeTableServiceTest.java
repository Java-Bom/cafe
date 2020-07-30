package com.javabom.cafe.service;

import com.javabom.cafe.domain.table.CafeTable;
import com.javabom.cafe.repository.CafeTableRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class CafeTableServiceTest {

    @Autowired
    private CafeTableRepository cafeTableRepository;

    @Test
    @DisplayName("생성된 모든 테이블을 조회하는지 확인.")
    void getTablesTest() {
        CafeTable cafeTable1 = CafeTable.ofName("1");
        CafeTable cafeTable2 = CafeTable.ofName("2");

        cafeTableRepository.save(cafeTable1);
        cafeTableRepository.save(cafeTable2);
        List<CafeTable> findAll = cafeTableRepository.findAll();


        assertThat(findAll.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("새로운 테이블을 생성하는지 확인.")
    void createTableTest() {
        CafeTable cafeTable1 = CafeTable.ofName("1");

        cafeTableRepository.save(cafeTable1);
        CafeTable findTable = cafeTableRepository.findById(cafeTable1.getId()).get();

        assertThat(findTable.getTableName()).isEqualTo(cafeTable1.getTableName());
    }

    @Test
    @DisplayName("기존에 생성된 테이블을 삭제하는지 확인.")
    void deleteTableTest() {
        CafeTable cafeTable1 = CafeTable.ofName("1");
        CafeTable cafeTable2 = CafeTable.ofName("2");

        cafeTableRepository.save(cafeTable1);
        cafeTableRepository.save(cafeTable2);
        cafeTableRepository.delete(cafeTable1);
        List<CafeTable> findAll = cafeTableRepository.findAll();


        assertThat(findAll.size()).isEqualTo(1);
    }
}