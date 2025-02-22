package objetos;
// Generated 22 feb 2025, 16:06:35 by Hibernate Tools 6.5.1.Final

import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

/**
 * Reunion generated by hbm2java
 */
public class Reunion implements java.io.Serializable {

	private Integer id;
	private Estado estado;
	private Profesor profesor;
	private String titulo;
	private String asunto;
	private Date dia;
	private Time hora;
	private String aula;
	private Set reunionInvitadoses = new HashSet(0);

	public Reunion() {
	}

	public Reunion(Estado estado, Profesor profesor, String titulo, Date dia, Time hora, String aula) {
		this.estado = estado;
		this.profesor = profesor;
		this.titulo = titulo;
		this.dia = dia;
		this.hora = hora;
		this.aula = aula;
	}

	public Reunion(Estado estado, Profesor profesor, String titulo, String asunto, Date dia, Time hora, String aula,
			Set reunionInvitadoses) {
		this.estado = estado;
		this.profesor = profesor;
		this.titulo = titulo;
		this.asunto = asunto;
		this.dia = dia;
		this.hora = hora;
		this.aula = aula;
		this.reunionInvitadoses = reunionInvitadoses;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Profesor getProfesor() {
		return this.profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAsunto() {
		return this.asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public Date getDia() {
		return this.dia;
	}

	public void setDia(Date dia) {
		this.dia = dia;
	}

	public Time getHora() {
		return this.hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public String getAula() {
		return this.aula;
	}

	public void setAula(String aula) {
		this.aula = aula;
	}

	public Set getReunionInvitadoses() {
		return this.reunionInvitadoses;
	}

	public void setReunionInvitadoses(Set reunionInvitadoses) {
		this.reunionInvitadoses = reunionInvitadoses;
	}

}
