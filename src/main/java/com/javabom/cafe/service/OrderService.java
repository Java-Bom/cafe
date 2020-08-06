package com.javabom.cafe.service;

import com.javabom.cafe.domain.menu.Menu;
import com.javabom.cafe.domain.menu.MenuRepository;
import com.javabom.cafe.domain.order.OrderMenu;
import com.javabom.cafe.domain.order.OrderMenuRepository;
import com.javabom.cafe.domain.order.OrderMenus;
import com.javabom.cafe.domain.order.Quantity;
import com.javabom.cafe.domain.table.Table;
import com.javabom.cafe.domain.table.TableRepository;
import com.javabom.cafe.dto.order.OrderAddDto;
import com.javabom.cafe.dto.order.TableOrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderMenuRepository orderMenuRepository;
    private final MenuRepository menuRepository;
    private final TableRepository tableRepository;

    @Transactional
    public void addOrder(final OrderAddDto orderAddDto) {
        Table table = findTableById(orderAddDto.getTableId());
        Menu menu = findMenuById(orderAddDto.getMenuId());
        Quantity menuQuantity = new Quantity(orderAddDto.getQuantity());

        OrderMenus orderMenus = new OrderMenus(orderMenuRepository.findByTable(table));
        Optional<OrderMenu> findOrderMenu = orderMenus.findByMenu(menu);

        if (findOrderMenu.isPresent()) {
            findOrderMenu.get().addQuantity(menuQuantity);
            return;
        }

        OrderMenu orderMenu = OrderMenu.builder()
                .menu(menu)
                .table(table)
                .quantity(menuQuantity)
                .build();

        orderMenuRepository.save(orderMenu);
    }

    public TableOrderDto showOrders(final Long tableId) {
        Table table = findTableById(tableId);
        OrderMenus orderMenus = new OrderMenus(orderMenuRepository.findByTable(table));

        return TableOrderDto.builder()
                .tableId(table.getId())
                .tableName(table.getTableName())
                .orders(orderMenus.getOrders())
                .payment(orderMenus.calculatePayment())
                .build();
    }

    private Table findTableById(final Long id) {
        return tableRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    private Menu findMenuById(final Long id) {
        return menuRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }
}
