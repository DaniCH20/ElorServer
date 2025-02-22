package socketIO;

/**
 * Lo que envía el servidor. El mensaje está en JSON, la librería lo parsea
 */
public class MessageInput extends AbstractMessage {
	public MessageInput(String message) {
		super(message);
	}
}
