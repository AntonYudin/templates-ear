
package com.antonyudin.templates.ear.module2.ui.web;


import java.io.Serializable;

import java.util.logging.Logger;

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
public class ItemsView implements Serializable {

	private final static Logger logger = Logger.getLogger(ItemsView.class.getName());

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
		}
	}

	private Item[] itemsList = null;

	public Item[] getItemsList() {
		return itemsList;
	}

	public void search() throws Exception {
		itemsList = items.findAll(); 
		addMessage(FacesMessage.SEVERITY_INFO, "findAll successful", "" + itemsList.length);
	}

}

