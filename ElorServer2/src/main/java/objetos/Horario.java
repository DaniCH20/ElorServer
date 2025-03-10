package objetos;
// Generated 22 feb 2025, 16:06:35 by Hibernate Tools 6.5.1.Final

import java.sql.Time;

/**
 * Horario generated by hbm2java
 */
public class Horario implements java.io.Serializable {

	private Integer id;
	private Asignatura asignatura;
	private Usuario usuario;
	private String dia;
	private Time horaInicio;
	private Time horaFin;

	public Horario() {
	}

	public Horario(Asignatura asignatura, Usuario usuario, String dia, Time horaInicio, Time horaFin) {
		this.asignatura = asignatura;
		this.usuario = usuario;
		this.dia = dia;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Asignatura getAsignatura() {
		return this.asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getDia() {
		return this.dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public Time getHoraInicio() {
		return this.horaInicio;
	}

	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Time getHoraFin() {
		return this.horaFin;
	}

	public void setHoraFin(Time horaFin) {
		this.horaFin = horaFin;
	}

}
