package com.gui;

import java.awt.Graphics;

import com.Art.Tiles;
import com.animation.Explode;
import com.physics.Block;

public class BlockGraphics extends Block {
	
	private Explode e;
	
	public BlockGraphics(int w, int h) {
		super(w, h);
	}
	
	public void draw(Graphics g)
	{
		int x = (int) getX();
		int y = (int) getY();
		int w = getSize().width;
		int h = getSize().height;
		
		if(!exploded && booming)
		{
			e = new Explode();
			exploded = true;
			e.start();
		}
		if(!exploded)
		{
			g.drawImage(Tiles.block, x, y, w, h, null);
		} else {
			if(e.running)
			{
				g.drawImage(Tiles.explosions[e.cur], x - w / 2, y - h / 2, w * 2, h * 2, null);
			} else {
				booming = false;
				done = true;
			}
		}
		
		//drawMenu(g);
	}
	
	/*private void drawMenu(Graphics g)
	{
		int x = (int) getX();
		int y = (int) getY();
		int h = getSize().height;
		
		int menuX = x;
		int menuY = y + h;
		
		String longString = "Pos: " + x + ", " + y;
		
		if(menuX < 4)
		{
			menuX = 4;
		}
		if(menuX > Main.WIDTH - longString.length() * 5 - 5)
		{
			menuX = Main.WIDTH - longString.length() * 5 - 5;
		}
		if(menuY > Main.HEIGHT - 65)
		{
			menuY = Main.HEIGHT - 65;
		}
		
		g.setColor(Color.GREEN);
		g.setFont(new Font("Arial", Font.PLAIN, 10));
		g.drawString("F: " + ((int) (getForce() * 100)) / 100.0, menuX, menuY + 10);
		g.drawString("A: " + ((int) (getAng() * 100)) / 100.0, menuX, menuY + 20);
		g.drawString("Fx: " + ((int) (getXForce() * 100)) / 100.0, menuX, menuY + 30);
		g.drawString("Fy: " + ((int) (getYForce() * 100)) / 100.0, menuX, menuY + 40);
		g.drawString("Xx: " + getXDir() + ", Xy: " + getYDir(), menuX, menuY + 50);
		g.drawString(longString, menuX, menuY + 60);
	}*/
	
}
