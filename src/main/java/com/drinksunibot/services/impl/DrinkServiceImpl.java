package com.drinksunibot.services.impl;

import com.drinksunibot.model.Drink;
import com.drinksunibot.repositories.DrinkRepository;
import com.drinksunibot.services.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrinkServiceImpl implements DrinkService {
	
	private final DrinkRepository drinkRepository;
	
	@Autowired
	public DrinkServiceImpl(DrinkRepository drinkRepository) {
		this.drinkRepository = drinkRepository;
		
		// todo just for this task, not for real life case
		createAndFillCollectionDrinkIfNotExist();
	}
	
	@Override
	public List<Drink> findAll() {
		return drinkRepository.findAll();
	}
	
	@Override
	public List<Drink> getAllByName(String name) {
		return drinkRepository.getAllByName(name);
	}
	
	public Drink getFirstByName(String name) {
		return drinkRepository.getFirstByName(name);
	}
	
	// todo just for this task, not for real life case
	public void createAndFillCollectionDrinkIfNotExist() {
		
		if (drinkRepository.getAllByName("Cognac").stream().count() == 0) {
			Drink drink = new Drink();
			drink.setName("Cognac");
			drink.setDesctiption("Cognac (/ˈkɒnjæk/ KON-yak or /ˈkoʊnjæk/ KOHN-yak; French pronunciation: [kɔ.ɲak]) is a variety of brandy " + "named after the town of Cognac, France. It is produced in the surrounding wine-growing region in the departments " + "of Charente and Charente-Maritime.");
			drinkRepository.save(drink);
		}
		
		if (drinkRepository.getAllByName("Whiskey").stream().count() == 0) {
			Drink drink = new Drink();
			drink.setName("Whiskey");
			drink.setDesctiption("Whisky or whiskey is a type of distilled alcoholic beverage made from fermented grain mash. " + "Various grains (which may be malted) are used for different varieties, including barley, corn (maize), rye, and wheat." + "Whisky is typically aged in wooden casks, generally made of charred white oak.");
			drinkRepository.save(drink);
		}
		
		if (drinkRepository.getAllByName("Rum").stream().count() == 0) {
			Drink drink = new Drink();
			drink.setName("Rum");
			drink.setDesctiption("Rum is a distilled alcoholic beverage made from sugarcane byproducts, such as molasses or honeys, " + "or directly from sugarcane juice, by a process of fermentation and distillation. The distillate, a clear liquid, " + "is then usually aged in oak barrels.");
			drinkRepository.save(drink);
		}
		
		if (drinkRepository.getAllByName("Vodka").stream().count() == 0) {
			Drink drink = new Drink();
			drink.setName("Vodka");
			drink.setDesctiption("Vodka (from Polish: wódka, Russian: водка [ˈvotkə]) is a distilled beverage composed primarily of water and ethanol, " + "but sometimes with traces of impurities and flavorings. Traditionally, vodka is made through the distillation of cereal grains " + "or potatoes that have been fermented, though some modern brands, such as Ciroc, CooranBong, and Bombora, use fruits or sugar.");
			drinkRepository.save(drink);
		}
		
		if (drinkRepository.getAllByName("Tequila").stream().count() == 0) {
			Drink drink = new Drink();
			drink.setName("Tequila");
			drink.setDesctiption("Tequila (Spanish pronunciation: [teˈkila]) is a regional distilled beverage and type of alcoholic drink " + "made from the blue agave plant, primarily in the area surrounding the city of Tequila, 65 km (40 mi) northwest of Guadalajara, " + "and in the highlands (Los Altos) of the central western Mexican state of Jalisco. Aside from differences in region of origin, " + "tequila is a type of mezcal (and the regions of production of the two drinks are overlapping).");
			drinkRepository.save(drink);
		}
		
		if (drinkRepository.getAllByName("Bloody Mary").stream().count() == 0) {
			Drink drink = new Drink();
			drink.setName("Bloody Mary");
			drink.setDesctiption("A Bloody Mary is a cocktail containing vodka, tomato juice, and combinations of other spices and flavorings including Worcestershire sauce, hot sauce, piri piri sauce, garlic and herb sauce, beef consommé or bouillon, horseradish, celery, olives, salt, black pepper, lemon juice, lime juice and/or celery salt. In the United States, it is usually consumed in the morning or early afternoon, and is popular as a hangover cure.");
			drinkRepository.save(drink);
		}
		
	}
	
}
