package com.foodie.repositories;
import com.foodie.entities.MenuItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<MenuItems,String> {

    @Query("select m from MenuItems m where m.menuId=:menuId")
    public Optional<MenuItems> findItemsById(@Param("menuId") String menuId);

}
