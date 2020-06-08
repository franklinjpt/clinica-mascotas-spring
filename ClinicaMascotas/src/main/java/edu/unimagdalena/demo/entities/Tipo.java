package edu.unimagdalena.demo.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Tipo {
	PERRO("Perro"),
	LORO("Loro"),
	GATO("Gato"),
	HAMSTER("Hamster");
	
	private String displayName;

    Tipo(String displayName) {
        this.displayName = displayName;
    }

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
