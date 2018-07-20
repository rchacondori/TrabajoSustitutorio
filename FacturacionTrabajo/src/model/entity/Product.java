package model.entity;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Index;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Product {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) private Long id;
	@Persistent @Index private Integer codigoBarra;
	@Persistent private String nombre;
	@Persistent private double precioFinal;
	
	@Persistent private String unidadId;
	@Persistent private String categoriaId;
	@Persistent private String almacenId;
	
	@Persistent private String descripcion;
	@Persistent private boolean state;
	
	@Persistent private String madeBy;

	/*public Product(Integer codigoBarra, String nombre, double precioFinal, Long unidadId, Long categoriaId,
			Long almacenId, String descripcion, boolean state) {
		super();
		this.codigoBarra = codigoBarra;
		this.nombre = nombre;
		this.precioFinal = precioFinal;
		this.unidadId = unidadId;
		this.categoriaId = categoriaId;
		this.almacenId = almacenId;
		this.descripcion = descripcion;
		this.state = state;
	}*/
	public Product(String codigoBarra, String nombre, String precioFinal, String unidadId, String categoriaId,
			String almacenId, String descripcion, boolean state, String mail) {
		super();
		this.codigoBarra = Integer.parseInt(codigoBarra);
		this.nombre = nombre;
		this.precioFinal = Double.parseDouble(precioFinal);
		this.unidadId = unidadId;
		this.categoriaId = categoriaId;
		this.almacenId = almacenId;
		this.descripcion = descripcion;
		this.state = state;
		this.madeBy = mail;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getCodigoBarra() {
		return codigoBarra;
	}
	public void setCodigoBarra(Integer codigoBarra) {
		this.codigoBarra = codigoBarra;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPrecioFinal() {
		return precioFinal;
	}
	public void setPrecioFinal(double precioFinal) {
		this.precioFinal = precioFinal;
	}
	public String getUnidadId() {
		return unidadId;
	}
	public void setUnidadId(String unidadId) {
		this.unidadId = unidadId;
	}
	public String getCategoriaId() {
		return categoriaId;
	}
	public void setCategoriaId(String categoriaId) {
		this.categoriaId = categoriaId;
	}
	public String getAlmacenId() {
		return almacenId;
	}
	public void setAlmacenId(String almacenId) {
		this.almacenId = almacenId;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
}