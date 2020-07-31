package service.order;

import domain.menu.Menu;
import domain.menu.MenuRepository;
import domain.order.OrderMenu;
import domain.order.OrderMenuRepository;
import domain.order.OrderMenus;
import domain.order.Quantity;
import domain.table.Table;
import domain.table.TableRepository;
import dto.order.OrderAddDto;
import dto.order.OrdersDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    public List<OrdersDto> showOrders(final Long tableId) {
        Table table = findTableById(tableId);
        OrderMenus orderMenus = new OrderMenus(orderMenuRepository.findByTable(table));

        return orderMenus.getOrders();
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
