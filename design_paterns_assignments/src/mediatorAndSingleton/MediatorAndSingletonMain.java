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
		
		user3.send("/banWord cat");
		
		user3.send("/addbot");
		user3.send("/help");
		user3.send("/addBot");
		//Bot automatically bans "cat" and "cats" and censores "dog" and "dogs"
		
		user1.send("Dogs! I love dogs, but dogs.");
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
		
		/*
Console Output:
DogLover has been added
CatLover has been added
BirdLover has been added
DogLover sends: I love dogs
CatLover received: I love dogs
BirdLover received: I love dogs
CatLover sends: I love cats
DogLover received: I love cats
BirdLover received: I love cats
BirdLover sends: I love birds
DogLover received: I love birds
CatLover received: I love birds
BirdLover sends: /banWord cat
BirdLover received: You need to add a monitoring bot first.
BirdLover sends: /addbot
BirdLover received: This command doesn't exist.
BirdLover sends: /help
Here's a list of available commands:
- addBot
- help
- banWord word
- unbanWord word
- censoreWord word
- uncensoreWord word
BirdLover sends: /addBot
DogLover received: A bot has been added to monitor your behavior.
CatLover received: A bot has been added to monitor your behavior.
BirdLover received: A bot has been added to monitor your behavior.
DogLover sends: Dogs! I love dogs, but dogs.
CatLover received: ****! I love ****, but ****.
BirdLover received: ****! I love ****, but ****.
CatLover sends: Cats are the best!!!
DogLover received: CatLover was banned for using the illegall word 'cats'
BirdLover received: CatLover was banned for using the illegall word 'cats'
BirdLover sends: I love birds
DogLover received: I love birds
DogLover sends: Dude Wtf!!!
BirdLover received: Dude Wtf!!!
DogLover sends: /uncensoreWord dog
DogLover received: The word 'dog' is no longer censored
BirdLover received: The word 'dog' is no longer censored
DogLover sends: /uncensoreWord dogs
DogLover received: The word 'dogs' is no longer censored
BirdLover received: The word 'dogs' is no longer censored
DogLover sends: dog dog dog
BirdLover received: dog dog dog
DogLover sends: Good, it works!
BirdLover received: Good, it works!
DogLover sends: /unbanWord cat
DogLover received: The word 'cat' is no longer forbiden
BirdLover received: The word 'cat' is no longer forbiden
DogLover sends: /unbanWord cats
DogLover received: The word 'cats' is no longer forbiden
BirdLover received: The word 'cats' is no longer forbiden
DogLover sends: cat, cat ,cat
BirdLover received: cat, cat ,cat
DogLover sends: Good, this works as well!
BirdLover received: Good, this works as well!
BirdLover sends: /censoreWord wtf
DogLover received: The word 'wtf' is now censored
BirdLover received: The word 'wtf' is now censored
DogLover sends: wtf dude
BirdLover received: *** dude
BirdLover sends: /banWord
BirdLover received: Example: /banWord word
BirdLover sends: /banWord censore
DogLover received: The word 'censore' is now forbiden
BirdLover received: The word 'censore' is now forbiden
DogLover sends: You can't censore any word you want!!!
BirdLover received: DogLover was banned for using the illegall word 'censore'
BirdLover sends: ^U^

		 */
	}
	
}
