// This file contains material supporting section 2.9 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

/**
 * This interface contains abstract methods for Cartesian/polar
 * coordinates to be implemented in subclasses.
 * 
 * @author Catherine Chen
 */
abstract interface PointCP5 {
	public abstract double getX();
	public abstract double getY();
	public abstract double getRho();
	public abstract double getTheta();
	public PointCP2 convertStorageToPolar();
	public PointCP3	convertStorageToCartesian();
	public double getDistance(PointCP5 pointB);
	public PointCP5 rotatePoint(double rotation);
}