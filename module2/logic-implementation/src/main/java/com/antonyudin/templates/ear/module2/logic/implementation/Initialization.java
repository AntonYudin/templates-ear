
package com.antonyudin.templates.ear.module2.logic.implementation;


import java.io.Serializable;

import java.util.UUID;

import java.util.logging.Logger;

import jakarta.annotation.PostConstruct;

import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.DependsOn;

import jakarta.inject.Inject;

import com.antonyudin.templates.ear.module1.logic.Parents;

import com.antonyudin.templates.ear.module2.model.Item;
import com.antonyudin.templates.ear.module2.logic.Items;


@Singleton
@Startup
@DependsOn("module1.Initialization")
public class Initialization implements Serializable {

	private final Logger logger = Logger.getLogger(Initialization.class.getName());


	@Inject
	private Items items;

	@Inject
	private Parents parents;

	@PostConstruct
	public void postConstruct() {
		logger.info("postConstruct(), " + items + ", " + parents);
		final var parent = parents.findAll()[0];
		final var item = items.create(Item.of("test", parent));
		logger.info("item: [" + item + "]");
		final var item1 = items.findAll()[0];
		item1.setValue("value 2");
		final var updated1 = items.update(item1);
	}

}

