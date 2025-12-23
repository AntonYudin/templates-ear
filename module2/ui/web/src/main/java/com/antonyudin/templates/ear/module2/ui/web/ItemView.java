
package com.antonyudin.templates.ear.module2.ui.web;


import java.io.Serializable;

import java.util.logging.Logger;

import java.util.UUID;

import jakarta.faces.application.FacesMessage;

import jakarta.inject.Inject;
import jakarta.inject.Named;

import jakarta.enterprise.context.ConversationScoped;
import jakarta.enterprise.context.Conversation;

import jakarta.faces.context.FacesContext;

import com.antonyudin.templates.ear.module2.model.Item;
import com.antonyudin.templates.ear.module2.logic.Items;


@ConversationScoped
@Named
public class ItemView implements Serializable {

	private final static Logger logger = Logger.getLogger(ItemView.class.getName());

	@Inject
	private Items items;


	protected void addMessage(final FacesMessage.Severity severity, final String summary, final String detail) {
		FacesContext.getCurrentInstance().addMessage(
			null,
			new FacesMessage(severity, summary, detail)
		);
	}


	@Inject
	private Conversation conversation;

	public void initialize() {
		if (conversation.isTransient()) {
			conversation.begin();

			item = items.findByIdentity(UUID.fromString(getIdentity()));
		}
	}

	private String identity;

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(final String value) {
		identity = value;
	}

	private Item item = null;

	public Item getItem() {
		return item;
	}

	public void update() {
		item = items.update(item)[0];
		addMessage(FacesMessage.SEVERITY_INFO, "Item updated sucessfully", "" + item.getIdentity());
	}

}

