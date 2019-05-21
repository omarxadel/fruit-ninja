package controller;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

public class SoundSystem implements Sound {
	String path = new File ("res/menu.mp3").getAbsolutePath();
	private Media mediafile= new Media (new File(path).toURI().toString());
	private MediaPlayer mediaplayer= new MediaPlayer(mediafile);
	javafx.scene.media.MediaPlayer.Status status=mediaplayer.getStatus();
	
	@Override
	public void on() {
		mediaplayer.play();
		if (status==Status.STOPPED)
			mediaplayer.play();
		mediaplayer.setVolume(0.2);
		
	}

	@Override
	public void mute() {
		
		if (mediaplayer!=null) {
			 System.out.println("hh");
				if (status==Status.PLAYING) {
				System.out.println("mm");
					mediaplayer.stop();
				}

}
	}
}
