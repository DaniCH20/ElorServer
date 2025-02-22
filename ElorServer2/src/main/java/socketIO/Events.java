package socketIO;

public enum Events {
	LOGIN ("login"),
	LOGOUT ("logout"),
	RESPUESTA_LOGIN ("respuestaLogin"),
	VERIFICAR_CLAVE ("verificarClave"),
	ON_VERIFICAR_CLAVE ("respuestaVerificarClave"),
	ERROR_VERIFICAR_CLAVE ("errorVerificarClave"),
    ACTUALIZAR_CLAVE ("actualizarClave"),
    ON_ACTUALIZAR_CLAVE ("respuestaActualizarClave"),
    ERROR_ACTUALIZAR_CLAVE ("errorActualizarClave"),
	OBTENER_DATOS_DE_USUARIO("obtenerDatosDeUsuario"),
	OBTENER_ALUMNO("obtenerAlumno"),
	OBTENER_CICLO("obtenerCiclo"),
	ON_OBTENER_DATOS_DE_USUARIO("respuestaObteneDatosDeUsuario"),
	ON_OBTENER_ALUMNO("respuestaObteneDatosDeAlumno"),
	ON_OBTENER_CICLO("respuestaObteneDatosDeCiclo"),
	ERROR_LOGIN("errorLogin"),
	ERROR_ALUMNO("errorAlumno"),
	INSERTAR_USUARIO("insertarUsuario"),
    ON_INSERTAR_USUARIO("respuestaInsertarUsuario"),
    ERROR_INSERTAR_USUARIO("errorInsertarUsuario"),
	ERROR_CONTRASENA_INCORRECTA("errorContrasenaIncorrecta"),
	RESET_PASSWORD("resetPassword"),
	NEW_PASSWORD("newPassword");

	public final String value;

	private Events(String value) {
		this.value = value;
	}
}
