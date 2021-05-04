package mediatorAndSingleton;

public class HumanUser implements ChatUser {

	private MessageMediator mediator;
	private String username;
	
	public HumanUser(MessageMediator mediator, String username) {
		this.mediator = mediator;
		this.username = username;
		mediator.addUser(this);
	}
	
	@Override
	public void send(String message) {
		System.out.println(username + " sends: " + message);
		mediator.sendMessage(this, message);
	}

	@Override
	public void receive(String message) {
		System.out.println(username + " received: " + message);
	}

	@Override
	public String getUsername() {
		return username;
	}

}
