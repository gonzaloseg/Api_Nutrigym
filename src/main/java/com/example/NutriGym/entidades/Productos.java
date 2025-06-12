package com.example.NutriGym.entidades;

import jakarta.persistence.*;

@Entity
public class Productos {
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id_producto") // Nombre de la columna en la base de datos
	    private Long id;

	    @Column(name = "nombre", nullable = false, length = 100)
	    private String nombre;

	    @Column(name = "stock", nullable = false)
	    private Integer stock;

	    @Column(name = "precio", nullable = false)
	    private Double precio;

	    @Column(name = "categoria", length = 100)
	    private String categoria;
	    
	    @Column(name = "descripcion", length = 700)
	    private String descripcion;
	    
	    @Column(name = "imagen", length = 500)
	    private String imagen;
	    
	    @Column(name = "imagen2", length = 500)
	    private String imagen2;
	    
	    @Column(name = "imagen3", length = 500)
	    private String imagen3;

	    
	    
	   

		public String getImagen() {
			return imagen;
		}

		public void setImagen(String imagen) {
			this.imagen = imagen;
		}
		 public String getImagen2() {
			return imagen2;
		}
		
		public void setImagen2(String imagen2) {
			this.imagen2 = imagen2;
		}
		
		public String getImagen3() {
			return imagen3;
		}
		
		public void setImagen3(String imagen3) {
		    this.imagen3 = imagen3;
		}
		public String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

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

	    public Integer getStock() {
	        return stock;
	    }

	    public void setStock(Integer stock) {
	        this.stock = stock;
	    }

	    public Double getPrecio() {
	        return precio;
	    }

	    public void setPrecio(Double precio) {
	        this.precio = precio;
	    }

	    public String getCategoria() {
	        return categoria;
	    }

	    public void setCategoria(String categoria) {
	        this.categoria = categoria;
	    }



		public Productos(Long id, String nombre, Integer stock, Double precio, String categoria, String descripcion,
				String imagen, String imagen2, String imagen3) {
			super();
			this.id = id;
			this.nombre = nombre;
			this.stock = stock;
			this.precio = precio;
			this.categoria = categoria;
			this.descripcion = descripcion;
			this.imagen = imagen;
			this.imagen2 = imagen2;
			this.imagen3 = imagen3;
		}

		public Productos() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
	    
	}


