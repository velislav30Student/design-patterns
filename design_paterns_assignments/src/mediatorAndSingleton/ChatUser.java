package mediatorAndSingleton;

public interface ChatUser {
	public void send(String message);
	public void receive(String message);
	public String getUsername();
}
