package com.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.Game;
import com.physics.Block;

public class GameGraphics extends Game {
	
	public long test;
	
	public void draw(Graphics g)
	{
		for(Block b: blocks)
		{
			((BlockGraphics) b).draw(g);
		}
		
		if(isCreating())
		{
			test = System.currentTimeMillis();
		}
		if(System.currentTimeMillis() - test <= 100)
		{
			drawCursor(g);
		}
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		g.drawString("Size: " + getSize(), 5, 20);
	}
	
	private void drawCursor(Graphics g)
	{
		g.setColor(Color.BLUE);
		//g.drawLine(getStartX(), getStartY(), getEndX(), getStartY());
		//g.drawLine(getStartX(), getStartY(), getStartX(), getEndY());
		
		g.setColor(Color.RED);
		//g.drawLine(getEndX(), getStartY(), getEndX(), getEndY());
		//g.drawLine(getStartX(), getEndY(), getEndX(), getEndY());
		
		g.setColor(Color.GREEN);
		g.drawLine(getStartX(), getStartY(), getEndX(), getEndY());
		//g.drawLine(getStartX(), getEndY(), getStartX(), getEndY());
		//g.drawLine(getEndX(), getStartY(), getEndX(), getStartY());
		
		g.setColor(Color.BLUE);
		g.fillOval(getStartX() - 10, getStartY() - 10, 20, 20);
		
		g.setColor(Color.RED);
		g.fillOval(getEndX() - 10, getEndY() - 10, 20, 20);
	}
	
}
