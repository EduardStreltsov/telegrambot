package com.drinksunibot;

import com.drinksunibot.services.impl.DrinkServiceImpl;
import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.toIntExact;

public class Bot extends TelegramLongPollingBot {
	
	private DrinkServiceImpl drinkService;
	
	public void setDrinkService(DrinkServiceImpl drinkService) {
		this.drinkService = drinkService;
	}
	
	@Override
	public String getBotUsername() {
		return "drinksunibot";
	}
	
	@Override
	public String getBotToken() {
		return "558732077:AAExwykj_xMDPrN1hsiXadP7fOh7u3UNzpY";
	}
	
	@Override
	public void onUpdateReceived(Update update) {
		
		// We check if the update has a message and the message has text
		if (update.hasMessage() && update.getMessage().hasText()) {
			
			// Set variables
			String messageText = update.getMessage().getText();
			long chatId = update.getMessage().getChatId();
			
			if (messageText.equalsIgnoreCase("/start")) {
				
				start(chatId);
				
			} else if (messageText.equalsIgnoreCase("/start2")) {
				
				// just for fun
				start2(chatId);
				
			} else if (messageText.equalsIgnoreCase("Whiskey")
					|| messageText.equalsIgnoreCase("Rum")
					|| messageText.equalsIgnoreCase("Cognac")
					|| messageText.equalsIgnoreCase("Vodka")
					|| messageText.equalsIgnoreCase("Tequila")
					|| messageText.equalsIgnoreCase("Bloody Mary")) {
				
				sendDrinkInformation(messageText, chatId, false);
				
			} else if (messageText.equalsIgnoreCase("Exit")) {
				exit2(chatId);
			} else {
				
				SendMessage message = new SendMessage().setChatId(chatId).setText(textWithEmoji("It looks like someone is drunk :smile: Let me help you"));
				
				InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
				List<List<InlineKeyboardButton>> rows = new ArrayList<>();
				List<InlineKeyboardButton> row = new ArrayList<>();
				
				row.add(new InlineKeyboardButton().setText("/Start").setCallbackData("/Start"));
				row.add(new InlineKeyboardButton().setText("/Start2").setCallbackData("/Start2"));
				
				rows.add(row);
				
				inlineKeyboardMarkup.setKeyboard(rows);
				message.setReplyMarkup(inlineKeyboardMarkup);
				
				executeMessage(message);
			}
		} else if (update.hasCallbackQuery()) {
			
			// Set variables
			String callData = update.getCallbackQuery().getData();
			long messageId = update.getCallbackQuery().getMessage().getMessageId();
			long chatId = update.getCallbackQuery().getMessage().getChatId();
			String previousText = update.getCallbackQuery().getMessage().getText();
			
			if (callData.equalsIgnoreCase("Whiskey")
					|| callData.equalsIgnoreCase("Rum")
					|| callData.equalsIgnoreCase("Cognac")
					|| callData.equalsIgnoreCase("Vodka")
					|| callData.equalsIgnoreCase("Tequila")
					|| callData.equalsIgnoreCase("Bloody Mary")) {
				
				removeInlineKeyboardFromMessage(messageId, chatId, previousText);
				
				sendDrinkInformation(callData, chatId, true);
				
			} else if (callData.equalsIgnoreCase("/Start")) {
				
				start(chatId);
				
			} else if (callData.equalsIgnoreCase("/Start2")) {
				
				removeInlineKeyboardFromMessage(messageId, chatId, previousText);
				start2(chatId);
				
			} else if (callData.equalsIgnoreCase("Exit")) {
				
				removeInlineKeyboardFromMessage(messageId, chatId, previousText);
				
				SendMessage message1 = new SendMessage().setChatId(chatId).setText(sayBye());
				executeMessage(message1);
				
			} else {
				
				SendMessage message1 = new SendMessage().setChatId(chatId).setText(textWithEmoji("Now it looks like I'm drunk :smile:"));
				executeMessage(message1);
			}
		}
		
	}
	
	
	private void executeMessage(SendMessage message) {
		try {
			execute(message);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
	
	private String textWithEmoji(String text) {
		return EmojiParser.parseToUnicode(text);
	}
	
	private void addInlineKeyboardToMessage(SendMessage message) {
		
		InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
		List<List<InlineKeyboardButton>> rows = new ArrayList<>();
		List<InlineKeyboardButton> row = new ArrayList<>();
		
		row.add(new InlineKeyboardButton().setText("Whiskey").setCallbackData("Whiskey"));
		row.add(new InlineKeyboardButton().setText("Rum").setCallbackData("Rum"));
		row.add(new InlineKeyboardButton().setText("Cognac").setCallbackData("Cognac"));
		
		rows.add(row);
		
		row = new ArrayList<>();
		row.add(new InlineKeyboardButton().setText("Vodka").setCallbackData("Vodka"));
		row.add(new InlineKeyboardButton().setText("Tequila").setCallbackData("Tequila"));
		row.add(new InlineKeyboardButton().setText("Bloody Mary").setCallbackData("Bloody Mary"));
		
		rows.add(row);
		
		row = new ArrayList<>();
		row.add(new InlineKeyboardButton().setText("/Start2").setCallbackData("/Start2"));
		row.add(new InlineKeyboardButton().setText("Exit").setCallbackData("Exit"));
		
		rows.add(row);
		
		inlineKeyboardMarkup.setKeyboard(rows);
		message.setReplyMarkup(inlineKeyboardMarkup);
	}
	
	private void removeInlineKeyboardFromMessage(long messageId, long chatId, String previousText) {
		EditMessageText message = new EditMessageText().setChatId(chatId).setMessageId(toIntExact(messageId)).setText(previousText);
		try {
			execute(message);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
	
	private String sayHi() {
		return textWithEmoji("Hi! Choose a drink, please :tropical_drink:");
	}
	
	private String sayBye() {
		return textWithEmoji("Bye! :wave:");
	}
	
	private void start(long chatId) {
		
		SendMessage message = new SendMessage().setChatId(chatId).setText(sayHi());
		
		addInlineKeyboardToMessage(message);
		
		executeMessage(message);
	}
	
	private void start2(long chatId) {
		
		SendMessage message = new SendMessage().setChatId(chatId).setText(sayHi());
		
		ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
		List<KeyboardRow> keyboard = new ArrayList<>();
		
		KeyboardRow row = new KeyboardRow();
		
		row.add("Whiskey");
		row.add("Rum");
		row.add("Cognac");
		
		keyboard.add(row);
		
		row = new KeyboardRow();
		row.add("Vodka");
		row.add("Tequila");
		row.add("Bloody Mary");
		keyboard.add(row);
		
		row = new KeyboardRow();
		row.add("Exit");
		keyboard.add(row);
		
		keyboardMarkup.setKeyboard(keyboard);
		message.setReplyMarkup(keyboardMarkup);
		
		executeMessage(message);
	}
	
	private void sendDrinkInformation(String messageText, long chatId, boolean withKeyboard) {
		
		String answer = textWithEmoji(drinkService.getFirstByName(messageText).getDesctiption());
		SendMessage message = new SendMessage().setChatId(chatId).setText(answer);
		if (withKeyboard) {
			addInlineKeyboardToMessage(message);
		}
		executeMessage(message);
	}
	
	private void exit2(long chatId) {
		SendMessage message = new SendMessage().setChatId(chatId).setText(sayBye());
		ReplyKeyboardRemove keyboardMarkup = new ReplyKeyboardRemove();
		message.setReplyMarkup(keyboardMarkup);
		executeMessage(message);
	}
	
}
