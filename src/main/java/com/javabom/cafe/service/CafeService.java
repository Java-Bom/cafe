package com.javabom.cafe.service;

import com.javabom.cafe.dtos.CreateTableDto;
import com.javabom.cafe.entity.CafeMenu;
import com.javabom.cafe.entity.CafeTable;
import domain.Category;
import domain.Menu;
import com.javabom.cafe.dtos.CreateMenuDto;
import com.javabom.cafe.dtos.ShownTable;
import com.javabom.cafe.repository.MenusRepository;
import com.javabom.cafe.repository.TablesRepository;
import domain.Cafe;
import domain.Table;
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
        this.cafe = new Cafe(convertToTables(tablesRepository.findAll()), convertToMenus(menusRepository.findAll()));
    }

    private List<Table> convertToTables(List<CafeTable> cafeTables) {
        return cafeTables.stream()
                .map(cafeTable -> new Table(cafeTable.getId(), cafeTable.getName()))
                .collect(Collectors.toList());
    }

    private List<Menu> convertToMenus(List<CafeMenu> cafeMenus) {
        return cafeMenus.stream()
                .map(cafeMenu -> new Menu(cafeMenu.getId(), cafeMenu.getName(), cafeMenu.getCategory(), cafeMenu.getPrice()))
                .collect(Collectors.toList());
    }

    public void createTable(final CreateTableDto dto) {
        CafeTable createdTable = new CafeTable(dto.getTableName());
        tablesRepository.save(createdTable);
        cafe.addTable(new Table(createdTable.getId(), dto.getTableName()));
    }

    public List<ShownTable> findAllTables() {
        return cafe.getTables().stream()
                .map(ShownTable::new)
                .collect(Collectors.toList());
    }

    public void deleteTable(int tableNumber) {
        tablesRepository.deleteTableById(tableNumber);
        cafe.deleteTable(tableNumber);
    }

    public void createMenu(final CreateMenuDto dto) {
        CafeMenu createdMenu = new CafeMenu(dto.getMenuName(), Category.valueOf(dto.getMenuType()), dto.getMenuPrice());
        menusRepository.save(createdMenu);
        cafe.addMenu(new Menu(createdMenu.getId(), dto.getMenuName(), Category.valueOf(dto.getMenuType()), dto.getMenuPrice()));
    }

    public List<Menu> findAllMenus() {
        return cafe.getMenus();
    }

    public void deleteMenu(int menuNumber) {
        menusRepository.deleteMenuById(menuNumber);
        cafe.deleteMenu(menuNumber);
    }
}
