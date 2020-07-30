package com.javabom.cafe.service;

import com.javabom.cafe.controller.dto.NewOrderDto;
import com.javabom.cafe.controller.dto.OrderMenuDto;
import com.javabom.cafe.controller.dto.OrdersDto;
import com.javabom.cafe.domain.menu.Menu;
import com.javabom.cafe.domain.menu.OrderMenu;
import com.javabom.cafe.domain.table.CafeTable;
import com.javabom.cafe.domain.vo.Amount;
import com.javabom.cafe.domain.vo.Quantity;
import com.javabom.cafe.repository.CafeMenuRepository;
import com.javabom.cafe.repository.CafeTableRepository;
import com.javabom.cafe.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CafeMenuRepository menuRepository;

    @Autowired
    private CafeTableRepository tableRepository;

    @Transactional
    public void createOrder(final NewOrderDto newOrderDto) {
        try {
            CafeTable cafeTable = findCafeTableById(newOrderDto.getTableId());
            Menu menu = menuRepository.findById(newOrderDto.getOrderMenuId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            String.format("주문할 메뉴 : %s - 해당 메뉴는 존재하지 않습니다.", newOrderDto.getOrderMenuId())));

            OrderMenu orderMenu = OrderMenu.selectOrderMenu(menu, cafeTable, newOrderDto.getOrderAmount());
            orderRepository.save(orderMenu);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private CafeTable findCafeTableById(final Long tableId) {
        return tableRepository.findById(tableId)
                    .orElseThrow(() -> new IllegalArgumentException(
                            String.format("입력한 table Id : %d - 테이블이 존재하지 않습니다.", tableId)));
    }

    public OrdersDto getOrderMenus(final Long tableId) {
        try {
            CafeTable orderTable = findCafeTableById(tableId);
            List<OrderMenu> orderMenus = orderRepository.findAllByTableId(tableId)
                    .orElseThrow(() -> new IllegalArgumentException(
                            String.format("입력한 table Id : %d - 해당 테이블에서 주문한 메뉴가 없습니다.", tableId)));

            return new OrdersDto(orderTable.getId(), orderTable.getTableName(), getOrderMenuDtoList(orderMenus));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return new OrdersDto();
        }

    }

    private List<OrderMenuDto> getOrderMenuDtoList(final List<OrderMenu> orderMenus) {
        List<OrderMenuDto> dtos = new ArrayList<>();

        for (OrderMenu orderMenu : orderMenus) {
            Menu menu = orderMenu.getMenu();
            Quantity quantity = orderMenu.getQuantity();
            Amount price = menu.getPrice();
            OrderMenuDto orderMenuDto = new OrderMenuDto(menu.getMenuName(), quantity.getValue(), price.getValue());
            dtos.add(orderMenuDto);
        }

        return dtos;
    }
}
