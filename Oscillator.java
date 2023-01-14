package edu.mccc.cos210.synth;

public class Oscillator {
	private static final double P = 2 * Math.PI;
	private static final double S = Math.pow(2.0, 1.0 / 12.0);
	
	private ADSREnvelope adsr;

	private WaveProfile base;

	private int rate;
	
	//Creates new Oscillator with root frequency of root
	//and sample rate of rate hz
	public Oscillator(double root, double amp, int rate) {
		this.base = new WaveProfile(root, amp, 0, 0, 0, 0, 0, 0);
		this.adsr = new ADSREnvelope(1.0, 1.0, 0.0, 0.0, 0.0, true);
		this.rate = rate;
	}
	
	public Waveform generateWave() {
		return new Waveform(base, adsr);
	}
	
	public void setWaveProfile(double root, double amp, double h1f, double h1a, double h2f, double h2a, double h3f, double h3a) {
		this.base = new WaveProfile(root, amp, h1f, h1a, h2f, h2a, h3f, h3a);
	}
	
	public void setWaveProfile(WaveProfile wp) {
		this.base = wp;
	}
	
	public WaveProfile getWaveProfile() {
		return new WaveProfile(this.base.f1, this.base.a1, this.base.f2, this.base.a2, this.base.f3, this.base.a3, this.base.f4, this.base.a4);
	}
	
	public void setADSR(double peak, double sustain, double attack, double decay, double release, boolean linear) {
		this.adsr = new ADSREnvelope(peak, sustain, attack, decay, release, linear);
	}
	
	public void setADSR(ADSREnvelope adsr) {
		this.adsr = adsr;
	}
	
	public ADSREnvelope getADSR() {
		return new ADSREnvelope(adsr.peak, adsr.sustain, adsr.attack, adsr.decay, adsr.release, adsr.linear);
	}
	
	//Returns sample rate in hertz
	public int getRate() {
		return this.rate;
	}
	
	//Gets a double valued sample of the waveform associated with key.
	//Keys are 0 - 11.
	public double getSample(Waveform w, int key) {
		double mod;
		w.t += 1.0 / rate;
		
		if (w.adsr.linear) {
			if (w.released) {
				mod = (1.0 - ((w.t - w.rtime) / w.adsr.release)) * w.lastMod;
			} else if (w.t <= w.adsr.attack) {
				mod = (w.t / w.adsr.attack) * w.adsr.peak; 
				w.lastMod = mod;
			} else if (w.t <= w.adsr.attack + w.adsr.decay) {
				mod = w.adsr.peak + ((w.adsr.sustain - w.adsr.peak) * ((w.t - w.adsr.attack) / w.adsr.decay));
				w.lastMod = mod;
			} else {
				mod = w.adsr.sustain;
				w.lastMod = mod;
			}			
		} else {
			if (w.released) {
				mod = (1.0 - Math.sqrt((w.t - w.rtime) / w.adsr.release)) * w.lastMod;
			} else if (w.t <= w.adsr.attack) {
				mod = Math.pow(w.t / w.adsr.attack, 2) * w.adsr.peak; 
				w.lastMod = mod;
			} else if (w.t <= w.adsr.attack + w.adsr.decay) {
				mod = w.adsr.peak + ((w.adsr.sustain - w.adsr.peak) * Math.sqrt((w.t - w.adsr.attack) / w.adsr.decay));
				w.lastMod = mod;
			} else {
				mod = w.adsr.sustain;
				w.lastMod = mod;
			}			
		}
		
		
		
		double realVal = getHarmWaveValue(mod * w.wave.a1, w.t, w.wave.f1 * Math.pow(S, key)) 
					   + getHarmWaveValue(mod * w.wave.a2, w.t, w.wave.f2 * Math.pow(S, key)) 
					   + getHarmWaveValue(mod * w.wave.a3, w.t, w.wave.f3 * Math.pow(S, key)) 
					   + getHarmWaveValue(mod * w.wave.a4, w.t, w.wave.f4 * Math.pow(S, key))
				;
		
//		System.out.println(realVal);
		return realVal;
	}
	
	public double getHarmWaveValue(double amp, double t, double wave) { 
		return amp * Math.sin(P * t * wave);
	}
	
	public boolean isDead(Waveform w) {
		boolean b = false;
		if (w.t + (1.0 / rate) - w.rtime >= adsr.release) {
//			System.out.println("dead");
			b = true;
		}
		return b;
	}
	
	public static class ADSREnvelope {
		private double peak = 1.0;
		private double sustain = 1.0;
		
		private double attack = 0.0;
		private double decay = 0.0;
		private double release = 0.0;
		private boolean linear = true;
		
		public ADSREnvelope(double peak, double sustain, double attack, double decay, double release, boolean linear) {
			this.peak = peak;
			this.sustain = sustain;
			this.attack = attack;
			this.decay = decay;
			this.release = release;
			this.linear = linear;
		}
		public void setPeak(double p) {
			peak = p;
		}
		public void setSustain(double s) {
			sustain = s;
		}
		public void setAttack(double a) {
			attack = a;
		}
		public void setDecay(double d) {
			decay = d;
		}
		public void setRelease(double r) {
			release  = r;
		}
		public void setLinear(boolean l) {
			linear = l;
		}
		
	}
	
	public static class WaveProfile {
		private double f1;
		private double a1;
		private double f2;
		private double a2;
		private double f3;
		private double a3;
		private double f4;
		private double a4;
		
		public WaveProfile(double f1, double a1, double f2, double a2, double f3, double a3, double f4, double a4) {
			this.f1 = f1;
			this.a1 = a1;
			this.f2 = f2;
			this.a2 = a2;
			this.f3 = f3;
			this.a3 = a3;
			this.f4 = f4;
			this.a4 = a4;
		}
		
		public void setRootf(double freq) {
			f1 = freq;
		}
		public void setRoota(double amp) {
			a1 = amp;
		}
		
		//Sets value of Harmonic 1 to frequency freq and amplitude amp.
		public void setHarm1f(double freq) {
			f2 = freq;
		}
		public void setHarm1a(double amp) {
			a2 = amp;
		}
		
		//Sets value of Harmonic 2 to frequency freq and amplitude amp.
		public void setHarm2f(double freq) {
			f3 = freq;
		}
		public void setHarm2a(double amp) {
			a3 = amp;
		}

		
		//Sets value of Harmonic 3 to frequency freq and amplitude amp.
		public void setHarm3f(double freq) {
			f4 = freq;
		}
		public void setHarm3a(double amp) {
			a4 = amp;
		}
	}
	
	public static class Waveform {
		private WaveProfile wave;
		private ADSREnvelope adsr;
		
		private double t = 0.0;
		private boolean released = false;
		private double rtime = 0.0;
		private double lastMod = 0.0;
		
		public Waveform(WaveProfile w, ADSREnvelope adsr) {
			this.wave = w;
			this.adsr = adsr;
		}
		
		public boolean isReleased() {
			return released;
		}
		
		public void release() {
			this.released = true;
			this.rtime = this.t;
		}
	}
}
