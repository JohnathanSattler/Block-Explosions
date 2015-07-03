package com;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.concurrent.CopyOnWriteArrayList;

import com.gui.BlockGraphics;
import com.physics.Block;

public class Game {
	
	public CopyOnWriteArrayList<Block> blocks;
	
	public Game()
	{
		blocks = new CopyOnWriteArrayList<Block>();
		
		lastAdded = System.currentTimeMillis();
		
		for(int i = 0; i < 100; i++)
		{
			//addBlock(1);
		}
	}
	
	private long lastAdded;
	public void update()
	{
		for(Block b: blocks)
		{
			b.update();
		}
		
		int i = 0;
		for(Block b: blocks)
		{
			if(b.done)
			{
				blocks.remove(i);
				break;
			}
			i++;
		}
		
		if(System.currentTimeMillis() - lastAdded >= 1000 && blocks.size() < 0)
		{
			int size = (int) (Math.random() * (64 - 32) + 32);
			
			addBlock(size);
			
			lastAdded = System.currentTimeMillis();
		}
	}
	
	int id = 0;
	private void addBlock(int size)
	{
		int index = blocks.size();
		
		blocks.add(new BlockGraphics(size, size));
		blocks.get(index).setIndex(id);
		id++;
		blocks.get(index).setBlocks(blocks);
		blocks.get(index).start();
	}
	
	private void addBlock(int size, double f, double a, int xd, int yd, double xo, double yo)
	{
		int index = blocks.size();
		
		blocks.add(new BlockGraphics(size, size));
		blocks.get(index).setIndex(id);
		id++;
		blocks.get(index).setStats(f, a, xd, yd, xo, yo);
		blocks.get(index).setBlocks(blocks);
		blocks.get(index).start();
	}
	
	
	private boolean creating = false;
	public boolean isCreating(){return creating;}
	
	private int startX, startY;
	public int getStartX(){return startX;}
	public int getStartY(){return startY;}
	
	private int endX, endY;
	public int getEndX(){return endX;}
	public int getEndY(){return endY;}
	
	private boolean canCreate = false;
	
	public void mousePressed(MouseEvent e)
	{
		int key = e.getButton();
		
		if(key == MouseEvent.BUTTON1)
		{
			canCreate = true;
			
			creating = true;
			startX = e.getX() / Main.SCALE;
			startY = e.getY() / Main.SCALE;
			endX = startX;
			endY = startY;
		}
	}
	
	public void mouseReleased(MouseEvent e)
	{
		int key = e.getButton();
		
		if(key == MouseEvent.BUTTON1)
		{
			endX = e.getX() / Main.SCALE;
			endY = e.getY() / Main.SCALE;
			
			if(endX < 0)
				endX = 0;
			if(endX >= Main.WIDTH)
				endX = Main.WIDTH - 1;
			if(endY < 0)
				endY = 0;
			if(endY >= Main.HEIGHT)
				endY = Main.HEIGHT - 1;
			
			creating = false;
			
			if(canCreate)
			{
				addBlock(/*(int) (Math.random() * (32 - 32) + 32)*/ size , Math.sqrt(((startX - endX) * (startX - endX)) + ((startY - endY) * (startY - endY))) / 10, Math.toDegrees(Math.atan2(Math.abs(startY - endY), Math.abs(startX - endX))), (startX - endX >= 0) ? 1 : -1, (startY - endY >= 0) ? 1 : -1, endX - size / 2, endY - size / 2);
			}
		}
	}
	
	public void mouseDragged(MouseEvent e)
	{
		endX = e.getX() / Main.SCALE;
		endY = e.getY() / Main.SCALE;
		
		if(endX < 0)
			endX = 0;
		if(endX >= Main.WIDTH)
			endX = Main.WIDTH - 1;
		if(endY < 0)
			endY = 0;
		if(endY >= Main.HEIGHT)
			endY = Main.HEIGHT - 1;
	}
	
	
	private int size = 32;
	public int getSize(){return size;}
	
	public void mouseWheelMoved(MouseWheelEvent e)
	{
		int k = e.getWheelRotation();
		
		if(k > 0)
		{
			if(size > 16)
			{
				size--;
			}
		} else {
			if(size < 64)
			{
				size++;
			}
		}
	}
	
}
