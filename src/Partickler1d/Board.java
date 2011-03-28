package Partickler1d;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import javax.swing.JPanel;

public class Board extends JPanel implements Runnable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7263171567980839267L;
	private volatile Thread animator;
    private int iTimer = 0;
    private volatile boolean runAnim = false;
    ParticleData1d DataModule; 
    private double dXScaleFactor=1;

    private final int DELAY = 50;


    public Board(ParticleData1d DataMod) {
    	DataModule = DataMod;
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        
        //runAnim = false;
    }
    
    public boolean isRunning() {
    	return runAnim;
    }
    
    public void toggleRun() {
    	if (runAnim) {
    		runAnim = false;
    		//animator = null;
    		stop();
    	} else {
    		if(DataModule.size()>0) {
    			//calculate scalfactor
    			//dScaleFactor
    			double testPos;
    			if(DataModule.getMaxPos0()>Math.abs(DataModule.getMinPos0())) {
    				testPos = DataModule.getMaxPos0(); 
    			} else {
    				testPos = DataModule.getMinPos0();
    			}
    				
    			if(testPos*4>this.getWidth()) {
    				dXScaleFactor = this.getWidth()/(testPos*4); 
    			}
	    		runAnim = true;
	    		iTimer = 0;
	    		animator = new Thread(this);
	    		animator.start();
    		} else {
    			System.out.println("no data to show. there is a problem");
    		}

    	}
    }
    
    public void addNotify() {
        super.addNotify();
    }

    public void paint(Graphics g) {
        super.paint(g);
        
        if (runAnim) {
        	Graphics2D g2d = (Graphics2D)g;
        	
        	for (int i = 0; i<DataModule.size();i++) {
        		double pos0 = DataModule.getPatIndex(i);
        		double vel = DataModule.getVatIndex(i);
        		Ellipse2D e = new Ellipse2D.Double(0, 0, 1, 1);
        		if (vel>0) {
        			g2d.setPaint(Color.GREEN);
        		} else if (vel<0) {
        			g2d.setPaint(Color.RED);
        		} else {
        			g2d.setPaint(Color.BLUE);
        		}
        		double dX0 =(pos0*dXScaleFactor)+(this.getWidth()/2);
        		double dXt = vel*iTimer*dXScaleFactor;
        		double dY0 =this.getHeight();
        		double dYt = iTimer*(-1);
        		
        		g2d.setStroke(new BasicStroke());
    	        AffineTransform at =
    	            AffineTransform.getTranslateInstance(dXt+dX0, dYt+dY0);
    	        g2d.draw(at.createTransformedShape(e));
    	        g2d.fill(at.createTransformedShape(e));
    	        Line2D lin = new Line2D.Double(dX0, dY0, dXt+dX0, dYt+dY0);
    	        g2d.draw(lin);
        	}
        	g2d.setPaint(Color.WHITE);
        	float[] dashPattern = { 30, 10, 10, 10 };
	        g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT,
	                                      BasicStroke.JOIN_MITER, 10,
	                                      dashPattern, 0));
	        Line2D lin = new Line2D.Double(this.getWidth()/2, 0, this.getWidth()/2, this.getHeight());
	        g2d.draw(lin);
        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }


    public void cycle() {

    	iTimer++;
    	if(iTimer>this.getHeight()) {
    		runAnim = false;
    		//animator.interrupt();
    	}
    }

    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();
        
        
        while (animator == Thread.currentThread()) {

            cycle();
            repaint();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0)
                sleep = 2;
            try {
                Thread.sleep(sleep);
                synchronized(this) {
                    while (!runAnim && animator==Thread.currentThread())
                        wait();
                }
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }

            beforeTime = System.currentTimeMillis();
        }
    }
    
    public synchronized void stop() {
        animator = null;
        notify();
    }
}
