package com.javabom.cafe.service;

import com.javabom.cafe.domain.order.Order;
import com.javabom.cafe.domain.order.OrderRepository;
import com.javabom.cafe.domain.table.Table;
import com.javabom.cafe.domain.table.TableRepository;
import com.javabom.cafe.domain.vo.Money;
import com.javabom.cafe.dto.order.OrderCreateReqDto;
import com.javabom.cafe.dto.order.OrderFindAllResDto;
import com.javabom.cafe.dto.table.FindAllResDto;
import com.javabom.cafe.dto.table.TableCreateReqDto;
import com.javabom.cafe.dto.table.TableGetResDto;
import com.javabom.cafe.dto.table.TableAmountResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TableService {

    private final TableRepository tableRepository;
    private final OrderRepository orderRepository;

    public ResponseEntity<TableGetResDto> get(final Long id) {
        Table table = tableRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("테이블번호: %d, 존재하지 않는 테이블 번호입니다.", id)));

        TableGetResDto resDto = new TableGetResDto(table);
        return new ResponseEntity<>(resDto, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> create(final TableCreateReqDto reqDto) {
        Table table = Table.builder()
                .name(reqDto.getTableName())
                .build();

        tableRepository.save(table);

        return new ResponseEntity<>("테이블 생성", HttpStatus.OK);
    }

    public ResponseEntity<String> delete(final Long id) {
        tableRepository.deleteById(id);

        return new ResponseEntity<>("테이블 삭제", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> addOrder(final Long id, final OrderCreateReqDto orderCreateReqDto) {
        Table table = tableRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException((String.format("테이블번호: %d, 존재하지 않는 테이블 번호입니다.", id))));
        Order order = Order.builder()
                .menu(orderCreateReqDto.getMenu())
                .quantity(orderCreateReqDto.getQuantity())
                .build();

        orderRepository.save(order);
        table.addOrder(order);

        return ResponseEntity.ok("주문 저장 성공");
    }

    public ResponseEntity<List<OrderFindAllResDto>> findOrders(final Long id) {
        Table table = tableRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException((String.format("테이블번호: %d, 존재하지 않는 테이블 번호입니다.", id))));

        List<OrderFindAllResDto> resDtos = table.getOrders().stream()
                .map(order -> new OrderFindAllResDto(order.getMenu().getName(),
                        order.getQuantity(),
                        order.getMenu().getPrice()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(resDtos);
    }

    @Transactional
    public ResponseEntity<TableAmountResDto> getAmounts(final Long id) {
        Table table = tableRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException((String.format("테이블번호: %d, 존재하지 않는 테이블 번호입니다.", id))));

        Money cash = table.calculatePrice(2);
        Money card = table.calculatePrice(1);

        return ResponseEntity.ok(new TableAmountResDto(cash, card));
    }

    public ResponseEntity<List<FindAllResDto>> findAll() {
        List<Table> tables = tableRepository.findAll();
        List<FindAllResDto> resDtos = tables.stream()
                .map(table -> new FindAllResDto(table.getId(), table.getName()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(resDtos);
    }
}
