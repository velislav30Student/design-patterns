package mediatorAndSingleton;

public interface MessageMediator {
	void sendMessage(ChatUser user, String message);
	void addUser(ChatUser user);
	void removeUser(ChatUser user);
}
