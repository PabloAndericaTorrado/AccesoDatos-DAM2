package Entities;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


public class Mascota {


   
    private Long id;


    private String nombre;


    private String raza;


    private Propietario propietario;

   
    private Cuidador cuidador;

	public Mascota(Long id, String nombre, String raza, Propietario propietario, Cuidador cuidador) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.raza = raza;
		this.propietario = propietario;
		this.cuidador = cuidador;
	}

	public Mascota() {
		super();
	}

	public Mascota(String nombre, String raza, Propietario propietario, Cuidador cuidador) {
		super();
		this.nombre = nombre;
		this.raza = raza;
		this.propietario = propietario;
		this.cuidador = cuidador;
	}
	
	

	public Mascota(String nombre, String raza) {
		super();
		this.nombre = nombre;
		this.raza = raza;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public Propietario getPropietario() {
		return propietario;
	}

	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}

	public Cuidador getCuidador() {
		return cuidador;
	}

	public void setCuidador(Cuidador cuidador) {
		this.cuidador = cuidador;
	}

	@Override
	public String toString() {
		return "Mascota [id=" + id + ", nombre=" + nombre + ", raza=" + raza + ", cuidador=" + cuidador + "]";
	}


	


	


    
}
