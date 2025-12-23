
package com.antonyudin.templates.ear.module2.logic;


import java.util.UUID;

import com.antonyudin.templates.ear.module2.model.Item;


public interface Items {

	public Item create(final Item item);
	public Item[] update(final Item ... items);
	public Item[] findAll();
	public Item findByIdentity(final UUID value);
}

