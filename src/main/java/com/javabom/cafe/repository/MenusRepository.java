package com.javabom.cafe.repository;


import com.javabom.cafe.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenusRepository extends JpaRepository<Menu, Integer> {

    List<Menu> findAll();

    void deleteMenuByNumber(int menuNumber);
}
