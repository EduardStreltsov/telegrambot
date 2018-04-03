package com.drinksunibot.configs;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.drinksunibot.repositories")
public class MongoConfig extends AbstractMongoConfiguration {
	
	@Override
	protected String getDatabaseName() {
		return "drinksunibot";
	}
	
	@Override
	public MongoClient mongoClient() {
		MongoClientURI clientURI = new MongoClientURI("mongodb://localhost:27017");
		return new MongoClient(clientURI);
	}
	
	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongoClient(), getDatabaseName());
	}
	
}