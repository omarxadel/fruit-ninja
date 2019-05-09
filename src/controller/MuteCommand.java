package controller;

public class MuteCommand implements Command{

	private Sound sound;

	public MuteCommand(Sound sound) {
		this.sound = sound;
	}
	@Override
	public void execute() {
		sound.mute();
	}
}
