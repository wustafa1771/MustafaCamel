package com.smartin.demo.routes;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long>{
	  List<Inventory> findByActive(boolean active);
	  List<Inventory> findByUserId(long userId);
}
