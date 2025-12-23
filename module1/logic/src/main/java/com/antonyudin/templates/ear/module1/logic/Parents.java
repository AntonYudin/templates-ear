
package com.antonyudin.templates.ear.module1.logic;


import com.antonyudin.templates.ear.module1.model.Parent;


public interface Parents {

	public Parent create(final Parent parent);
	public Parent[] update(final Parent ... parents);
	public Parent[] findAll();
}

