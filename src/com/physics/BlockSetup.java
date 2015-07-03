package com.physics;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import com.Main;

public abstract class BlockSetup {
	
	private int index;
	
	private int w, h;
	
	private double force, angle, ang;
	private int xDir, yDir;
	private double xOff, yOff;
	private double xo, yo;
	
	private ActionListener moveAction;
	private Timer move;
	
	//private ActionListener slowDown;
	//private Timer slow;
	
	
	public int getIndex(){return index;}
	public void setIndex(int i){index = i;}
	
	public Dimension getSize(){return new Dimension(w, h);}
	
	public double getForce(){return force;}
	public double getAngle(){return angle;}
	public double getAng(){return ang;}
	public void setForce(double f){force = f;}
	public void setAngle(double a){angle = a;}
	public void setAng(double a){ang = a;}
	
	public int getXDir(){return xDir;}
	public int getYDir(){return yDir;}
	public void flipXDir(){xDir = -xDir;}
	public void flipYDir(){yDir = -yDir;}
	
	public double getXForce(){return xOff;}
	public double getYForce(){return yOff;}
	
	public double getX(){return xo;}
	public double getY(){return yo;}
	public void setX(double x){xo = x;}
	public void setY(double y){yo = y;}
	
	public BlockSetup(int w, int h)
	{
		this.w = w;
		this.h = h;
		
		moveAction = new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(force > 0.01)
				{
					move();
				}
			}
		};
		move = new Timer(10, moveAction);
		
		/*slowDown = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(force > 0)
				{
					force += -force / 1000;
					if(force < 0)
					{
						force = 0;
					}
				}
				if(force <= 0.01){
					force = 0;
				}
				xOff = force * Math.cos(Math.toRadians(angle));
				yOff = force * Math.sin(Math.toRadians(angle));
			}
		};*/
		//slow = new Timer(10, slowDown);
		
		//setStats(50, Math.toDegrees(Math.atan2(Main.HEIGHT, Main.WIDTH)), 1, 1);
		setStats(50, Math.random() * (70 - 20) + 20, 1, 1, Math.random() * (Main.WIDTH - w), 0);
	}
	
	public void start()
	{
		if(move.isRunning())// || slow.isRunning())
			stop();
		move.start();
		//slow.start();
	}
	
	public void stop()
	{
		move.stop();
		//slow.stop();
	}
	
	public void setStats(double f, double a, int xd, int yd, double xo, double yo)
	{
		force = f;
		angle = a;
		ang = angle;
		
		if(xd < 0)
			xDir = -1;
		else
			xDir = 1;
		
		if(yd < 0)
			yDir = -1;
		else
			yDir = 1;
		
		xOff = force * Math.cos(Math.toRadians(angle));
		yOff = force * Math.sin(Math.toRadians(angle));
		
		this.xo = xo;
		this.yo = yo;
	}
	
	private boolean restart = false;
	public boolean done = false;
	private long stopTime;
	public void update()
	{
		if(force == 0)
		{
			move.stop();
			//slow.stop();
			
			if(!restart)
			{
				stopTime = System.currentTimeMillis();
				restart = true;
			}
			
			if(System.currentTimeMillis() - stopTime >= 5000 && restart)
			{
				restart = false;
				//done = true;
				//setStats(50, Math.random() * (70 - 20) + 20, 1, 1, Math.random() * (Main.WIDTH - w), 0);
				
				//move.start();
				//slow.start();
			}
		}
	}
	
	public abstract void move();
	
}
