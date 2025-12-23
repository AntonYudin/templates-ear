
package com.antonyudin.templates.ear.module1.logic.implementation;


import java.io.Serializable;

import java.util.UUID;

import java.util.logging.Logger;

import jakarta.annotation.PostConstruct;

import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

import jakarta.inject.Inject;

import com.antonyudin.templates.ear.module1.model.Parent;
import com.antonyudin.templates.ear.module1.logic.Parents;


@Singleton(name = "module1.Initialization")
@Startup
public class Initialization implements Serializable {

	private final Logger logger = Logger.getLogger(Initialization.class.getName());


	@Inject
	private Parents parents;

	@PostConstruct
	public void postConstruct() {
		logger.info("postConstruct(), " + parents);
		final var parent = parents.create(Parent.of("test"));
		logger.info("parent: [" + parent + "]");
		final var parent1 = parents.findAll()[0];
		logger.info("parent1: [" + parent1 + "]");
		final var parent2 = parents.findAll()[0];
		logger.info("parent2: [" + parent2 + "]");
		parent1.setValue("value 2");
		final var updated1 = parents.update(parent1);
	//	parent2.setValue("value 3");
	//	final var updated2 = parents.update(parent2);
	}

}

