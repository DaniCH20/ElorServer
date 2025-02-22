package socketIO;

public enum Events {
	LOGIN ("login"),
	ERROR_LOGIN("errorLogin"),
	ERROR_USUARIO_NO_EXISTE("errorUsuarioNoExiste"),
	ERROR_CONTRASENA_INCORRECTA("errorContrasenaIncorrecta"),
	RESPUESTA_LOGIN ("respuestaLogin"),
	LOGOUT ("logout"),
	
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
	ERROR_OBTENER_DATOS_DE_USUARIO("respuestaObteneDatosDeUsuario"),
	ERROR_ALUMNO("errorAlumno"),
	ERROR_CICLO("errorCiclo"),
	INSERTAR_USUARIO("insertarUsuario"),
    ON_INSERTAR_USUARIO("respuestaInsertarUsuario"),
    ERROR_INSERTAR_USUARIO("errorInsertarUsuario"),

	RESET_PASSWORD("resetPassword"),
	NEW_PASSWORD("newPassword");

	public final String value;

	private Events(String value) {
		this.value = value;
	}
}
