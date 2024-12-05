package com.smartin.demo.routes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventory")
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "inventory_name")
	private String inventory_name;

	@Column(name = "description")
	private String description;

	@Column(name = "is_inventory_active")
	private boolean active;

	@Column(name = "user_id")
	private long userId;

	public Inventory() {

	}

	public Inventory(String inventory_name, String description, boolean active,long userId) {
		this.inventory_name = inventory_name;
		this.description = description;
		this.active = active;
		this.userId = userId;
	}

	public long getId() {
		return id;
	}

	public String getinventory_name() {
		return inventory_name;
	}

	public void setinventory_name(String inventory_name) {
		this.inventory_name = inventory_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isactive() {
		return active;
	}

	public void setactive(boolean isactive) {
		this.active = isactive;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Inventory [id=" + id + ", inventory_name=" + inventory_name + ", description=" + description
				+ ", active=" + active + ", userId=" + userId + "]";
	}
}
