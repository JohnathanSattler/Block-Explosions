package com.Art;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tiles {
	
	public static BufferedImage block = loadSprite("tiles.png", 0, 0, 8, 8);;
	
	private static BufferedImage explosion = loadImage("explosions.png");
	public static BufferedImage[] explosions =
	{
		explosion.getSubimage(0, 0, 64, 64),
		explosion.getSubimage(64, 0, 64, 64),
		explosion.getSubimage(128, 0, 64, 64),
		explosion.getSubimage(192, 0, 64, 64),
		
		explosion.getSubimage(0, 64, 64, 64),
		explosion.getSubimage(64, 64, 64, 64),
		explosion.getSubimage(128, 64, 64, 64),
		explosion.getSubimage(192, 64, 64, 64),
		
		explosion.getSubimage(0, 128, 64, 64),
		explosion.getSubimage(64, 128, 64, 64),
		explosion.getSubimage(128, 128, 64, 64),
		explosion.getSubimage(192, 128, 64, 64),
		
		explosion.getSubimage(0, 192, 64, 64),
		explosion.getSubimage(64, 192, 64, 64),
		explosion.getSubimage(128, 192, 64, 64),
		explosion.getSubimage(192, 192, 64, 64),
	};
	
	public static void load()
	{
		block = loadSprite("tiles.png", 0, 0, 8, 8);;
		
		explosion = loadImage("explosions.png");
		explosions = new BufferedImage[]
		{
			explosion.getSubimage(0, 0, 64, 64),
			explosion.getSubimage(64, 0, 64, 64),
			explosion.getSubimage(128, 0, 64, 64),
			explosion.getSubimage(192, 0, 64, 64),
			
			explosion.getSubimage(0, 64, 64, 64),
			explosion.getSubimage(64, 64, 64, 64),
			explosion.getSubimage(128, 64, 64, 64),
			explosion.getSubimage(192, 64, 64, 64),
			
			explosion.getSubimage(0, 128, 64, 64),
			explosion.getSubimage(64, 128, 64, 64),
			explosion.getSubimage(128, 128, 64, 64),
			explosion.getSubimage(192, 128, 64, 64),
			
			explosion.getSubimage(0, 192, 64, 64),
			explosion.getSubimage(64, 192, 64, 64),
			explosion.getSubimage(128, 192, 64, 64),
			explosion.getSubimage(192, 192, 64, 64)
		};
	}
	
	public static BufferedImage loadSprite(String file, int x, int y, int w, int h)
	{
		BufferedImage img = loadImage(file).getSubimage(x, y, w, h);
		return img;
	}
	
	public static BufferedImage loadImage(String file)
	{
		try {
			BufferedImage img = ImageIO.read(Tiles.class.getResource("/com/Art/" + file));
			return img;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
