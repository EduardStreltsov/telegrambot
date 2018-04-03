package com.drinksunibot.repositories;

import com.drinksunibot.model.Drink;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DrinkRepository extends MongoRepository<Drink, String> {
	
	List<Drink> findAll();
	
	List<Drink> getAllByName(String name);
	
	Drink getFirstByName(String name);
	
}