package observer;

import java.util.ArrayList;

public class Channel implements Observable {

	private String name;
	private ArrayList<Observer> observers;
	private ArrayList<Video> uploadedVideos;
	
	public Channel(String name) {
		this.name = name;
		observers = new ArrayList<Observer>();
		uploadedVideos = new ArrayList<Video>();
	}
	
	@Override
	public void subscribe(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void unsubscribe(Observer observer) {
		observers.remove(observer);
	}

	@Override
	public void uploadVideo(String title) {
		Video newVideo = new Video(this, title);
		uploadedVideos.add(newVideo);
		
		for (Observer observer : observers) {
			observer.update(newVideo);
		}
		
	}
	
	public void printUploadeds() {
		System.out.println(name + "'s uploads:");
		
		if(uploadedVideos.isEmpty()) {
			System.out.println("Empty/n");
			return;
		}
		
		for (Video video : uploadedVideos) {
			System.out.println(video.getTitle() + " - " + video.getUploadTime());
		}
		System.out.println("/n");
	}
	
	public String getName() {
		return name;
	}

}
