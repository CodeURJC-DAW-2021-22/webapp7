package com.example.demo.repository;

import com.example.demo.model.Menu;
import com.example.demo.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu,Long> {
    Optional<Menu> findMenuById(Long id);

    @Modifying
    @Query("update Menu m set m.weeklyPlan = ?1 where m.weeklyPlan= ?2")
    int updateBeforeDelete(List<Recipe> newWP, List<Recipe> oldWP);

}
