package observer;

public class ObserverMain {
	
	//Theme: Youtube
	public static void main(String[] args) {
		//Initializing
		Channel ch1 = new Channel("Vloger");
		Channel ch2 = new Channel("Gamer");
		Channel ch3 = new Channel("News");
		
		User u1 = new User("user1");
		User u2 = new User("user2");
		User u3 = new User("user3");
		
		//Subscribing/Attaching
		ch1.subscribe(u1);
		
		ch2.subscribe(u1);
		ch2.subscribe(u3);
		
		ch3.subscribe(u1);
		ch3.subscribe(u2);
		ch3.subscribe(u3);

		//Updating
		ch1.uploadVideo("Vlog1");
		ch2.uploadVideo("Game1");
		ch1.uploadVideo("Vlog2");
		ch3.uploadVideo("News1");
		ch2.uploadVideo("Game2");
		
		//Printing
		ch1.printUploadeds();
		ch2.printUploadeds();
		ch3.printUploadeds();
		
		u1.printSubFeed();
		u2.printSubFeed();
		u3.printSubFeed();
	}

}
