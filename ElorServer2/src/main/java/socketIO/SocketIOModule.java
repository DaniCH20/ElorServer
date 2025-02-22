package socketIO;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import menu.Consultas;
import menu.Encriptar;
import objetos.Alumno;
import objetos.Ciclo;
import objetos.Usuario;

public class SocketIOModule {

	private SocketIOServer server = null;
	private final Gson gson = new Gson();
	private Consultas consulta = new Consultas();
	private Encriptar cifrado=new Encriptar ();
	public SocketIOModule(SocketIOServer server) {
		super();
		this.server = server;
		// Eventos por defecto (para controlar la conexión de clientes)
		server.addConnectListener(onConnect());
		server.addDisconnectListener(onDisconnect());
		// Eventos propios
		server.addEventListener(Events.LOGIN.value, MessageInput.class, this.login());
		server.addEventListener(Events.LOGOUT.value, MessageInput.class, this.logout());
		server.addEventListener(Events.VERIFICAR_CLAVE.value, MessageInput.class, this.verificarClave());
		server.addEventListener(Events.OBTENER_DATOS_DE_USUARIO.value, MessageInput.class, this.obtenerDatosUser());
		server.addEventListener(Events.OBTENER_ALUMNO.value, MessageInput.class, this.obtenerDatosAlumno());
		server.addEventListener(Events.OBTENER_CICLO.value, MessageInput.class, this.obtenerDatosCiclo());
		server.addEventListener(Events.INSERTAR_USUARIO.value, MessageInput.class, this.insertarUsuario());
		server.addEventListener(Events.ACTUALIZAR_CLAVE.value, MessageInput.class, this.actualizarClave());
		//server.addEventListener(Events.NEW_PASSWORD.value, MessageInput.class, this.newPassword());
	}

	private ConnectListener onConnect() {
		return (client -> {
			client.joinRoom("default-room");
			System.out.println("Nueva conexión, cliente: " + client.getRemoteAddress());
		});
	}

	private DisconnectListener onDisconnect() {
		return (client -> {
			client.leaveRoom("default-room");
			System.out.println(client.getRemoteAddress() + "desconectado del servidor");
		});
	}

	// Server control
	public void start() {
		server.start();
		System.out.println("Arrancando servidor...");
	}

	public void stop() {
		server.stop();
		System.out.println("Servidor detenido");
	}

	private DataListener<MessageInput> login() {
		return ((client, data, ackSender) -> { // data es el JSON
			System.out.println("El cliente " + client.getRemoteAddress() + " quiere iniciar sesión");
			System.out.println(data.getMessage());
			// El JSON de MessageInput
			String message = data.getMessage();

			JsonObject jsonObject = new Gson().fromJson(message, JsonObject.class);
			try {
				String login = jsonObject.get("login").getAsString();
				String clave = jsonObject.get("password").getAsString();
				if (jsonObject.get("login") == null || jsonObject.get("password") == null) {
					client.sendEvent(Events.ERROR_LOGIN.value, new MessageOutput("Datos incompletos"));
					return;
				}
				System.out.println(login + ":" + clave);

				// String nombreUsuario = jsonObject.get("message").getAsString();
				// Accede a BBDD, toma los datos del objeto y responde
				// Verificar si el usuario existe
				Usuario email = consulta.existeUsuario(login);
				if (email == null) {
					JsonObject json = new JsonObject();
					json.addProperty("message", "Usuario no existe");
					String answerMessage = gson.toJson(json);
					MessageOutput messageOutput = new MessageOutput(answerMessage);
					client.sendEvent(Events.ERROR_USUARIO_NO_EXISTE.value, messageOutput);
				} else {
					// Verificar si la contraseña es correcta
					Usuario user = consulta.obtenerDatosUsuario(login, clave);
					if (user != null) {
						System.out.println("Usuario y contraseña correctos.");
						Usuario user2 = new Usuario();
						user2.setClave(user.getClave());
						user2.setExiste(user.getExiste());
						user2.setNombre(user.getNombre());
						user2.setTipoUsuario(user.getTipoUsuario());
						String answerMessage = gson.toJson(user2);
						// Reenviamos al cliente con MessageOutput
						System.out.println("JSON Enviado: " + answerMessage);
						MessageOutput messageOutput = new MessageOutput(answerMessage);
						client.sendEvent(Events.RESPUESTA_LOGIN.value, messageOutput);
					} else {
						System.out.println("Contraseña incorrecta.");
						JsonObject json = new JsonObject();
						json.addProperty("message", "Contraseña incorrecta");
						String answerMessage = gson.toJson(json);
						MessageOutput messageOutput = new MessageOutput(answerMessage);
						client.sendEvent(Events.ERROR_CONTRASENA_INCORRECTA.value, messageOutput);

					}
				}
			} catch (Exception e) {
				System.out.println("Error : " + e);
				client.sendEvent(Events.ERROR_LOGIN.value, new MessageOutput("Error del servidor"));
			}
		});
	}

	private DataListener<MessageInput> logout() {
		return ((client, data, ackSender) -> {
			System.out.println("El cliente " + client.getRemoteAddress() + " quiere cerrar sesión");
			String message = data.getMessage();
			JsonObject jsonObject = gson.fromJson(message, JsonObject.class);
			String nombreUsuario = jsonObject.get("message").getAsString();
			System.out.println(nombreUsuario + " desconectado");
		});
	}

	/**
	 * EJEMPLO DE BORJA DIVIDIR METODOS EN 2 PARA CAPTURAR EXCEPCIONES
	 * 
	 * @return
	 */
	private DataListener<MessageInput> verificarClave() {
		return ((client, data, ackSender) -> { // data es el JSON
			JsonObject response = new JsonObject();
			Gson gson = new Gson();
			try {
				response = processVerificarClave(client, data, ackSender);
				String repuesta = gson.toJson(response);
				MessageOutput messageOutput = new MessageOutput(repuesta);
				System.out.println("JSON Enviado: " + repuesta);
				client.sendEvent(Events.ON_VERIFICAR_CLAVE.value, messageOutput);
			} catch (Exception e) {
				System.out.println("Error : " + e);
				response.addProperty("message", "Error interno del servidor");
				String repuesta = gson.toJson(response);
				MessageOutput messageOutput = new MessageOutput(repuesta);
				System.out.println("JSON Enviado: " + repuesta);
				client.sendEvent(Events.ERROR_VERIFICAR_CLAVE.value, messageOutput);
			}

		});
	}

	private JsonObject processVerificarClave(SocketIOClient client, MessageInput data, AckRequest ackSender) {
		JsonObject ret = null;

		System.out.println("El cliente " + client.getRemoteAddress() + " quiere verificar su clave");
		System.out.println(data.getMessage());
		String message = data.getMessage();
		JsonObject jsonObject = new Gson().fromJson(message, JsonObject.class);

		String nombre = jsonObject.get("nombre").getAsString();
		String clave = jsonObject.get("clave").getAsString();
		System.out.println("Recibiste nombre: " + nombre + " y una Clave: " + clave);
		ret = new JsonObject();
		Usuario user = consulta.verificarUsuario(nombre, clave);
		if (user != null) {
			ret.addProperty("message", "Correcto");
		} else {
			ret.addProperty("message", "Contraseña desactualizada");
		}

		return ret;
	}

	private DataListener<MessageInput> obtenerDatosUser() {
		return (client, data, ackSender) -> {
			System.out.println("El cliente " + client.getRemoteAddress() + " quiere obtener datos de usuario");
			System.out.println("Mensaje recibido: " + data.getMessage());

			JsonObject jsonObject = new Gson().fromJson(data.getMessage(), JsonObject.class);
			String login = jsonObject.get("login").getAsString();
			String clave = jsonObject.get("password").getAsString();

			System.out.println("Intento de login: " + login + " - " + clave);

			// Verificar si la contraseña es correcta
			Usuario user = consulta.obtenerDatosUsuario(login, clave);

			if (user != null) {
				System.out.println("Usuario y contraseña correctos.");
				Usuario user2 = new Usuario();
				user2.setId(user.getId());
				user2.setApellido(user.getApellido());
				user2.setClave(user.getClave());
				user2.setDireccion(user.getDireccion());
				user2.setDni(user.getDni());
				user2.setExiste(user.getExiste());
				user2.setFoto(user.getFoto());
				user2.setLogin(user.getLogin());
				user2.setNombre(user.getNombre());
				user2.setTelefono1(user.getTelefono1());
				user2.setTelefono2(user.getTelefono2());
				enviarRespuesta(client, user2, Events.ON_OBTENER_DATOS_DE_USUARIO.value);
			} else {
				System.out.println("Contraseña incorrecta.");
				client.sendEvent(Events.ERROR_OBTENER_DATOS_DE_USUARIO.value, new MessageOutput("Error del servidor"));
			}
		};
	}

	private DataListener<MessageInput> obtenerDatosAlumno() {
		return (client, data, ackSender) -> {
			System.out.println("El cliente " + client.getRemoteAddress() + " quiere obtener datos de alumno");
			System.out.println("Mensaje recibido: " + data.getMessage());

			JsonObject jsonObject = new Gson().fromJson(data.getMessage(), JsonObject.class);
			try {
				int id = jsonObject.get("id").getAsInt();
				// Verificar si la contraseña es correcta
				Alumno alumno = consulta.obtenerDatosEstudiante(id);

				if (alumno != null) {
					System.out.println("Alumno");
					Alumno alumno2 = new Alumno();
					alumno2.setCurso(alumno.getCurso());
					alumno2.setDualIntensiva(alumno.getDualIntensiva());
					alumno2.setId(alumno.getCiclo().getId());
					enviarRespuesta(client, alumno2, Events.ON_OBTENER_ALUMNO.value);
				} else {
					System.out.println("Usuario no es alumno: Informacion incompleta");
					String repuesta = "Informacion incompleta";
					MessageOutput messageOutput = new MessageOutput(repuesta);
					client.sendEvent(Events.ERROR_ALUMNO.value, messageOutput);
				}
			} catch (Exception e) {
				System.out.println("Error : " + e);
				client.sendEvent(Events.ERROR_ALUMNO.value, new MessageOutput("Error del servidor"));
			}
		};
	}

	private DataListener<MessageInput> obtenerDatosCiclo() {
		return (client, data, ackSender) -> {
			System.out.println("El cliente " + client.getRemoteAddress() + " quiere obtener datos de Ciclo");
			System.out.println("Mensaje recibido: " + data.getMessage());

			JsonObject jsonObject = new Gson().fromJson(data.getMessage(), JsonObject.class);
			try {
				int id = jsonObject.get("id").getAsInt();
				// Verificar si la contraseña es correcta
				Ciclo ciclo = consulta.obtenerDatosCiclo(id);

				if (ciclo != null) {
					System.out.println("Ciclo");
					Ciclo ciclo2 = new Ciclo();
					ciclo2.setNombre(ciclo.getNombre());
					enviarRespuesta(client, ciclo2, Events.ON_OBTENER_CICLO.value);
				} else {
					System.out.println("No hay ciclo");
					client.sendEvent(Events.ERROR_CICLO.value, new MessageOutput("El alumno no tiene ciclo"));
				}
			} catch (Exception e) {
				System.out.println("Error : " + e);
				client.sendEvent(Events.ERROR_CICLO.value, new MessageOutput("Error del servidor"));
			}
		};
	}

	private <T> void enviarRespuesta(SocketIOClient client, T data, String event) {
		String answerMessage = gson.toJson(data);
		System.out.println("Objeto enviado: " + answerMessage);
		MessageOutput messageOutput = new MessageOutput(answerMessage);
		client.sendEvent(event, messageOutput);
	}
	private DataListener<MessageInput> insertarUsuario() {
		return (client, data, ackSender) -> {
			System.out.println("El cliente " + client.getRemoteAddress() + " quiere registrar el usuario");
			System.out.println("Mensaje recibido: " + data.getMessage());

			JsonObject jsonObject = new Gson().fromJson(data.getMessage(), JsonObject.class);
			int id = jsonObject.get("id").getAsInt();
			String nombre = jsonObject.get("nombre").getAsString();
			String apellido = jsonObject.get("apellidos").getAsString();
			String login = jsonObject.get("login").getAsString();
			String clave = jsonObject.get("clave1").getAsString();
			String direccion = jsonObject.get("direccion").getAsString();
			String dni = jsonObject.get("dni").getAsString();
			String foto = jsonObject.get("foto").getAsString();
			int telefono1 = jsonObject.get("telefono1").getAsInt();
			int telefono2 = jsonObject.get("telefono2").getAsInt();

			boolean respuesta = false;
			try {
				respuesta = consulta.actualizarUsuario(id, apellido, clave, direccion, dni, foto, login, nombre,
						telefono1, telefono2);
				System.out.println("Usuario actualizado" + respuesta);
				client.sendEvent(Events.ON_INSERTAR_USUARIO.value, new MessageOutput("Usuario actualizado"));
				return;

			} catch (Exception e) {
				System.out.println("Error : " + respuesta + e);
				client.sendEvent(Events.ERROR_INSERTAR_USUARIO.value, new MessageOutput("Error en la insercion"));
				return;
			}

		};
	}
	private DataListener<MessageInput> actualizarClave() {
		return ((client, data, ackSender) -> {
			String message = data.getMessage();
			JsonObject jsonObject = gson.fromJson(message, JsonObject.class);

			String nombre = jsonObject.get("nombre").getAsString();
			String clave = jsonObject.get("clave").getAsString();

			System.out.println(nombre + ":" + clave);
			if (consulta.actualizarClaveUsuario(nombre, clave)) {
				String repuesta = "Actualizado";
				String res = cifrado.encriptar(repuesta);
				MessageOutput messageOutput = new MessageOutput(res);
				System.out.println(" Enviado : " + res);
				client.sendEvent(Events.ON_ACTUALIZAR_CLAVE.value, messageOutput);
			} else {
				String repuesta = "Error";
				String res = cifrado.encriptar(repuesta);
				MessageOutput messageOutput = new MessageOutput(res);
				System.out.println(" Enviado: " + res);
				client.sendEvent(Events.ERROR_ACTUALIZAR_CLAVE.value, messageOutput);
			}
		});
	}

}
