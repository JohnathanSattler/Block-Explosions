package com.gui;

import java.awt.Color;
import java.awt.Font;
//import java.awt.Font;
import java.awt.Graphics;

import com.Game;
import com.Main;
import com.Art.Tiles;

public class Screen {
	
	public void drawGame(Graphics g, Game game, Main main)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
		
		((GameGraphics) game).draw(g);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		//g.drawString("Fps: " + main.fps, 5, Main.HEIGHT - 5);
	}
	
	public void draw(Graphics g, Game game, Main main)
	{
		if(Main.loaded)
		{
			drawGame(g, game, main);
		} else {
			Tiles.load();
			Main.loaded = true;
		}
	}
	
}
