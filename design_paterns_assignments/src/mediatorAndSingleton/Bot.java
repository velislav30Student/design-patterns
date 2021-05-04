package mediatorAndSingleton;

import java.util.ArrayList;

public class Bot implements ChatUser {

	private static Bot instance;
	private MessageMediator mediator;
	private String name;
	private boolean shouldBanUser;
	private ChatUser messageSender;
	private String censoredMessage;
	
	private ArrayList<String> bannedWords;
	private ArrayList<String> censoredWords;
	
	private Bot() {
		bannedWords= new ArrayList<String>();
		censoredWords = new ArrayList<String>();
	}
	
	public static Bot getInstance() {
		if(instance == null) {
			instance = new Bot();
		}
		return instance;
	}
	

	@Override
	public void send(String message) {
		mediator.sendMessage(this, message);
	}

	public void receive(ChatUser user, String message) {
		messageSender = user;
		receive(message);
	}
	
	@Override
	public void receive(String message) {
		CheckMessageForBannedWords(message);
		if(shouldBanUser == false) {
			CensorMessage(message);
		}
	}
	
	private void CheckMessageForBannedWords(String message) {
		shouldBanUser = false;
		String usedWord = "";
		String lowercaseMessage = message.toLowerCase();
		lowercaseMessage = lowercaseMessage.replace(".", " ").replace("?", " ").replace("!", " ").replace(",", " ")
				.replace("'", " ").replace('"', ' ');
		
		for (String word : bannedWords) {
			if(lowercaseMessage.contains(" " + word + " ") ||
					lowercaseMessage.startsWith(word + " ") ||
					lowercaseMessage.endsWith(" " + word)) {
				shouldBanUser = true;
				usedWord = word;
				break;
			}
		}
		if(shouldBanUser) {
			BanUser(usedWord);
		}
	}
	
	private void BanUser(String usedWord) {
		mediator.removeUser(messageSender);
		send(messageSender.getUsername() + " was banned for using the illegall word '"+ usedWord +"'");
	}
	
	//Replaces every letter of all censored words with *
	private void CensorMessage(String message) {
		censoredMessage = message;
		String lowercaseMessage = "";
		
		ArrayList<String> symbols = new ArrayList<String>();
		symbols.add("");
		symbols.add(".");
		symbols.add(",");
		symbols.add("?");
		symbols.add("!");
		symbols.add("(");
		symbols.add(")");
		symbols.add("'");
		symbols.add(".'");
		symbols.add(",'");
		symbols.add("?'");
		symbols.add("!'");
		symbols.add("('");
		symbols.add(")'");
		symbols.add("'.");
		symbols.add("',");
		symbols.add("'?");
		symbols.add("'!");
		symbols.add("'(");
		symbols.add("')");
		symbols.add(String.valueOf('"'));
		symbols.add(String.valueOf('"') + ",");
		symbols.add(String.valueOf('"') + ".");
		symbols.add(String.valueOf('"') + "!");
		symbols.add(String.valueOf('"') + "?");
		symbols.add(String.valueOf('"') + "(");
		symbols.add(String.valueOf('"') + ")");
		symbols.add("." + String.valueOf('"'));
		symbols.add("," + String.valueOf('"'));
		symbols.add("?" + String.valueOf('"'));
		symbols.add("!" + String.valueOf('"'));
		symbols.add("(" + String.valueOf('"'));
		symbols.add(")" + String.valueOf('"'));
		
		for (String word : censoredWords) {
			if(censoredMessage.contentEquals(word)) {
				String censoredString = "*".repeat(word.length());
				censoredMessage = censoredString;
				return;
			}
			for (String symbol1 : symbols) {
				for (String symbol2 : symbols) {
					lowercaseMessage = censoredMessage.toLowerCase();
					CensorWord(word, symbol1 + word + symbol2, lowercaseMessage);
				}
			}
		}
	}
	
	//Replaces every letter of a censored word with *
	private void CensorWord(String originalWord,String currentWord, String message) {
		String censorChar = "*";
		
		//If word is in the middle of the sentence
		if(message.contains(" " + currentWord + " ")) {
			String censorString = censorChar.repeat(originalWord.length());
			censorString = currentWord.replace(originalWord, censorString);
			censoredMessage = censoredMessage.replace(" " + currentWord + " ", " " + censorString + " ");
		}
		
		//If word is at the begining of the sentence
		if(message.startsWith(currentWord + " ")) {
			String censorString = censorChar.repeat(originalWord.length());
			String firstChar = String.valueOf(censoredMessage.charAt(0));
			if(firstChar.contentEquals(".") || firstChar.contentEquals(",") || firstChar.contentEquals("?") || firstChar.contentEquals("!") ||
					firstChar.contentEquals("'") || firstChar.contentEquals(String.valueOf('"')) || 
					firstChar.contentEquals(String.valueOf('(')) || firstChar.contentEquals(String.valueOf(')'))) {
				censoredMessage = firstChar + censorString + censoredMessage.substring(originalWord.length() + 1);
			}
			else {
				censoredMessage = censorString + censoredMessage.substring(originalWord.length());
			}
		}
		
		//If word is at the end of the sentence
		if(message.endsWith(" " + currentWord)) {
			String censorString = censorChar.repeat(originalWord.length());
			String lastChar = String.valueOf(censoredMessage.charAt(censoredMessage.length()-1));
			if(lastChar.contentEquals(".") || lastChar.contentEquals(",") || lastChar.contentEquals("?") || lastChar.contentEquals("!") ||
					lastChar.contentEquals("'") || lastChar.contentEquals(String.valueOf('"')) || 
					lastChar.contentEquals(String.valueOf('(')) || lastChar.contentEquals(String.valueOf(')'))) {
				censoredMessage = censoredMessage.substring(0, censoredMessage.length() - originalWord.length()-1) + censorString + lastChar;
			}
			else {
				censoredMessage = censoredMessage.substring(0, censoredMessage.length() - originalWord.length()) + censorString;
			}
		}
	}
	
	public String getCensoredMessage() {
		return censoredMessage;
	}

	@Override
	public String getUsername() {
		return name;
	}
	
	public boolean IsUserBanned() {
		return shouldBanUser;
	}
	
	public void Init(MessageMediator mediator, String name) {
		this.mediator = mediator;
		this.name = name;
		bannedWords.add("cat");
		censoredWords.add("dog");
		bannedWords.add("cats");
		censoredWords.add("dogs");
	}
	
	public void BanWord(String word) {
		if(!bannedWords.contains(word)) {
			bannedWords.add(word);
		}
		censoredWords.remove(word);
	}
	
	public void UnbanWord(String word) {
		bannedWords.remove(word);
	}
	
	public void CensoreWord(String word) {
		if(!censoredWords.contains(word)) {
			censoredWords.add(word);
		}
		bannedWords.remove(word);
	}
	
	public void UncensoreWord(String word) {
		censoredWords.remove(word);
	}
}
