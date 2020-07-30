package com.javabom.cafe;

import com.javabom.cafe.domain.menu.Menu;
import com.javabom.cafe.domain.menu.MenuType;
import com.javabom.cafe.domain.table.CafeTable;
import com.javabom.cafe.repository.CafeMenuRepository;
import com.javabom.cafe.repository.CafeTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
public class MockDataController {

    @Autowired
    private InitDB initDB;

    @PostConstruct
    public void init() {
//        initDB.dbInit1();
//        initDB.dbInit2();
    }

    @Component
    @Transactional
    static class InitDB {

        @Autowired
        private CafeTableRepository tableRepository;

        @Autowired
        private CafeMenuRepository menuRepository;

        public void dbInit1() {
            List<CafeTable> tables = Arrays.asList(CafeTable.ofName("1번 테이블"), CafeTable.ofName("2번 테이블"),
                    CafeTable.ofName("3번 테이블"), CafeTable.ofName("4번 테이블"));

            for (CafeTable table : tables) {
                tableRepository.save(table);
            }
        }


        public void dbInit2() {
            List<Menu> menus = Arrays.asList(new Menu("Cheeze", MenuType.CAKE, 7000),
                    new Menu("choco", MenuType.CAKE, 5000),
                    new Menu("Americano", MenuType.BEVERAGE, 3500),
                    new Menu("latte", MenuType.BEVERAGE, 4000));

            for (Menu menu : menus) {
                menuRepository.save(menu);
            }
        }


    }

}
