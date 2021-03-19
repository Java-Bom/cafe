package com.javabom.cafe.repository;


import com.javabom.cafe.entity.CafeMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenusRepository extends JpaRepository<CafeMenu, Integer> {

    List<CafeMenu> findAll();

    void deleteMenuById(int menuId);
}
