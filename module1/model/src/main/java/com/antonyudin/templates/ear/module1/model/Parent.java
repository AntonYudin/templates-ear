
package com.antonyudin.templates.ear.module1.model;


import java.io.Serializable;

import java.time.LocalDateTime;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import jakarta.persistence.Column;


@Entity
public class Parent implements Serializable {

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


	public static Parent of(final String value) {
		final var result = new Parent();
		result.setValue(value);
		return result;
	}

}

