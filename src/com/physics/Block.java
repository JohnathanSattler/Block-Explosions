package com.physics;

import java.util.concurrent.CopyOnWriteArrayList;

import com.Main;

public class Block extends BlockSetup {
	
	private CopyOnWriteArrayList<Block> blocks;
	
	public Block(int w, int h) {
		super(w, h);
	}
	
	public void setBlocks(CopyOnWriteArrayList<Block> b)
	{
		blocks = b;
	}
	
	public boolean exploded = false;
	public boolean booming = false;
	public void boom()
	{
		setForce(0);
		booming = true;
	}
	
	public void move()
	{
		double xo = getX();
		double yo = getY();
		
		xo += getXForce() * getXDir();
		yo += getYForce() * getYDir();
		
		// if hit left or right of screen
		if(xo < 0 || xo >= Main.WIDTH - getSize().width)
		{
			// if to left of screen
			if(xo < 0)
			{
				xo = 0;
			}
			// if to right of screen
			if(xo >= Main.WIDTH - getSize().width)
			{
				xo = Main.WIDTH - getSize().width;
			}
			
			// if fast enough to bounce off
			//if(getForce() > 2)
			//{
				flipXDir();
				//setForce(getForce() - getForce() / 100);
			//}
		}
		
		// if hit top or bottom of screen
		if(yo < 0 || yo >= Main.HEIGHT - getSize().height)
		{
			// if above screen
			if(yo < 0)
			{
				yo = 0;
			}
			// if below screen
			if(yo >= Main.HEIGHT - getSize().height)
			{
				yo = Main.HEIGHT - getSize().height;
			}
			
			// if fast enough to bounce off
			//if(getForce() > 2)
			//{
				flipYDir();
				//setForce(getForce() - getForce() / 100);
			//}
		}
		
		// If sliding on a wall
		if((xo == 0 && getX() == 0) || (xo == Main.WIDTH - getSize().width && getX() == Main.WIDTH - getSize().width) ||
		   (yo == 0 && getY() == 0) || (yo == Main.HEIGHT - getSize().height && getY() == Main.HEIGHT - getSize().height))
		{ // Slow down
			setForce(getForce() - 0.01);
		}
		
		double x = xo;
		double y = yo;
		for(Block b: blocks)
		{ 
			if(xo + getSize().width >= b.getX() && xo < b.getX() + b.getSize().width &&
			   yo + getSize().height >= b.getY() && yo < b.getY() + b.getSize().height && !b.exploded)
			{
				// if this was to left of block
				if(getX() + getSize().width < b.getX())
				{
					while(x + getSize().width > b.getX())
					{
						x--;
					}
					
					flipXDir();
					if(b.getXDir() < 0)
					{
						//flipXDir();
						b.flipXDir();
						//double tempF = b.getForce() - getForce() / 10;
						//double tempF = (getForce() + b.getForce()) / 2;
						//setForce(getForce() - b.getForce() / 10);
						//setForce(tempF);
						//b.setForce(tempF);
					} else {
						//b.setForce(b.getForce() + getForce() / 2);
						//setForce(getForce() / 2);
					}
					if(b.getForce() < 0)
					{
						b.setForce(Math.abs(b.getForce()));
						b.flipXDir();
					}
					
					
					
					//Explode
					if(getIndex() != b.getIndex() && !b.exploded)
					{
						boom();
						b.boom();
					}
				}
				
				// if this was to right of block
				if(getX() >= b.getX() + b.getSize().width)
				{
					while(x < b.getX() + b.getSize().width)
					{
						x++;
					}
					
					flipXDir();
					if(b.getXDir() > 0)
					{
						//flipXDir();
						b.flipXDir();
						//double tempF = b.getForce() - getForce() / 10;
						//double tempF = (getForce() + b.getForce()) / 2;
						//setForce(getForce() - b.getForce() / 10);
						//setForce(tempF);
						//b.setForce(tempF);
					} else {
						//b.setForce(b.getForce() + getForce() / 2);
						//setForce(getForce() / 2);
					}
					if(b.getForce() < 0)
					{
						b.setForce(Math.abs(b.getForce()));
						b.flipXDir();
					}
					
					
					
					//Explode
					if(getIndex() != b.getIndex() && !b.exploded)
					{
						boom();
						b.boom();
					}
				}
				
				// if this was above block
				if(getY() + getSize().height < b.getY())
				{
					while(y + getSize().height >= b.getY())
					{
						y--;
					}
					
					flipYDir();
					if(b.getYDir() < 0)
					{
						//flipYDir();
						b.flipYDir();
						//double tempF = b.getForce() - getForce() / 10;
						//double tempF = (getForce() + b.getForce()) / 2;
						//setForce(getForce() - b.getForce() / 10);
						//setForce(tempF);
						//b.setForce(tempF);
					} else {
						//b.setForce(b.getForce() + getForce() / 2);
						//setForce(getForce() / 2);
					}
					if(b.getForce() < 0)
					{
						b.setForce(Math.abs(b.getForce()));
						b.flipYDir();
					}
					
					
					
					//Explode
					if(getIndex() != b.getIndex() && !b.exploded)
					{
						boom();
						b.boom();
					}
				}
					
				// if this was below block
				if(getY() >= b.getY() + b.getSize().height)
				{
					while(y < b.getY() + b.getSize().height)
					{
						y++;
					}
					
					flipYDir();
					if(b.getYDir() > 0)
					{
						//flipYDir();
						b.flipYDir();
						//double tempF = b.getForce() - getForce() / 10;
						//double tempF = (getForce() + b.getForce()) / 2;
						//setForce(getForce() - b.getForce() / 10);
						//setForce(tempF);
						//b.setForce(tempF);
					} else {
						//b.setForce(b.getForce() + getForce() / 2);
						//setForce(getForce() / 2);
					}
					if(b.getForce() < 0)
					{
						b.setForce(Math.abs(b.getForce()));
						b.flipYDir();
					}
					
					
					
					//Explode
					if(getIndex() != b.getIndex() && !b.exploded)
					{
						boom();
						b.boom();
					}
				}
			}
		}
		xo = x;
		yo = y;
		
		setAng(-(Math.toDegrees(Math.atan2(getX() - xo, yo - getY())) + 90));
		while(getAng() < 0)
		{
			setAng(getAng() + 360);
		}
		
		// If not moving (or moving very slow)
		if(xo == getX() && yo == getY())
		{ // stop
			setForce(0);
		} else { // continue moving
			setX(xo);
			setY(yo);
		}
	}
	
}
