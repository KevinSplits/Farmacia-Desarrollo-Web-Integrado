package pe.edu.utp.farmacia.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "producto")
public class ProductEntity {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idproducto;
    
    private String descripcion;
    private int stock;
    @Column(nullable = false, columnDefinition = "DECIMAL(10,2) DEFAULT 0.0")
    private double precio = 0.0;
    //private double costo;
    //private double precio_venta;
    private String estado;
	public int getIdproducto() {
		return idproducto;
	}
	public void setIdproducto(int idproducto) {
		this.idproducto = idproducto;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

        public double getPrecio() {
            return precio;
        }

        public void setPrecio(double precio) {
            this.precio = precio;
        }
        


  
}
