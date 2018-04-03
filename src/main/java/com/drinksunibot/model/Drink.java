package com.drinksunibot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document()
public class Drink {
	
	@Id
	private String id;
	@Indexed(direction = IndexDirection.ASCENDING)
	private String name;
	private String desctiption;
	
	public Drink() {
	
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDesctiption() {
		return desctiption;
	}
	
	public void setDesctiption(String desctiption) {
		this.desctiption = desctiption;
	}
	
	@Override
	public String toString() {
		return "Drink{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", desctiption='" + desctiption + '\'' + '}';
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Drink drink = (Drink) o;
		return Objects.equals(id, drink.id) && Objects.equals(name, drink.name) && Objects.equals(desctiption, drink.desctiption);
	}
	
	@Override
	public int hashCode() {
		
		return Objects.hash(id, name, desctiption);
	}
}
