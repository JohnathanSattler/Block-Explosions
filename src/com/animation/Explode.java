package com.animation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import com.Art.Tiles;

public class Explode {
	
	public int cur = 0;
	public boolean running = false;
	
	public void start()
	{
		running = true;
		t.start();
	}
	
	final Timer t = new Timer(50, new ActionListener(){
		public void actionPerformed(ActionEvent e)
		{
			if(cur < Tiles.explosions.length - 1)
			{
				next();
			} else {
				running = false;
				((Timer) e.getSource()).stop();
			}
		}
	});
	
	public void next()
	{
		if(cur < Tiles.explosions.length - 1)
		{
			cur++;
		} else {
			running = false;
		}
	}
	
}
