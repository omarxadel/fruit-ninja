package controller;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundSystem implements Sound {
	private URL af;
	private Media mf;
	private MediaPlayer mp;
	//ClassLoader classLoader = ClassLoader.getSystemClassLoader();
	
	@Override
	public void on() {
	/*	af = ClassLoader.getSystemResource("menu.ogg");
		try {
			mf = new Media(af.toURI().toString());
			MediaPlayer mp=new MediaPlayer(mf);
			mp.setAutoPlay(true);
			mp.setVolume(0.3);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}	*/
		
	}

	@Override
	public void mute() {
		
		mp.stop();
	}

}
