package service.order;

import domain.order.OrderMenuRepository;
import domain.table.Table;
import domain.table.TableRepository;
import dto.table.TableInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TableService {
    private final TableRepository tableRepository;
    private final OrderMenuRepository orderMenuRepository;

    public List<TableInfoDto> getTableInfos() {
        List<Table> tables = tableRepository.findAll();

        return tables.stream()
                .map(table -> new TableInfoDto(table.getTableName(), orderMenuRepository.existsByTable(table)))
                .collect(Collectors.toList());
    }
}
