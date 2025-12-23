
package com.antonyudin.templates.ear.module2.logic.implementation;


import java.util.logging.Logger;

import java.util.UUID;
import java.util.ArrayList;

import jakarta.ejb.Stateless;

import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;

import jakarta.persistence.criteria.CriteriaBuilder;

import com.antonyudin.templates.ear.module1.model.Parent;

import com.antonyudin.templates.ear.module2.model.Item;
import com.antonyudin.templates.ear.module2.model.Item_;


@Stateless
public class Items implements com.antonyudin.templates.ear.module2.logic.Items {

	private final static Logger logger = Logger.getLogger(Items.class.getName());


	@PersistenceContext(unitName = "default")
	private EntityManager entityManager;


	@Override
	public Item create(final Item value) {
		value.setIdentity(UUID.randomUUID());
		entityManager.persist(value);
		entityManager.flush();
		return value;
	}

	@Override
	public Item[] update(final Item ... parents) {
		final var result = new ArrayList<>();
		for (var parent: parents)
			result.add(entityManager.merge(parent));
		entityManager.flush();
		return result.toArray(new Item[result.size()]);
	}

	@Override
	public Item[] findAll() {
		final var criteriaBuilder = entityManager.getCriteriaBuilder();
		final var criteriaQuery = criteriaBuilder.createQuery(Item.class);
		final var from = criteriaQuery.from(Item.class);
		criteriaQuery.orderBy(criteriaBuilder.asc(from.get(Item_.value)));
		criteriaQuery.distinct(true);
		criteriaQuery.select(from);
		from.fetch(Item_.parents);
		final var result = entityManager.createQuery(criteriaQuery).getResultList();
		entityManager.clear();
		logger.info("findAll(): " + result.size());
		return result.toArray(new Item[result.size()]);
	}

	@Override
	public Item findByIdentity(final UUID identity) {
		final var criteriaBuilder = entityManager.getCriteriaBuilder();
		final var criteriaQuery = criteriaBuilder.createQuery(Item.class);
		final var from = criteriaQuery.from(Item.class);
		criteriaQuery.orderBy(criteriaBuilder.asc(from.get(Item_.value)));
		criteriaQuery.distinct(true);
		criteriaQuery.select(from);
		criteriaQuery.where(
			criteriaBuilder.equal(
				from.get(Item_.identity),
				identity
			)
		);
		from.fetch(Item_.parents);
		final var result = entityManager.createQuery(criteriaQuery).getSingleResult();
		entityManager.clear();
		logger.info("findByIdentity(): " + result);
		return result;
	}

}

