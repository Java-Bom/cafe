package com.javabom.cafe.service;

import com.javabom.cafe.domain.order.Order;
import com.javabom.cafe.domain.order.OrderRepository;
import com.javabom.cafe.domain.table.Table;
import com.javabom.cafe.domain.table.TableRepository;
import com.javabom.cafe.dto.order.OrderCreateReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
}
