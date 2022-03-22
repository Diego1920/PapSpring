package org.diego.pap2021.entities;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String login;

	private String nombre;
	private String apellido;
	
	//se tiene que hacer la conexion por los dos lados
	@ManyToOne
	private Pais nace;
	
	@ManyToOne
	private Pais vive;
	
	
	
	public Persona() {
		
	}
	//constructor con todos los datos que se le pasan
	public Persona(String login, String nombre, String apellido,Pais nace, Pais vive) {
		this.login = login;
		this.nombre = nombre;
		this.apellido = apellido;
	
		if (nace!=null) {
			this.nace = nace;
			this.nace.getNativos().add(this);
		}
		
		if (vive!=null) {
			this.vive = vive;
			this.vive.getHabitantes().add(this);
		}
	
	
	}

	
	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	
	//============
	public Pais getNace() {
		return nace;
	}

	public void setNace(Pais nace) {
		this.nace = nace;
		this.nace.getNativos().add(this);
	}
	
	
	public Pais getVive() {
		return vive;
	}

	public void setVive(Pais vive) {
		this.vive = vive;
		this.vive.getHabitantes().add(this);
	}
	
}
