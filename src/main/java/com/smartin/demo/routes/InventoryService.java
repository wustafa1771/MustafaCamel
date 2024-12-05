package com.smartin.demo.routes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

	@Autowired
	InventoryRepository inventoryRepository;
	
	public List<Inventory> getAllServices() {
		List<Inventory> inventories  = inventoryRepository.findAll();

		return inventories;
	}

	public Optional<Inventory> getInventoryById(long id) {
		Optional<Inventory> inv = inventoryRepository.findById(id);
		return inv;
	}

	public Inventory saveInventory(Inventory inv) {
		Inventory createdInv = inventoryRepository.save(inv);

		return createdInv;
	}

	public void deleteInventory(long id) {
		inventoryRepository.deleteById(id);

	}

	public List<Inventory> findByAvailable() {
		// List<Inventory> inventories = inventoryRepository.findByPublished(true);
		return null;
	}

	public List<Inventory> findByUserId(long userId) {
		List<Inventory> inventories = inventoryRepository.findByUserId(userId);
		return inventories ;
	}


}