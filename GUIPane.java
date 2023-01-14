package edu.mccc.cos210.synth;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GUIPane extends JFrame implements Runnable {
    public static double roota = 0.25;
    public static double harm1a = 0.0;
    public static double harm2a = 0.0;
    public static double harm3a = 0.0;
    public static double attack = 0.0;
    public static double decay = 0.0;
    public static double sustain = 1.0;
    public static double release = 0.0;
    public static double rootf = 440.0;
    public static double harm1f = 0.0;
    public static double harm2f = 0.0;
    public static double harm3f = 0.0;
    
    public static JTextField tf1;
    public static JTextField tf2;
    public static JTextField tf3;
    public static JTextField tf4;
    public static JTextField tf5;
    public static JTextField tf6;
    public static JTextField tf7;
    public static JTextField tf8;
    public static JTextField tf9;
    public static JTextField tf10;
    public static JTextField tf11;
    public static JTextField tf12;
    
	public static JButton B1;
	public static JButton B2;
	public static JButton B3;
	public static JButton B4;
	public static JButton B5;
	public static JButton B6;
	public static JButton B7;
	public static JButton B8;
	public static JButton B9;
	public static JButton B10;
	public static JButton B12;
	public static JButton B11;
	
	private boolean mtf1;
	private boolean mtf2;
	private boolean mtf3;
	private boolean mtf4;
	private boolean mtf5;
	private boolean mtf6;
	private boolean mtf7;
	private boolean mtf8;
	private boolean mtf9;
	private boolean mtf10;
	private boolean mtf11;
	private boolean mtf12;
	
	public WaveMixer mixer;
	public boolean end = false;
	
	public GUIPane(WaveMixer mixer) {
		this.mixer = mixer;
		this.setResizable(false);
		this.setTitle("Synth");
		this.setName("Synth");
        this.setSize(900, 500);       
	    this.setLayout(null);  
	}
	
	public void run() {
	    JLabel l1;
	    l1 = new JLabel("Attack Duration");  
	    l1.setBounds(700, 25, 150, 30);  
	    add(l1); 
	    
	    JLabel l2;
	    l2 = new JLabel("Decay Duration");  
	    l2.setBounds(700, 65, 150, 30);  
	    add(l2);
	    
	    JLabel l3;
	    l3 = new JLabel("Sustain Amplitude");  
	    l3.setBounds(700, 105, 150, 30);  
	    add(l3);
	    
	    JLabel l4;
	    l4 = new JLabel("Release Duration");  
	    l4.setBounds(700, 145, 150, 30);  
	    add(l4);
	    
	    JLabel l5;
	    l5 = new JLabel("Root Frequency");  
	    l5.setBounds(170, 307, 150, 30);  
	    add(l5);  
	    
	    JLabel l6;
	    l6 = new JLabel("Harmonic 1 Freq.");  
	    l6.setBounds(170, 338, 150, 30);  
	    add(l6); 
	    
	    JLabel l7;
	    l7 = new JLabel("Harmonic 2 Freq.");  
	    l7.setBounds(170, 369, 150, 30);  
	    add(l7); 
	    
	    JLabel l8;
	    l8 = new JLabel("Harmonic 3 Freq.");  
	    l8.setBounds(170, 400, 150, 30);  
	    add(l8); 
	    
	    JLabel l9;
	    l9 = new JLabel("Root Amplitude");  
	    l9.setBounds(510, 307, 150, 30);  
	    add(l9); 
	    
	    JLabel l10;
	    l10 = new JLabel("Harmonic 1 Amp.");  
	    l10.setBounds(510, 338, 150, 30);  
	    add(l10);
	    
	    JLabel l11;
	    l11 = new JLabel("Harmonic 2 Amp.");  
	    l11.setBounds(510, 369, 150, 30);  
	    add(l11);
	    
	    JLabel l12;
	    l12 = new JLabel("Harmonic 3 Amp.");  
	    l12.setBounds(510, 400, 150, 30);  
	    add(l12);  
	    
	    tf1 = new JTextField(); 
	    tf1.setText("0.0");
        tf1.setBounds(700, 50, 150, 20);
        tf1.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				mtf1 = true;
			}

			@Override
			public void mouseExited(MouseEvent e) {
				mtf1 = false;				
			}
        });
        
        tf1.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				if (mtf1) {
					DecimalFormat df = new DecimalFormat("#.#");
				    df.setRoundingMode(RoundingMode.HALF_EVEN);
					double d = attack + 0.1 * -e.getWheelRotation();
					attack = (d > 3.0) || (d < 0.0 ) ? attack : Double.parseDouble(df.format(d));
					Oscillator.ADSREnvelope adsr = mixer.getADSR();
					adsr.setAttack(attack);
					mixer.setADSR(adsr);					
					tf1.setText(Double.toString(attack));
				}
			}	
        });
        tf1.addFocusListener(new FocusListener() {
        	@Override
    		public void focusLost(FocusEvent e) {
    			tf1.setText(Double.toString(attack));
    		}
			@Override
			public void focusGained(FocusEvent e) {
			}	
        });
        add(tf1);
        tf1.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {			
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String s = tf1.getText();
					double d = -1;
					try {
						 d = Double.parseDouble(s); 					
					} catch (NumberFormatException ex) {
						tf1.setText("");
					}
					if (d < 0 || d > 3) { 					
						tf1.setText("");
					} else {
						attack = d;
						Oscillator.ADSREnvelope adsr = mixer.getADSR();
						adsr.setAttack(attack);
						mixer.setADSR(adsr);
					}
					System.out.println(tf1.getText());
				}	
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}      	
        });

	    tf2 = new JTextField();  
	    tf2.setText("0.0");
        tf2.setBounds(700, 90, 150, 20);
        tf2.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				mtf2 = true;
			}

			@Override
			public void mouseExited(MouseEvent e) {
				mtf2 = false;				
			}
        });
        
        tf2.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				if (mtf2) {
					DecimalFormat df = new DecimalFormat("#.#");
				    df.setRoundingMode(RoundingMode.HALF_EVEN);
					double d = decay + 0.1 * -e.getWheelRotation();
					decay = (d > 3.0) || (d < 0.0 ) ? decay : Double.parseDouble(df.format(d));
					Oscillator.ADSREnvelope adsr = mixer.getADSR();
					adsr.setDecay(decay);
					mixer.setADSR(adsr);					
					tf2.setText(Double.toString(decay));
				}
			}	
        });
        tf2.addFocusListener(new FocusListener() {
        	@Override
    		public void focusLost(FocusEvent e) {
    			tf2.setText(Double.toString(decay));
    		}
			@Override
			public void focusGained(FocusEvent e) {
			}	
        });
        add(tf2);
        tf2.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String s = tf2.getText();
					double d = -1;
					try {
						 d = Double.parseDouble(s); 					
					} catch (NumberFormatException ex) {
						tf2.setText("");
					}
					if (d < 0 || d > 3) {						
						tf2.setText("");
					} else {
						decay = d;
						Oscillator.ADSREnvelope adsr = mixer.getADSR();
						adsr.setDecay(decay);
						mixer.setADSR(adsr);
					}
					System.out.println(tf2.getText());
				}	
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
        });
        
	    tf3 = new JTextField();  
	    tf3.setText("1.0");
        tf3.setBounds(700, 130, 150, 20);
        tf3.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				mtf3 = true;
			}

			@Override
			public void mouseExited(MouseEvent e) {
				mtf3 = false;				
			}
        });
        
        tf3.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				if (mtf3) {
					DecimalFormat df = new DecimalFormat("#.#");
				    df.setRoundingMode(RoundingMode.HALF_EVEN);
					double d = sustain + 0.1 * -e.getWheelRotation();
					sustain = (d > 1.0) || (d < 0.0 ) ? sustain : Double.parseDouble(df.format(d));
					Oscillator.ADSREnvelope adsr = mixer.getADSR();
					adsr.setSustain(sustain);
					mixer.setADSR(adsr);					
					tf3.setText(Double.toString(sustain));
				}
			}	
        });
        tf3.addFocusListener(new FocusListener() {
        	@Override
    		public void focusLost(FocusEvent e) {
    			tf3.setText(Double.toString(sustain));
    		}
			@Override
			public void focusGained(FocusEvent e) {
			}	
        });
        add(tf3);
        tf3.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String s = tf3.getText();
					double d = -1;
					try {
						 d = Double.parseDouble(s); 					
					} catch (NumberFormatException ex) {
						tf3.setText("");
					}
					if (d < 0 || d > 1) {						
						tf3.setText("");
					} else {
						sustain = d;
						Oscillator.ADSREnvelope adsr = mixer.getADSR();
						adsr.setSustain(sustain);
						mixer.setADSR(adsr);
					}
				}	
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
        });
        
	    tf4 = new JTextField(); 
	    tf4.setText("0.0");
        tf4.setBounds(700, 170, 150, 20);
        tf4.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				mtf4 = true;
			}

			@Override
			public void mouseExited(MouseEvent e) {
				mtf4 = false;				
			}
        });
        
        tf4.addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				if (mtf4) {
					DecimalFormat df = new DecimalFormat("#.#");
				    df.setRoundingMode(RoundingMode.HALF_EVEN);
					double d = release + 0.1 * -e.getWheelRotation();
					release = (d > 3.0) || (d < 0.0 ) ? release : Double.parseDouble(df.format(d));
					Oscillator.ADSREnvelope adsr = mixer.getADSR();
					adsr.setRelease(release);
					mixer.setADSR(adsr);					
					tf4.setText(Double.toString(release));
				}
			}	
        });
        tf4.addFocusListener(new FocusListener() {
        	@Override
    		public void focusLost(FocusEvent e) {
    			tf4.setText(Double.toString(release));
    		}
			@Override
			public void focusGained(FocusEvent e) {
			}	
        });
        add(tf4);
        tf4.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String s = tf4.getText();
					double d = -1;
					try {
						 d = Double.parseDouble(s); 					
					} catch (NumberFormatException ex) {
						tf4.setText("");
					}
					if (d < 0 || d > 3) {						
						tf4.setText("");
					} else {
						release = d;
						Oscillator.ADSREnvelope adsr = mixer.getADSR();
						adsr.setRelease(release);
						mixer.setADSR(adsr);
					}
				}	
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
        });
        
	    tf5 = new JTextField(); 
	    tf5.setText("440.0");
        tf5.setBounds(10, 312, 150, 20);
        tf5.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				mtf5 = true;
			}

			@Override
			public void mouseExited(MouseEvent e) {
				mtf5 = false;				
			}
        });
        
        tf5.addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				if (mtf5) {
					DecimalFormat df = new DecimalFormat("#.#");
				    df.setRoundingMode(RoundingMode.HALF_EVEN);
					double d = rootf - e.getWheelRotation();
					rootf = (d < 0 ) || ( d > 22050 ) ? rootf : Double.parseDouble(df.format(d));
					Oscillator.WaveProfile wp = mixer.getProfile();
					wp.setRootf(rootf);
					mixer.setProfile(wp);;					
					tf5.setText(Double.toString(rootf));
				}
			}	
        });
        tf5.addFocusListener(new FocusListener() {
        	@Override
    		public void focusLost(FocusEvent e) {
    			tf5.setText(Double.toString(rootf));
    		}
			@Override
			public void focusGained(FocusEvent e) {
			}	
        });
        add(tf5);
        tf5.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String s = tf5.getText();
					double d = -1;
					try {
						 d = Double.parseDouble(s); 					
					} catch (NumberFormatException ex) {
						tf5.setText("");
					}
					if (d < 0 || d > 22050) {						
						tf5.setText("");
					} else {
						rootf = d;
						Oscillator.WaveProfile prof = mixer.getProfile();
						prof.setRootf(rootf);
						mixer.setProfile(prof);
					}
				}	
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
        });
        
	    tf6 = new JTextField(); 
	    tf6.setText("0.0");
        tf6.setBounds(10, 343, 150, 20);
        tf6.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				mtf6 = true;
			}

			@Override
			public void mouseExited(MouseEvent e) {
				mtf6 = false;				
			}
        });
        
        tf6.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				if (mtf6) {
					DecimalFormat df = new DecimalFormat("#.#");
				    df.setRoundingMode(RoundingMode.HALF_EVEN);
					double d = harm1f - e.getWheelRotation();
					harm1f = (d < 0 ) || ( d > 22050 ) ? harm1f : Double.parseDouble(df.format(d));
					Oscillator.WaveProfile wp = mixer.getProfile();
					wp.setHarm1f(harm1f);
					mixer.setProfile(wp);;					
					tf6.setText(Double.toString(harm1f));
				}
			}	
        });
        tf6.addFocusListener(new FocusListener() {
        	@Override
    		public void focusLost(FocusEvent e) {
    			tf6.setText(Double.toString(harm1f));
    		}
			@Override
			public void focusGained(FocusEvent e) {
			}	
        });
        add(tf6);
        tf6.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String s = tf6.getText();
					double d = -1;
					try {
						 d = Double.parseDouble(s); 					
					} catch (NumberFormatException ex) {
						tf6.setText("");
					}
					if (d < 0 || d > 22050) {						
						tf6.setText("");
					} else {
						harm1f = d;
						Oscillator.WaveProfile prof = mixer.getProfile();
						prof.setHarm1f(harm1f);
						mixer.setProfile(prof);
					}
				}	
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
        });
        
	    tf7 = new JTextField(); 
	    tf7.setText("0.0");
        tf7.setBounds(10, 374, 150, 20);
        tf7.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				mtf7 = true;
			}

			@Override
			public void mouseExited(MouseEvent e) {
				mtf7 = false;				
			}
        });
        
        tf7.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				if (mtf7) {
					DecimalFormat df = new DecimalFormat("#.#");
				    df.setRoundingMode(RoundingMode.HALF_EVEN);
					double d = harm2f - e.getWheelRotation();
					harm2f = (d < 0 ) || ( d > 22050 ) ? harm2f : Double.parseDouble(df.format(d));
					Oscillator.WaveProfile wp = mixer.getProfile();
					wp.setHarm2f(harm2f);
					mixer.setProfile(wp);;					
					tf7.setText(Double.toString(harm2f));
				}
			}	
        });
        tf7.addFocusListener(new FocusListener() {
        	@Override
    		public void focusLost(FocusEvent e) {
    			tf7.setText(Double.toString(harm2f));
    		}
			@Override
			public void focusGained(FocusEvent e) {
			}	
        });
        add(tf7);
        tf7.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String s = tf7.getText();
					double d = -1;
					try {
						 d = Double.parseDouble(s); 					
					} catch (NumberFormatException ex) {
						tf7.setText("");
					}
					if (d < 0 || d > 22050) {						
						tf7.setText("");
					} else {
						harm2f = d;
						Oscillator.WaveProfile prof = mixer.getProfile();
						prof.setHarm2f(harm2f);
						mixer.setProfile(prof);
					}
				}	
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
        });
        
	    tf8 = new JTextField();  
	    tf8.setText("0.0");
        tf8.setBounds(10, 405, 150, 20);
        tf8.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				mtf8 = true;
			}

			@Override
			public void mouseExited(MouseEvent e) {
				mtf8 = false;				
			}
        });
        
        tf8.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				if (mtf8) {
					DecimalFormat df = new DecimalFormat("#.#");
				    df.setRoundingMode(RoundingMode.HALF_EVEN);
					double d = harm3f - e.getWheelRotation();
					harm3f = (d < 0 ) || ( d > 22050 ) ? harm3f : Double.parseDouble(df.format(d));
					Oscillator.WaveProfile wp = mixer.getProfile();
					wp.setHarm3f(harm3f);
					mixer.setProfile(wp);;					
					tf8.setText(Double.toString(harm3f));
				}
			}	
        });
        tf8.addFocusListener(new FocusListener() {
        	@Override
    		public void focusLost(FocusEvent e) {
    			tf8.setText(Double.toString(harm3f));
    		}
			@Override
			public void focusGained(FocusEvent e) {
			}	
        });
        add(tf8);
        tf8.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String s = tf8.getText();
					double d = -1;
					try {
						 d = Double.parseDouble(s); 					
					} catch (NumberFormatException ex) {
						tf8.setText("");
					}
					if (d < 0 || d > 22050) {						
						tf8.setText("");
					} else {
						harm3f = d;
						Oscillator.WaveProfile prof = mixer.getProfile();
						prof.setHarm3f(harm3f);
						mixer.setProfile(prof);
					}
				}	
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
        });
        
	    tf9 = new JTextField();  
	    tf9.setText("0.25");
        tf9.setBounds(350, 312, 150, 20);
        tf9.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				mtf9 = true;
			}

			@Override
			public void mouseExited(MouseEvent e) {
				mtf9 = false;				
			}
        });
        
        tf9.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				if (mtf9) {
					DecimalFormat df = new DecimalFormat("#.##");
				    df.setRoundingMode(RoundingMode.HALF_EVEN);
					double d = roota + 0.01 * -e.getWheelRotation();
					roota = (d < 0 ) || ( d > 1 ) ? roota : Double.parseDouble(df.format(d));
					double sum = roota + harm1a + harm2a + harm3a;
					if (sum > 1) {
						df.setRoundingMode(RoundingMode.FLOOR);
						double delta = sum - 1;
						sum = harm1a + harm2a + harm3a;
						System.out.println(sum);
						harm1a = Double.parseDouble(df.format(harm1a - delta * (harm1a / sum)));
						tf10.setText(Double.toString(harm1a));
						harm2a = Double.parseDouble(df.format(harm2a - delta * (harm2a / sum)));;
						tf11.setText(Double.toString(harm2a));
						harm3a = Double.parseDouble(df.format(harm3a - delta * (harm3a / sum)));
						tf12.setText(Double.toString(harm3a));
					}
					Oscillator.WaveProfile wp = mixer.getProfile();
					wp.setRoota(roota);
					mixer.setProfile(wp);;					
					tf9.setText(Double.toString(roota));
				}
			}	
        });
        tf9.addFocusListener(new FocusListener() {
        	@Override
    		public void focusLost(FocusEvent e) {
    			tf9.setText(Double.toString(roota));
    		}
			@Override
			public void focusGained(FocusEvent e) {
			}	
        });
        add(tf9);
        tf9.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String s = tf9.getText();
					double d = -1;
					try {
						 d = Double.parseDouble(s); 					
					} catch (NumberFormatException ex) {
						tf9.setText("");
					}
					if (d < 0 || d > 1) {						
						tf9.setText("");
						
					} else {
						double sum = d + harm1a + harm2a + harm3a;
						if (sum <= 1) {
							roota = d;
							System.out.println(tf9.getText());
						} else {
							DecimalFormat df = new DecimalFormat("#.###");
						    df.setRoundingMode(RoundingMode.FLOOR);
							double delta = sum - 1;
							sum = harm1a + harm2a + harm3a;
							harm1a = Double.parseDouble(df.format(harm1a - delta * (harm1a / sum)));
							tf10.setText(Double.toString(harm1a));
							harm2a = Double.parseDouble(df.format(harm2a - delta * (harm2a / sum)));;
							tf11.setText(Double.toString(harm2a));
							harm3a = Double.parseDouble(df.format(harm3a - delta * (harm3a / sum)));
							tf12.setText(Double.toString(harm3a));
							roota = d;
							Oscillator.WaveProfile prof = mixer.getProfile();
							prof.setRoota(roota);
							mixer.setProfile(prof);
						}
					 
					}
				}	
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
        });
        
	    tf10 = new JTextField();  
	    tf10.setText("0.0");
        tf10.setBounds(350, 343, 150, 20);
        tf10.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				mtf10 = true;
			}

			@Override
			public void mouseExited(MouseEvent e) {
				mtf10 = false;				
			}
        });
        
        tf10.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				if (mtf10) {
					DecimalFormat df = new DecimalFormat("#.##");
				    df.setRoundingMode(RoundingMode.HALF_EVEN);
					double d = harm1a + 0.01 * -e.getWheelRotation();
					harm1a = (d < 0 ) || ( d > 1 ) ? harm1a : Double.parseDouble(df.format(d));
					double sum = roota + harm1a + harm2a + harm3a;
					if (sum > 1) {
						df.setRoundingMode(RoundingMode.FLOOR);
						double delta = sum - 1;
						sum = roota + harm2a + harm3a;
						System.out.println(sum);
						roota = Double.parseDouble(df.format(roota - delta * (roota / sum)));
						tf9.setText(Double.toString(roota));
						harm2a = Double.parseDouble(df.format(harm2a - delta * (harm2a / sum)));;
						tf11.setText(Double.toString(harm2a));
						harm3a = Double.parseDouble(df.format(harm3a - delta * (harm3a / sum)));
						tf12.setText(Double.toString(harm3a));
					}
					Oscillator.WaveProfile wp = mixer.getProfile();
					wp.setHarm1a(harm1a);
					mixer.setProfile(wp);;					
					tf10.setText(Double.toString(harm1a));
				}
			}	
        });
        tf10.addFocusListener(new FocusListener() {
        	@Override
    		public void focusLost(FocusEvent e) {
    			tf10.setText(Double.toString(harm1a));
    		}
			@Override
			public void focusGained(FocusEvent e) {
			}	
        });
        add(tf10);
        tf10.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String s = tf10.getText();
					double d = -1;
					try {
						 d = Double.parseDouble(s); 					
					} catch (NumberFormatException ex) {
						tf10.setText("");
					}
					if (d < 0 || d > 1) {						
						tf10.setText("");
					} else { 
						double sum = roota + d + harm2a + harm3a;
						if (sum <= 1) {
							harm1a = d;
						System.out.println(tf10.getText());
						} else {
							DecimalFormat df = new DecimalFormat("#.###");
					        df.setRoundingMode(RoundingMode.FLOOR);
							double delta = sum - 1;
							sum = roota + harm2a + harm3a;
							roota = Double.parseDouble(df.format(roota - delta * (roota / sum)));
							tf9.setText(Double.toString(roota));
							harm2a = Double.parseDouble(df.format(harm2a - delta * (harm2a/ sum)));
							tf11.setText(Double.toString(harm2a));
							harm3a = Double.parseDouble(df.format(harm3a - delta * (harm3a / sum)));
							tf12.setText(Double.toString(harm3a));
							harm1a = d;
							Oscillator.WaveProfile prof = mixer.getProfile();
							prof.setHarm1a(harm1a);
							mixer.setProfile(prof);
						}
					}
				}	
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
        });
        
        
	    tf11 = new JTextField(); 
	    tf11.setText("0.0");
        tf11.setBounds(350, 374, 150, 20);
        tf11.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				mtf11 = true;
			}

			@Override
			public void mouseExited(MouseEvent e) {
				mtf11 = false;				
			}
        });
        
        tf11.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				if (mtf11) {
					DecimalFormat df = new DecimalFormat("#.##");
				    df.setRoundingMode(RoundingMode.HALF_EVEN);
					double d = harm2a + 0.01 * -e.getWheelRotation();
					harm2a = (d < 0 ) || ( d > 1 ) ? harm2a : Double.parseDouble(df.format(d));
					double sum = roota + harm1a + harm2a + harm3a;
					if (sum > 1) {
						df.setRoundingMode(RoundingMode.FLOOR);
						double delta = sum - 1;
						sum = roota + harm1a + harm3a;
						System.out.println(sum);
						roota = Double.parseDouble(df.format(roota - delta * (roota / sum)));
						tf9.setText(Double.toString(roota));
						harm1a = Double.parseDouble(df.format(harm1a - delta * (harm1a / sum)));;
						tf10.setText(Double.toString(harm1a));
						harm3a = Double.parseDouble(df.format(harm3a - delta * (harm3a / sum)));
						tf12.setText(Double.toString(harm3a));
					}
					Oscillator.WaveProfile wp = mixer.getProfile();
					wp.setHarm2a(harm2a);
					mixer.setProfile(wp);;					
					tf11.setText(Double.toString(harm2a));
				}
			}	
        });
        tf11.addFocusListener(new FocusListener() {
        	@Override
    		public void focusLost(FocusEvent e) {
    			tf11.setText(Double.toString(harm2a));
    		}
			@Override
			public void focusGained(FocusEvent e) {
			}	
        });
        add(tf11);
        tf11.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String s = tf11.getText();
					double d = -1;
					try {
						 d = Double.parseDouble(s); 					
					} catch (NumberFormatException ex) {
						tf11.setText("");
					}
					if (d < 0 || d > 1) {						
						tf11.setText("");
					} else {
						double sum = roota + harm1a + d + harm3a;
						if (sum <= 1) {
							harm2a = d;
						System.out.println(tf11.getText());
						} else {
							DecimalFormat df = new DecimalFormat("#.###");
					        df.setRoundingMode(RoundingMode.FLOOR);
							double delta = sum - 1;
							sum = roota + harm1a + harm3a;
							roota = Double.parseDouble(df.format(roota - delta * (roota / sum)));
							tf9.setText(Double.toString(roota));
							harm1a = Double.parseDouble(df.format(harm1a - delta * (harm1a / sum)));
							tf10.setText(Double.toString(harm1a));
							harm3a = Double.parseDouble(df.format(harm3a - delta * (harm3a / sum)));
							tf12.setText(Double.toString(harm3a));
						 	harm2a = d;
							Oscillator.WaveProfile prof = mixer.getProfile();
							prof.setHarm2a(harm2a);
							mixer.setProfile(prof);

						}
					}
				}	
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
        });
             
	    tf12 = new JTextField(); 
	    tf12.setText("0.0");
        tf12.setBounds(350, 405, 150, 20);
        tf12.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				mtf12 = true;
			}

			@Override
			public void mouseExited(MouseEvent e) {
				mtf12 = false;				
			}
        });
        
        tf12.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				if (mtf12) {
					DecimalFormat df = new DecimalFormat("#.##");
				    df.setRoundingMode(RoundingMode.HALF_EVEN);
					double d = harm3a + 0.01 * -e.getWheelRotation();
					harm3a = (d < 0 ) || ( d > 1 ) ? harm3a : Double.parseDouble(df.format(d));
					double sum = roota + harm1a + harm2a + harm3a;
					if (sum > 1) {
						df.setRoundingMode(RoundingMode.FLOOR);
						double delta = sum - 1;
						sum = roota + harm1a + harm2a;
						System.out.println(sum);
						roota = Double.parseDouble(df.format(roota - delta * (roota / sum)));
						tf9.setText(Double.toString(roota));
						harm1a = Double.parseDouble(df.format(harm1a - delta * (harm1a / sum)));;
						tf10.setText(Double.toString(harm1a));
						harm2a = Double.parseDouble(df.format(harm2a - delta * (harm2a / sum)));
						tf11.setText(Double.toString(harm2a));
					}
					Oscillator.WaveProfile wp = mixer.getProfile();
					wp.setHarm3a(harm3a);
					mixer.setProfile(wp);;					
					tf12.setText(Double.toString(harm3a));
				}
			}	
        });
        tf12.addFocusListener(new FocusListener() {
        	@Override
    		public void focusLost(FocusEvent e) {
    			tf12.setText(Double.toString(harm3a));
    		}
			@Override
			public void focusGained(FocusEvent e) {
			}	
        });
        add(tf12); 
        tf12.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String s = tf12.getText();
					double d = -1;
					try {
						 d = Double.parseDouble(s); 					
					} catch (NumberFormatException ex) {
						tf12.setText("");
					}
					if (d < 0 || d > 1) {						
						tf12.setText("");
					} else {
						double sum = roota + harm1a + harm2a + d;
						if (sum <= 1) {
							harm3a = d;
						    System.out.println(tf12.getText());
						} else {	
							DecimalFormat df = new DecimalFormat("#.###");
					        df.setRoundingMode(RoundingMode.FLOOR);
							double delta = sum - 1;
							sum = roota + harm1a + harm2a;
							roota = Double.parseDouble(df.format(roota - delta * (roota / sum)));
							tf9.setText(Double.toString(roota));
							harm1a = Double.parseDouble(df.format(harm1a - delta * (harm1a / sum)));
							tf10.setText(Double.toString(harm1a));
							harm2a = Double.parseDouble(df.format(harm2a - delta * (harm2a / sum)));
							tf11.setText(Double.toString(harm2a));
							harm3a = d;
							Oscillator.WaveProfile prof = mixer.getProfile();
							prof.setHarm3a(harm3a);
							mixer.setProfile(prof);
						}
					}

				}	
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
        });
        
        
        B1 =new JButton("1");  
        B1.addKeyListener(new KeyB());
        B1.setBounds(40, 50, 50, 200);
        B1.setBackground(new Color(255, 255, 255));
        B1.addChangeListener(new ChangeListener(){
        	private boolean test = false;
			@Override
			public void stateChanged(ChangeEvent e) {
				if (!test && B1.getModel().isArmed()) {
					mixer.pressKey(0);
					test = true;
					System.out.println("ON");
					
				} else if(test && !B1.getModel().isArmed()) {
					mixer.releaseKey(0);
					test = false;
					System.out.println("OFF");
					
				}
				
			}
        	
        });
        add(B1);
    
        B2 =new JButton("2");  
        B2.addKeyListener(new KeyB());
        B2.setBounds(89, 50, 50, 200);
        B2.setBackground(new Color(255, 255, 255));
        B2.addChangeListener(new ChangeListener(){
        	private boolean test = false;
			@Override
			public void stateChanged(ChangeEvent e) {
				if (!test && B2.getModel().isArmed()) {
					mixer.pressKey(1);
					test = true;
					System.out.println("ON");
					
					
				} else if(test && !B2.getModel().isArmed()) {
					mixer.releaseKey(1);
					test = false;
					System.out.println("OFF");
				}
				
			}
        	
        });
        add(B2);
        
        B3 =new JButton("3");  
        B3.addKeyListener(new KeyB());
        B3.setBounds(138, 50, 50, 200);
        B3.setBackground(new Color(255, 255, 255));
        B3.addChangeListener(new ChangeListener(){
        	
        	private boolean test = false;
			@Override
			public void stateChanged(ChangeEvent e) {
				if (!test && B3.getModel().isArmed()) {
					mixer.pressKey(2);
					test = true;
					System.out.println("ON");
					
				} else if(test && !B3.getModel().isArmed()) {
					mixer.releaseKey(2);
					test = false;
					System.out.println("OFF");
				}
				
			}
        	
        });
        add(B3);
        
        B4 =new JButton("4");  
        B4.addKeyListener(new KeyB());
        B4.setBounds(187, 50, 50, 200);
        B4.setBackground(new Color(255, 255, 255));
        B4.addChangeListener(new ChangeListener(){
        	private boolean test = false;
			@Override
			public void stateChanged(ChangeEvent e) {
				if (!test && B4.getModel().isArmed()) {
					mixer.pressKey(3);
					test = true;
					System.out.println("ON");
					
				} else if(test && !B4.getModel().isArmed()) {
					mixer.releaseKey(3);
					test = false;
					System.out.println("OFF");
				}
				
			}
        	
        });
        add(B4);
        
        B5 =new JButton("5");  
        B5.addKeyListener(new KeyB());
        B5.setBounds(236, 50, 50, 200);
        B5.setBackground(new Color(255, 255, 255));
        B5.addChangeListener(new ChangeListener(){
        	private boolean test = false;
			@Override
			public void stateChanged(ChangeEvent e) {
				if (!test && B5.getModel().isArmed()) {
					mixer.pressKey(4);
					test = true;
					System.out.println("ON");
					
				} else if(test && !B5.getModel().isArmed()) {
					mixer.releaseKey(4);
					test = false;
					System.out.println("OFF");
					
				}
				
			}
        	
        });
        add(B5);
        
        B6 =new JButton("6"); 
        B6.addKeyListener(new KeyB());
        B6.setBounds(285, 50, 50, 200);
        B6.setBackground(new Color(255, 255, 255));
        B6.addChangeListener(new ChangeListener(){
        	private boolean test = false;
			@Override
			public void stateChanged(ChangeEvent e) {
				if (!test && B6.getModel().isArmed()) {
					mixer.pressKey(5);
					test = true;
					System.out.println("ON");
					
				} else if(test && !B6.getModel().isArmed()) {
					mixer.releaseKey(5);
					test = false;
					System.out.println("OFF");
					
				}
				
			}
        	
        });
        add(B6);
        
        B7 =new JButton("7");  
        B7.addKeyListener(new KeyB());
        B7.setBounds(334, 50, 50, 200);
        B7.setBackground(new Color(255, 255, 255));
        B7.addChangeListener(new ChangeListener(){
        	private boolean test = false;
			@Override
			public void stateChanged(ChangeEvent e) {
				if (!test && B7.getModel().isArmed()) {
					mixer.pressKey(6);
					test = true;
					System.out.println("ON");
					
				} else if(test && !B7.getModel().isArmed()) {
					mixer.releaseKey(6);
					test = false;
					System.out.println("OFF");
				}
				
			}
        	
        });
        add(B7);

        B8 =new JButton("8");  
        B8.addKeyListener(new KeyB());
        B8.setBounds(383, 50, 50, 200);
        B8.setBackground(new Color(255, 255, 255));
        B8.addChangeListener(new ChangeListener(){

        	private boolean test = false;
			@Override
			public void stateChanged(ChangeEvent e) {
				if (!test && B8.getModel().isArmed()) {
					mixer.pressKey(7);
					test = true;
					System.out.println("ON");
					
					
				} else if(test && !B8.getModel().isArmed()) {
					mixer.releaseKey(7);
					test = false;
					System.out.print("OFF");
				}
				
			}
        	
        });
        add(B8);
        
        B9 =new JButton("9");  
        B9.addKeyListener(new KeyB());
        B9.setBounds(432, 50, 50, 200);
        B9.setBackground(new Color(255, 255, 255));
        B9.addChangeListener(new ChangeListener(){
        	private boolean test = false;
			@Override
			public void stateChanged(ChangeEvent e) {
				if (!test && B9.getModel().isArmed()) {
					mixer.pressKey(8);
					test = true;
					System.out.println("ON");
					
				} else if(test && !B9.getModel().isArmed()) {
					mixer.releaseKey(8);
					test = false;
					System.out.println("OFF");
				}
				
			}
        	
        });
        add(B9);
        
        B10 =new JButton("0");  
        B10.addKeyListener(new KeyB());
        B10.setBounds(481, 50, 50, 200);
        B10.setBackground(new Color(255, 255, 255));
        B10.addChangeListener(new ChangeListener(){
        	private boolean test = false;
			@Override
			public void stateChanged(ChangeEvent e) {
				if (!test && B10.getModel().isArmed()) {
					mixer.pressKey(9);
					test = true;
					System.out.println("ON");
					
				} else if(test && !B10.getModel().isArmed()) {
					mixer.releaseKey(9);
					test = false;
					System.out.println("OFF");
				}
				
			}
        	
        });
        add(B10);

        
        B11 =new JButton("-");  
        B11.addKeyListener(new KeyB());
        B11.setBounds(530, 50, 50, 200);
        B11.setBackground(new Color(255, 255, 255));
        B11.addChangeListener(new ChangeListener(){
        	private boolean test = false;
			@Override
			public void stateChanged(ChangeEvent e) {
				if (!test && B11.getModel().isArmed()) {
					mixer.pressKey(10);
					test = true;
					System.out.println("ON");
					
				} else if(test && !B11.getModel().isArmed()) {
					mixer.releaseKey(10);
					test = false;
					System.out.println("OFF");
				}
				
			}
        	
        });
        add(B11);
        
        B12 =new JButton("=");  
        B12.addKeyListener(new KeyB());
        B12.setBounds(579, 50, 50, 200);
        B12.setBackground(new Color(255, 255, 255));
        B12.addChangeListener(new ChangeListener(){
        	private boolean test = false;
			@Override
			public void stateChanged(ChangeEvent e) {				
				if (!test && B12.getModel().isArmed()) {					
					mixer.pressKey(11);
					test = true;
					System.out.println("ON");
					
				} else if(test && !B12.getModel().isArmed()) {
					mixer.releaseKey(11);
					test = false;
					System.out.println("OFF");
				}
				
			}
        	
        });
        add(B12);

        addWindowListener(new WindowListener(){

			@Override
			public void windowOpened(WindowEvent e) {
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				end = true;
				
			}

			@Override
			public void windowClosed(WindowEvent e) {

				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				
			}
        	
        });
        
        ImageIcon m = new ImageIcon("./Image/Spike.png");  
        JLabel spike = new JLabel(m);
        spike.setBounds(650, 200, 250, 250);
        add(spike);

	    this.setVisible(true); 
    }
	
	private class KeyB implements KeyListener{
		boolean Pressed1 = false;
		boolean Pressed2 = false;
		boolean Pressed3 = false;
		boolean Pressed4 = false;
		boolean Pressed5 = false;
		boolean Pressed6 = false;
		boolean Pressed7 = false;
		boolean Pressed8 = false;
		boolean Pressed9 = false;
		boolean Pressed10 = false;
		boolean Pressed11 = false;
		boolean Pressed12 = false;
  	    
		public void keyTyped(KeyEvent n) {
  	    }
  	    
		public void keyPressed(KeyEvent n) {
			Character c = n.getKeyChar(); 
			if (!Pressed1 && c.equals('1')) {
				Pressed1 = true;
				mixer.pressKey(0);
				B1.setBackground(new Color (184, 205,231));
			} else if (!Pressed2 && c.equals('2')) {
				Pressed2 = true;
				B2.setBackground(new Color (184, 205,231));
				mixer.pressKey(1);
			} else if (!Pressed3 && c.equals('3')) {
				Pressed3 = true;
				B3.setBackground(new Color (184, 205,231));
				mixer.pressKey(2);
			} else if (!Pressed4 && c.equals('4')) { 
				Pressed4 = true;
				B4.setBackground(new Color (184, 205,231));
				mixer.pressKey(3);
			} else if (!Pressed5 && c.equals('5')) {
				Pressed5 = true;
				B5.setBackground(new Color (184, 205,231));
				mixer.pressKey(4);
			} else if (!Pressed6 && c.equals('6')) {
				Pressed6 = true;
				B6.setBackground(new Color (184, 205,231));
				mixer.pressKey(5);
			} else if (!Pressed7 && c.equals('7')) {
				Pressed7 = true;
				B7.setBackground(new Color (184, 205,231));
				mixer.pressKey(6);
			} else if (!Pressed8 && c.equals('8')) {
				Pressed8 = true;
				B8.setBackground(new Color (184, 205,231));
				mixer.pressKey(7);
			} else if (!Pressed9 && c.equals('9')) {
				Pressed9 = true;
				B9.setBackground(new Color (184, 205,231));
				mixer.pressKey(8);
			} else if (!Pressed10 && c.equals('0')) {
				Pressed10 = true;
				B10.setBackground(new Color (184, 205,231));
				mixer.pressKey(9);
			} else if (!Pressed11 && c.equals('-')) {
				Pressed11 = true;
				B11.setBackground(new Color (184, 205,231));
				mixer.pressKey(10);
			} else if (!Pressed12 && c.equals('=')) {
				Pressed12 = true;
				B12.setBackground(new Color (184, 205,231));
				mixer.pressKey(11);
			}	
		}
		
		public void keyReleased(KeyEvent n) {
			Character c = n.getKeyChar(); 
			if (c.equals('1')) {
				Pressed1 = false;
				B1.setBackground(Color.WHITE);
				mixer.releaseKey(0);
			} else if (c.equals('2')) {
				Pressed2 = false;
				B2.setBackground(Color.WHITE);
				mixer.releaseKey(1);
			} else if (c.equals('3')) {
				Pressed3 = false;
				B3.setBackground(Color.WHITE);
				mixer.releaseKey(2);
			} else if (c.equals('4')) { 
				Pressed4 = false;
				B4.setBackground(Color.WHITE);
				mixer.releaseKey(3);
			} else if (c.equals('5')) {
				Pressed5 = false;
				B5.setBackground(Color.WHITE);
				mixer.releaseKey(4);
			} else if (c.equals('6')) {
				Pressed6 = false;
				B6.setBackground(Color.WHITE);
				mixer.releaseKey(5);
			} else if (c.equals('7')) {
				Pressed7 = false;
				B7.setBackground(Color.WHITE);
				mixer.releaseKey(6);
			} else if (c.equals('8')) {
				Pressed8 = false;
				B8.setBackground(Color.WHITE);
				mixer.releaseKey(7);
			} else if (c.equals('9')) {
				Pressed9 = false;
				B9.setBackground(Color.WHITE);
				mixer.releaseKey(8);
			} else if (c.equals('0')) {
				Pressed10 = false;
				B10.setBackground(Color.WHITE);
				mixer.releaseKey(9);
			} else if (c.equals('-')) {
				Pressed11 = false;
				B11.setBackground(Color.WHITE);
				mixer.releaseKey(10);
			} else if (c.equals('=')) {
				Pressed12 = false;
				B12.setBackground(Color.WHITE);
				mixer.releaseKey(11);
			}
		}
    }	
}
