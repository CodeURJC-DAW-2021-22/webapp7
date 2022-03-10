package com.example.demo.repository;

import com.example.demo.model.Menu;
import com.example.demo.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu,Long> {
    Optional<Menu> findMenuById(Long id);

    @Modifying @Transactional
    @Query(value = "update public.menu_weekly_plan set weekly_plan_id = ?1 where weekly_plan_id = ?2", nativeQuery = true)
    int updateBeforeDelete(long idToChange, long idToRemove);

}
