package controller;

import dto.table.TableInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.order.OrderService;
import service.order.TableService;

import java.util.List;

@RestController
@RequestMapping("tables")
@RequiredArgsConstructor
public class TableController {

    private final TableService tableService;
    private final OrderService orderService;

    @GetMapping("")
    public ResponseEntity<List<TableInfoDto>> showTableList() {
        List<TableInfoDto> tableInfos = tableService.getTableInfos();

        return ResponseEntity.ok()
                .body(tableInfos);
    }
}
