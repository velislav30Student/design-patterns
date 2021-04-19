package observer;

import java.util.ArrayList;

public class User implements Observer {
	
	private String username;
	private ArrayList<Video> subscriptionFeed = new ArrayList<Video>();
	
	public User(String username) {
		this.username = username;
		subscriptionFeed = new ArrayList<Video>();
	}

	@Override
	public void update(Video video) {
		subscriptionFeed.add(video);
	}

	public void printSubFeed() {
		System.out.println(username + "'s subscription feed:");
		
		if(subscriptionFeed.isEmpty()) {
			System.out.println("Empty/n");
			return;
		}
		
		for (int i = subscriptionFeed.size()-1; i >= 0; i--) {
			Video video = subscriptionFeed.get(i);
			System.out.println(video.getTitle() + " uploaded by " + video.getUploaderName() 
				+ " on " + video.getUploadTime());
		}
		System.out.println("/n");
	}
	
}
