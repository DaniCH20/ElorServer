package socketIO;

/**
 * Lo que recibe el servidor. El mensaje está en JSON, la librería lo parsea
 */
public class MessageOutput extends AbstractMessage {
	public MessageOutput(String message) {
		super(message);
	}
}
