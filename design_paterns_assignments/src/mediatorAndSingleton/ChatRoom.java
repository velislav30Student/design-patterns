package mediatorAndSingleton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChatRoom implements MessageMediator {

	private ArrayList<ChatUser> users;
	private Bot monitorBot;
	
	public ChatRoom() {
		users = new ArrayList<ChatUser>();
	}

	@Override
	public void sendMessage(ChatUser user, String message) {
		if(message.startsWith("/")) {
			ExecuteCommand(user, message);
		}
		else {
			if(monitorBot != null && monitorBot != user) {
				monitorBot.receive(user, message);
				if(monitorBot.IsUserBanned()) {
					return;
				}
				message = monitorBot.getCensoredMessage();
			}
			for (ChatUser chatUser : users) {
				if(chatUser != user) {
					chatUser.receive(message);
				}
			}
			
		}
	}

	@Override
	public void addUser(ChatUser user) {
		users.add(user);
		System.out.println(user.getUsername() + " has been added");
	}

	@Override
	public void removeUser(ChatUser user) {
		users.remove(user);
	}

	private void ExecuteCommand(ChatUser user, String command) {
		List<String> commandParameters = Arrays.asList(command.split(" "));
		switch (commandParameters.get(0)) {
		case "/help":
			System.out.println("Here's a list of available commands: /n"
					+ "- addBot\n"
					+ "- help\n"
					+ "- banWord word\n"
					+ "- unbanWord word\n"
					+ "- censoreWord word\n"
					+ "- uncensoreWord word");
			break;
		case "/addBot":
			ExecuteAddBotCommand();
			break;
		case "/banWord":
			if(commandParameters.size()<2) {
				user.receive("Example: /banWord word");
			}
			else {
				ExecuteBanWordCommand(commandParameters.get(1));
			}
			break;
		case "/unbanWord":
			if(commandParameters.size()<2) {
				user.receive("Example: /unbanWord word");
			}
			else {
				ExecuteUnbanWordCommand(commandParameters.get(1));
			}
			break;
		case "/censoreWord":
			if(commandParameters.size()<2) {
				user.receive("Example: /censoreWord word");
			}
			else {
				ExecuteCensoreWordCommand(commandParameters.get(1));
			}
			break;
		case "/uncensoreWord":
			if(commandParameters.size()<2) {
				user.receive("Example: /uncensoreWord word");
			}
			else {
				ExecuteUncensoreWordCommand(commandParameters.get(1));
			}
			break;
		default:
			user.receive("This command doesn't exist.");
		}
	}
	
	private void ExecuteAddBotCommand() {
		monitorBot = Bot.getInstance();
		monitorBot.Init(this, "Bot |0_o|");
		monitorBot.send("A bot has been added to monitor your behavior.");
	}
	
	private void ExecuteBanWordCommand(String word) {
		monitorBot.BanWord(word);
		monitorBot.send("The word '" + word + "' is now forbiden");
	}
	
	private void ExecuteUnbanWordCommand(String word) {
		monitorBot.UnbanWord(word);
		monitorBot.send("The word '" + word + "' is no longer forbiden");
	}
	
	private void ExecuteCensoreWordCommand(String word) {
		monitorBot.CensoreWord(word);
		monitorBot.send("The word '" + word + "' is now censored");
	}
	
	private void ExecuteUncensoreWordCommand(String word) {
		monitorBot.UncensoreWord(word);
		monitorBot.send("The word '" + word + "' is no longer censored");
	}
}
