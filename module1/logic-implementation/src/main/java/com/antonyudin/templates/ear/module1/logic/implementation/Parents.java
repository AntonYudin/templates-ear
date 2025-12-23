
package com.antonyudin.templates.ear.module1.logic.implementation;


import java.util.UUID;
import java.util.ArrayList;

import jakarta.ejb.Stateless;

import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;

import jakarta.persistence.criteria.CriteriaBuilder;

import com.antonyudin.templates.ear.module1.model.Parent;
import com.antonyudin.templates.ear.module1.model.Parent_;


@Stateless
public class Parents implements com.antonyudin.templates.ear.module1.logic.Parents {


	@PersistenceContext(unitName = "default")
	private EntityManager entityManager;


	@Override
	public Parent create(final Parent value) {
		value.setIdentity(UUID.randomUUID());
		entityManager.persist(value);
		entityManager.flush();
		return value;
	}

	@Override
	public Parent[] update(final Parent ... parents) {
		final var result = new ArrayList<>();
		for (var parent: parents)
			result.add(entityManager.merge(parent));
		entityManager.flush();
		return result.toArray(new Parent[result.size()]);
	}

	@Override
	public Parent[] findAll() {
		final var criteriaBuilder = entityManager.getCriteriaBuilder();
		final var criteriaQuery = criteriaBuilder.createQuery(Parent.class);
		final var from = criteriaQuery.from(Parent.class);
		criteriaQuery.orderBy(criteriaBuilder.asc(from.get(Parent_.value)));
		criteriaQuery.distinct(true);
		criteriaQuery.select(from);
		final var result = entityManager.createQuery(criteriaQuery).getResultList();
		entityManager.clear();
		return result.toArray(new Parent[result.size()]);
	}

}

