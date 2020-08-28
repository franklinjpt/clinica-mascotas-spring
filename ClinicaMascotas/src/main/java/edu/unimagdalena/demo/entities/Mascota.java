package edu.unimagdalena.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "mascotas")
public class Mascota {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;
	@Column
	private String nombre;
	@Enumerated(EnumType.STRING)
	@Column
	private Tipo tipo;
	@Column
	private String fechaNac;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_propietario", nullable = false)
	private Propietario propietario;
	
	public Mascota() {
		// TODO Auto-generated constructor stub
	}

	public Mascota(String nombre, Tipo tipo, String fechaNac, Propietario propietario) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.fechaNac = fechaNac;
		this.propietario = propietario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public String getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}

	public Propietario getPropietario() {
		return propietario;
	}

	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}
	
	
}
