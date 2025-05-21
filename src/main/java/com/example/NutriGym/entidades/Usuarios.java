package com.example.NutriGym.entidades;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Usuarios {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id_usuario") // Nombre de la columna en la base de datos
	    private Long id;
	    
	    @Column(name = "foto", nullable = false, length = 250)
	    private String foto;
	    
	    @Column(name = "nombre", nullable = false, length = 100)
	    private String nombre;

	    @Column(name = "correo_electronico", nullable = false, unique = true, length = 150)
	    private String correoElectronico;
	    
	    @Column(name = "rol_usuario", nullable = false, unique = true, length = 150)
	    private String rol_usuario; 
	    
	    public String getFoto() {
			return foto;
		}

		public void setFoto(String foto) {
			this.foto = foto;
		}

		@Column(name="contrasena", nullable = false)
	    private String contrasena;

	    @Column(name = "altura")
	    private Double altura;

	    @Column(name = "peso")
	    private Double peso;

	    // Getters y Setters
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

	    public String getCorreoElectronico() {
	        return correoElectronico;
	    }

	    public void setCorreoElectronico(String correoElectronico) {
	        this.correoElectronico = correoElectronico;
	    }

	    public Double getAltura() {
	        return altura;
	    }

	    public void setAltura(Double altura) {
	        this.altura = altura;
	    }

	    public Double getPeso() {
	        return peso;
	    }

	    public void setPeso(Double peso) {
	        this.peso = peso;
	    }

		public String getContrasena() {
			return contrasena;
		}

		public void setContrasena(String contrasena) {
			this.contrasena = contrasena;
		}

		

		public String getRol_usuario() {
			return rol_usuario;
		}

		public void setRol_usuario(String rol_usuario) {
			this.rol_usuario = rol_usuario;
		}

		

		public Usuarios(Long id, String foto, String nombre, String correoElectronico, String rol_usuario,
				String contrasena, Double altura, Double peso) {
			super();
			this.id = id;
			this.foto = foto;
			this.nombre = nombre;
			this.correoElectronico = correoElectronico;
			this.rol_usuario = rol_usuario;
			this.contrasena = contrasena;
			this.altura = altura;
			this.peso = peso;
		}

		public Usuarios() {
			super();
			// TODO Auto-generated constructor stub
		}

		
		
	    
	}


