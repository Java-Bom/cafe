package com.javabom.cafe.service;

import com.javabom.cafe.domain.Category;
import com.javabom.cafe.domain.Menu;
import com.javabom.cafe.dtos.CreateMenuDto;
import com.javabom.cafe.dtos.ShownTable;
import com.javabom.cafe.repository.MenusRepository;
import com.javabom.cafe.repository.TablesRepository;
import com.javabom.cafe.domain.Cafe;
import com.javabom.cafe.domain.Table;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CafeService {

    private final Cafe cafe;
    private final TablesRepository tablesRepository;
    private final MenusRepository menusRepository;

    public CafeService(TablesRepository tablesRepository, MenusRepository menusRepository) {
        this.tablesRepository = tablesRepository;
        this.menusRepository = menusRepository;
        this.cafe = new Cafe(tablesRepository.findAll(), menusRepository.findAll());
    }

    public void createTable(final String tableName) {
        Table createdTable = new Table(tableName);
        tablesRepository.save(createdTable);
        cafe.addTable(createdTable);
    }

    public List<ShownTable> findAllTables() {
        return cafe.getTables().stream()
                .map(table -> new ShownTable(table.getNumber(), table.getName(), table.hasMenu()))
                .collect(Collectors.toList());
    }

    public void deleteTable(int tableNumber) {
        tablesRepository.deleteTableByNumber(tableNumber);
        cafe.deleteTable(tableNumber);
    }

    public void createMenu(final CreateMenuDto dto) {
        Menu createdMenu = new Menu(dto.getMenuName(), Category.valueOf(dto.getMenuType()), dto.getMenuPrice());
        menusRepository.save(createdMenu);
        cafe.addMenu(createdMenu);
    }

    public List<Menu> findAllMenus() {
        return cafe.getMenus();
    }

    public void deleteMenu(int menuNumber) {
        menusRepository.deleteMenuByNumber(menuNumber);
        cafe.deleteMenu(menuNumber);
    }
}
