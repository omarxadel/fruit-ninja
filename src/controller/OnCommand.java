package controller;

public class OnCommand implements Command {

	private Sound sound;

	public OnCommand(Sound sound) {
		this.sound = sound;
	}
	@Override
	public void execute() {
		sound.on();
	}

}
