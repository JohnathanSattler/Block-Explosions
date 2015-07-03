package com;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import com.gui.GameGraphics;
import com.gui.Screen;

public class Main extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	public static int WIDTH = 800;
	public static int HEIGHT = 600;
	public static final int SCALE = 1;
	public static boolean loaded = false;
	
	private boolean running;
	private Thread thread;
	private BufferedImage img;
	
	private Game game;
	private Screen screen;
	
	public Main()
	{
		Dimension dim = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		
		setPreferredSize(dim);
		setMinimumSize(dim);
		setMaximumSize(dim);
		
		setBackground(Color.BLACK);
		
		addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e)
			{
				if(loaded)
				{
					game.mousePressed(e);
				}
			}
			public void mouseReleased(MouseEvent e)
			{
				if(loaded)
				{
					game.mouseReleased(e);
				}
			}
		});
		addMouseMotionListener(new MouseAdapter(){
			public void mouseDragged(MouseEvent e)
			{
				if(loaded)
				{
					game.mouseDragged(e);
				}
			}
		});
		addMouseWheelListener(new MouseAdapter(){
			public void mouseWheelMoved(MouseWheelEvent e)
			{
				if(loaded)
				{
					game.mouseWheelMoved(e);
				}
			}
			
		});
		
		img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		game = new GameGraphics();
		screen = new Screen();
	}
	
	public void start()
	{
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop()
	{
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public int fps = 0;
	public void run()
	{
		int cap = 120;
		
		while(running)
		{
			long before = System.currentTimeMillis();
			
			update();
			render();
			
			long after = System.currentTimeMillis() - before;
			if(after < 0)
				after = 0;
			
			long sleep = 1000 / cap - after;
			if(sleep < 0)
				sleep = 1;
			
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			after = System.currentTimeMillis() - before;
			fps = (int) (1000 / after);
		}
	}
	
	private void update()
	{
		if(WIDTH != getWidth() || HEIGHT != getHeight())
		{
			WIDTH = getWidth() / SCALE;
			HEIGHT = getHeight() / SCALE;
			
			img = new BufferedImage((WIDTH > 0) ? WIDTH : 1, (HEIGHT > 0) ? HEIGHT : 1, BufferedImage.TYPE_INT_RGB);
		}
		
		game.update();
	}
	
	private void render()
	{
		Graphics g = img.getGraphics();
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		String s = "Loading...";
		g.drawString(s, getWidth() / 2 - 5 * s.length(), getHeight() / 2 + 5);
		
		screen.draw(g, game, this);
		g.dispose();
		
		g = getGraphics();
		g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		Toolkit.getDefaultToolkit().sync();
	}
	
	public void paint(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		String s = "Loading...";
		g.drawString(s, getWidth() / 2 - 5 * s.length(), getHeight() / 2 + 5);
		
		g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Block Collision Physics Simulator");
		Main game = new Main();
		
		frame.add(game);
		frame.pack();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
	}
	
}
