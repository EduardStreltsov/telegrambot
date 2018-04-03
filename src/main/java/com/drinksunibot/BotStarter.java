package com.drinksunibot;

import com.drinksunibot.services.impl.DrinkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

@Component
public class BotStarter {
	
	@Autowired
	public BotStarter(DrinkServiceImpl drinkService) {
		
		ApiContextInitializer.init();
		TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
		Bot bot = new Bot();
		bot.setDrinkService(drinkService);
		try {
			telegramBotsApi.registerBot(bot);
			
			System.out.println();
			System.out.println("****************************************************************************");
			System.out.println("@drinksunibot registred successfully and works now!");
			System.out.println("It works with MongoDB (mongodb://localhost:27017) without user and password");
			System.out.println("All data needed will be written on start automatically.");
			System.out.println("The bot has two modes: /start and /start2 (just for fun)");
			System.out.println("Choose your drink :)");
			System.out.println();
			System.out.println();
			System.out.println("Best regards");
			System.out.println("Eduard Streltsov");
			System.out.println("e-mail: streltsov.eduard@gmail.com");
			System.out.println("phone: +38 (095) 389-43-15");
			System.out.println("****************************************************************************");
			System.out.println();
			
		} catch (TelegramApiRequestException e) {
			e.printStackTrace();
		}
		
	}
	
}
