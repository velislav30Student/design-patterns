package observer;

import java.time.LocalDateTime;

public class Video {

	private String title;
	private Channel uploader;
	private LocalDateTime uploadTime;
	
	public Video(Channel uploader, String title) {
		this.title = title;
		this.uploader = uploader;
		uploadTime = LocalDateTime.now();
	}

	public String getTitle() {
		return title;
	}
	
	public String getUploadTime() {
		return uploadTime.toString();
	}

	public String getUploaderName() {
		return uploader.getName();
	}
	
	

}
