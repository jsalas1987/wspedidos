package edu.ucla.wspedido.dominio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="pedido")
@XmlRootElement
public class Pedido {
	
	@Id
	@GeneratedValue
	private Integer id;
	@Column(name="descripcion")
	private String descripcion;
	@Column(name="cantidad")
	private Integer cantidad;
	@Column(name="unidad")
	private String unidad;
	@Column(name="fecha")
	private Date fecha;
	@Column(name="dias")
	private Integer diasEntrega;
	@ManyToOne
	@JoinColumn(name="id_producto")
	private Producto producto;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public String getUnidad() {
		return unidad;
	}
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Integer getDiasEntrega() {
		return diasEntrega;
	}
	public void setDiasEntrega(Integer diasEntrega) {
		this.diasEntrega = diasEntrega;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
}
