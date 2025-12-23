
package com.antonyudin.templates.ear.module2.model;


import java.io.Serializable;

import java.time.LocalDateTime;

import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OrderColumn;

import com.antonyudin.templates.ear.module1.model.Parent;


@Entity
public class Item implements Serializable {

	private UUID identity;

	@Id
	public UUID getIdentity() {
		return identity;
	}

	public void setIdentity(final UUID value) {
		identity = value;
	}


	private LocalDateTime changedAt;

	@Version
	public LocalDateTime getChangedAt() {
		return changedAt;
	}

	public void setChangedAt(final LocalDateTime value) {
		changedAt = value;
	}


	private String value;

	@Column(columnDefinition = "TEXT")
	public String getValue() {
		return value;
	}

	public void setValue(final String value) {
		this.value = value;
	}


	public static Item of(final String value, final Parent ... parents) {
		final var result = new Item();
		result.setValue(value);
		result.setParents(List.of(parents));
		return result;
	}


	private List<Parent> parents = new ArrayList<>();

	@ManyToMany
	@OrderColumn(name = "parents_order")
	public List<Parent> getParents() {
		return parents;
	}

	public void setParents(final List<Parent> value) {
		parents = value;
	}

}

