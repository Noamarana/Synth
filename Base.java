package edu.mccc.cos210.synth;

import javax.sound.sampled.AudioFormat;

public class Base {
	
	public static void main(String[] args) throws InterruptedException {
		Player player = new Player();
		Thread mixThread = new Thread(player);
		mixThread.start();
		System.out.println("Start");
		Thread.sleep(500);
		WaveMixer mixer = player.getMixer();
		
		GUIPane GUI = new GUIPane(mixer);
		Thread GUIThread = new Thread(GUI);
		GUIThread.start();
		
		while (!GUI.end) {
			Thread.sleep(1000);
		}
		
		mixThread.stop();
		GUIThread.stop();
		System.out.println("Stop");
		System.exit(0);
	}
	
	private static class Player implements Runnable {
		private WaveMixer mixer;
		
		public Player() {
			AudioFormat fmt = new AudioFormat(44100, 16, 1, true, false);
			Oscillator synth = new Oscillator(440, 0.25, 44100);
			try {
				mixer = new WaveMixer(fmt, 12, synth);				
			} catch (Exception ex) {
				ex.printStackTrace();
				System.exit(-1);
			}
		}
		
		@Override
		public void run() {
			try {
				while (true) {
					if (mixer.sdl.available() >= mixer.outArr.length) {
						mixer.playChunk();
						Thread.sleep(25);
					}
				}				
			} catch (Exception ex) {
				ex.printStackTrace();
				System.exit(-1);
			}
		}
		
		public WaveMixer getMixer() {
			return mixer;
		}
	}
}
