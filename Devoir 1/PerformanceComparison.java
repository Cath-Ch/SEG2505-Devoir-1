import java.util.ArrayList;
import java.util.LinkedList;

public class PerformanceComparison {
	
	private static final int X = 100000000;
	
	private static char[] array = new char[X];
	private static LinkedList<Character> linkedlist = new LinkedList<Character>();
	private static ArrayList<Character> arraylist = new ArrayList<Character>();
	
	private static final char[] ALPHABETS = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 
											 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	
	private static long startTime;
	private static long endTime;
	
	public static void main(String args[]) {
		
		// Adding to ArrayList
		startTime = System.currentTimeMillis();
		for (int i = 0; i < X; i++) {
			arraylist.add(Character.valueOf(ALPHABETS[(int)(Math.random() * 26)]));
		}
		endTime = System.currentTimeMillis();
		long arraylist_add_time = endTime - startTime;
		System.out.println("ArrayList fill time (10M elements): " + arraylist_add_time + " milliseconds");
		
		
		// Adding to LinkedList
		startTime = System.currentTimeMillis();
		for (int i = 0; i < X; i++) {
			linkedlist.add(Character.valueOf(ALPHABETS[(int)(Math.random() * 26)]));
		}
		endTime = System.currentTimeMillis();
		long linkedlist_add_time = endTime - startTime;
		System.out.println("LinkedList fill time (10M elements): " + linkedlist_add_time + " milliseconds");
		
		
		// Adding to array
		startTime = System.currentTimeMillis();
		for (int i = 0; i < X; i++) {
			array[i] = ALPHABETS[(int)(Math.random() * 26)];
		}
		endTime = System.currentTimeMillis();
		long array_add_time = endTime - startTime;
		System.out.println("Array fill time (10M elements): " + array_add_time + " milliseconds");
	}
}