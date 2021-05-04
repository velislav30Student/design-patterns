package mediatorAndSingleton;

public class MediatorAndSingletonMain {

	public static void main(String[] args) {
		MessageMediator chat = new ChatRoom();
		
		ChatUser user1 = new HumanUser(chat,"DogLover");
		ChatUser user2 = new HumanUser(chat,"CatLover");
		ChatUser user3 = new HumanUser(chat,"BirdLover");
		
		user1.send("I love dogs");
		user2.send("I love cats");
		user3.send("I love birds");
		
		user3.send("/addbot");
		user3.send("/help");
		user3.send("/addBot");
		
		user1.send("Dogs! I love dogs, but dogs");
		user2.send("Cats are the best!!!");
		user3.send("I love birds");
		
		user1.send("Dude Wtf!!!");
		
		user1.send("/uncensoreWord dog");
		user1.send("/uncensoreWord dogs");
		
		user1.send("dog dog dog");
		user1.send("Good, it works!");
		
		user1.send("/unbanWord cat");
		user1.send("/unbanWord cats");
		
		user1.send("cat, cat ,cat");
		user1.send("Good, this works as well!");
		
		user3.send("/censoreWord wtf");
		user1.send("wtf dude");
		
		user3.send("/banWord");
		user3.send("/banWord censore");
		user1.send("You can't censore any word you want!!!");
		
		user3.send("^U^");
	}

}
