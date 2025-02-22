package objetos;
// Generated 22 feb 2025, 16:06:35 by Hibernate Tools 6.5.1.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Usuario generated by hbm2java
 */
public class Usuario implements java.io.Serializable {

	private Integer id;
	private String login;
	private String dni;
	private String clave;
	private String nombre;
	private String apellido;
	private String telefono1;
	private String telefono2;
	private byte[] foto;
	private String direccion;
	private Boolean existe;
	private String tipoUsuario;
	private Profesor profesor;
	private Alumno alumno;
	private Set horarios = new HashSet(0);
	private Set rolUsuarios = new HashSet(0);

	public Usuario() {
	}

	public Usuario(String login, String dni, String clave, String nombre, String apellido, String tipoUsuario) {
		this.login = login;
		this.dni = dni;
		this.clave = clave;
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipoUsuario = tipoUsuario;
	}

	public Usuario(String login, String dni, String clave, String nombre, String apellido, String telefono1,
			String telefono2, byte[] foto, String direccion, Boolean existe, String tipoUsuario, Profesor profesor,
			Alumno alumno, Set horarios, Set rolUsuarios) {
		this.login = login;
		this.dni = dni;
		this.clave = clave;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono1 = telefono1;
		this.telefono2 = telefono2;
		this.foto = foto;
		this.direccion = direccion;
		this.existe = existe;
		this.tipoUsuario = tipoUsuario;
		this.profesor = profesor;
		this.alumno = alumno;
		this.horarios = horarios;
		this.rolUsuarios = rolUsuarios;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono1() {
		return this.telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	public String getTelefono2() {
		return this.telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	public byte[] getFoto() {
		return this.foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Boolean getExiste() {
		return this.existe;
	}

	public void setExiste(Boolean existe) {
		this.existe = existe;
	}

	public String getTipoUsuario() {
		return this.tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public Profesor getProfesor() {
		return this.profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public Alumno getAlumno() {
		return this.alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Set getHorarios() {
		return this.horarios;
	}

	public void setHorarios(Set horarios) {
		this.horarios = horarios;
	}

	public Set getRolUsuarios() {
		return this.rolUsuarios;
	}

	public void setRolUsuarios(Set rolUsuarios) {
		this.rolUsuarios = rolUsuarios;
	}

}
