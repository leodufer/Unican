package py.edu.unican.unicanapp.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Alumno {
	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField
	private String nombre;
	@DatabaseField
	private String cedula;
	@DatabaseField
	private String codigo;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Alumno [nombre=" + nombre + ", cedula=" + cedula + ", codigo="
				+ codigo + "]";
	}
}
