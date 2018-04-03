package com.drinksunibot.services;

import com.drinksunibot.model.Drink;

import java.util.List;

public interface DrinkService {
	
	List<Drink> findAll();
	
	List<Drink> getAllByName(String name);
	
	Drink getFirstByName(String name);
	
}
