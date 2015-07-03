package com.physics;

public class Physics {
	
	public static Vector getVect(double x, double y)
	{
		double m = Math.sqrt(((x) * (x)) + ((y) * (y)));
		double a = Math.toDegrees(Math.atan2(y, x));
		
		Vector result = new Vector(m, a);
		return result;
	}
	
	public static Vector addVect(Vector v1, Vector v2)
	{
		double x1 = v1.magnitude * Math.cos(Math.toRadians(v1.angle));
		double y1 = v1.magnitude * Math.sin(Math.toRadians(v1.angle));
		
		double x2 = v2.magnitude * Math.cos(Math.toRadians(v2.angle));
		double y2 = v2.magnitude * Math.sin(Math.toRadians(v2.angle));
		
		double resultX = x1 + x2;
		double resultY = y1 + y2;
		
		Vector result = getVect(resultX, resultY);
		return result;
	}
	
}
