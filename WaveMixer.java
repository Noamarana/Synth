package edu.mccc.cos210.synth;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import edu.mccc.cos210.ds.IArray;
import edu.mccc.cos210.ds.IQueue;
import edu.mccc.cos210.ds.ISinglyLinkedList;
import edu.mccc.cos210.ds.impl.Array;
import edu.mccc.cos210.ds.impl.Queue;
import edu.mccc.cos210.ds.impl.SinglyLinkedList;
import edu.mccc.cos210.synth.Oscillator.Waveform;

public class WaveMixer {
	Oscillator synth;
	
	IQueue<Integer> pressQueue;
	IQueue<Integer> releaseQueue;
	IArray<Waveform> keyTones;
	ISinglyLinkedList<Pair> fadeBuffer;
	
	byte[] outArr;
	int keys;
	
	SourceDataLine sdl;
	AudioFormat fmt;
	
	public WaveMixer(AudioFormat fmt, int keys, Oscillator synth) throws LineUnavailableException {
		pressQueue = new Queue<Integer>();
		releaseQueue = new Queue<Integer>();
		keyTones = new Array<Waveform>(keys);
		fadeBuffer = new SinglyLinkedList<Pair>();
		
		//50 ms per write
		outArr = new byte[(int)((fmt.getSampleRate() * 2) / 20) - (int)((fmt.getSampleRate() * 2) / 20) % 2];
		
		this.fmt = fmt;
		this.synth = synth;
		this.keys = keys;
		
		sdl = AudioSystem.getSourceDataLine(fmt);
		sdl.open(fmt, outArr.length * 2);
		sdl.start();
	}
	
	public void playChunk() {
		synchronized (this.pressQueue) {
			while (!pressQueue.isEmpty()) {
				keyTones.set(pressQueue.dequeue(), synth.generateWave());
			}			
		}
		
		synchronized (this.synth) {
			doMix();			
		}
		
		sdl.write(outArr, 0, outArr.length);
		
		synchronized (this.releaseQueue) {
			while (!releaseQueue.isEmpty()) {
				int ind = releaseQueue.dequeue();
				Waveform w = keyTones.get(ind);
				if (w != null) {
					w.release();
					keyTones.set(ind, null);
					fadeBuffer.addLast(new Pair(w, ind));					
				}
			}			
		}
	}
	
	private void doMix() {
		for (int j = 0; j+1 < outArr.length; j+=2) {
				short mix = 0;
				double total = 0.0;
				Waveform cur;
				
				for (int i = 0; i < keyTones.getSize(); i++) {
					cur = keyTones.get(i);
					if (cur != null) {
						total += synth.getSample(cur, i) / 6;				
					}
				}
				
				for (int i = fadeBuffer.getSize(); i > 0; i--) {
					Pair p = fadeBuffer.removeFirst();
					total += synth.getSample(p.w, p.key) / 6;	
					if (!synth.isDead(p.w)) {
						fadeBuffer.addLast(p);
					} else {
						System.out.println("Dead");
					}
				}					
				
				double clip = total > 1.0 ? 1.0 : total < -1.0 ? -1.0 : total;
				mix = (short)((1.4 * clip - (0.5 * Math.pow(clip, 3))) * 32767);
//					if(mix != 0) {
//						System.out.println(mix);
//					}
				
//				mix = (short)(((2 / Math.PI) * Math.atan(1.1 * total)) * 32767);
				outArr[j] = (byte)mix;
				outArr[j+1] = (byte)(mix >> 8);
		}
	}
	
	public void pressKey(int key) {
		synchronized (this.pressQueue) {
			if (key < this.keys) {
				pressQueue.enqueue(key);
			}
		}
			
	}
	
	public void releaseKey(int key) {
		synchronized (this.releaseQueue) {
			if (key < this.keys) {
				releaseQueue.enqueue(key);
			}			
		}
	}
	
	public Oscillator.ADSREnvelope getADSR() {
		synchronized (this.synth) {
			return this.synth.getADSR();			
		}
	}
	
	public void setADSR(Oscillator.ADSREnvelope adsr) {
		synchronized (this.synth) {
			this.synth.setADSR(adsr);			
		}
	}
	
	public Oscillator.WaveProfile getProfile() {
		synchronized (this.synth) {
			return this.synth.getWaveProfile();			
		}
	}
	
	public void setProfile(Oscillator.WaveProfile wp) {
		synchronized (this.synth) {
			this.synth.setWaveProfile(wp);
		}
	}
	
	private static class Pair {
		Waveform w;
		int key;
		
		public Pair(Waveform w, int key) {
			this.w = w;
			this.key = key;
		}
	}
}
