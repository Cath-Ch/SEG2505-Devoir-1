import java.lang.Math;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PointCPAnalysis {

	private static final char[] TYPE = { 'P', 'C' };
	private static final int NUMBER_OF_INSTANCES = 200000;
	private static final int NUMBER_OF_CALLS = 10;
	private static final int NUMBER_OF_TESTS = 10;
	private static final int NUMBER_OF_METHODS = 8;
	private static final String[] METHODS = { "getX", "getY", "getRho", "getTheta", 
			"convertStorageToPolar", "convertStorageToCartesian", "getDistance", "rotatePoint" };

	private static PointCP[] pointCP_list = new PointCP[NUMBER_OF_INSTANCES];
	private static PointCP5[] pointCP5_list = new PointCP5[NUMBER_OF_INSTANCES];

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		int angle = 90;
		
		
		// Design 1 instantiating running time
		final long d1_instantiating_startTime = System.nanoTime();
		for (int i = 0; i < NUMBER_OF_INSTANCES; i++) {

			char type = TYPE[(int) (Math.random() * 2)];
			double xOrRho = Math.random() * (10);
			double yOrTheta = Math.random() * (10);

			pointCP_list[i] = new PointCP(type, xOrRho, yOrTheta);
		}
		final long d1_instantiating_endTime = System.nanoTime();

		
		// Design 1 methods running time
		PointCP point2 = new PointCP(TYPE[(int) (Math.random() * 2)], Math.random() * (10), Math.random() * (10));

		double d1_method_time = 0;
		double[] d1_method_time_list = new double[NUMBER_OF_METHODS];

		long startTime = 0;
		long endTime = 0;

		for (int i = 0; i < NUMBER_OF_INSTANCES; i++) {

			// Methods 1-6
			int k = 0;
			while (k < NUMBER_OF_METHODS - 2) {

				Method d1_methods[] = pointCP_list[i].getClass().getDeclaredMethods();

				startTime = System.nanoTime();
				for (Method m : d1_methods) { 

					String methodName = m.getName(); 
					if (methodName.equals(METHODS[k])) { 

						m.invoke(pointCP_list[i], null); 
					} 
				} 
				endTime = System.nanoTime();

				d1_method_time_list[k] += (double)(endTime - startTime);


				k++;
			}


			// Method 7
			Method d1_methods[] = pointCP_list[i].getClass().getDeclaredMethods();

			startTime = System.nanoTime();
			for (Method m : d1_methods) { 

				String methodName = m.getName(); 
				if (methodName.equals(METHODS[k])) { 

					m.invoke(pointCP_list[i], point2); 
				} 
			} 
			endTime = System.nanoTime();

			d1_method_time_list[k] += (double)(endTime - startTime);

			k++;


			// Method 8
			startTime = System.nanoTime();
			for (Method m : d1_methods) { 

				String methodName = m.getName(); 
				if (methodName.equals(METHODS[k])) { 

					m.invoke(pointCP_list[i], angle); 
				} 
			} 
			endTime = System.nanoTime();

			d1_method_time_list[k] += (double)(endTime - startTime);
		}
		
		// Design 5 instantiating running time
		int[] design = new int[NUMBER_OF_INSTANCES];
		for (int i = 0; i < NUMBER_OF_INSTANCES; i++) {
			design[i] = (int) (Math.random() * ((3 - 2) + 1) + 2);
		}

		final long d5_instantiating_startTime = System.nanoTime();
		for (int i = 0; i < NUMBER_OF_INSTANCES; i++) {

			char type = TYPE[(int) (Math.random() * 2)];
			double xOrRho = Math.random() * (10);
			double yOrTheta = Math.random() * (10);

			if (design[i] == 2) {
				pointCP5_list[i] = new PointCP2(xOrRho, yOrTheta);
			} else {
				pointCP5_list[i] = new PointCP3(xOrRho, yOrTheta);
			}

		}
		final long d5_instantiating_endTime = System.nanoTime();



		// Design 5 methods running time
		PointCP5 point3 = new PointCP2(Math.random() * (10), Math.random() * (10));

		double d5_method_time = 0;
		double[] d5_method_time_list = new double[NUMBER_OF_METHODS];

		startTime = 0;
		endTime = 0;


		for (int i = 0; i < NUMBER_OF_INSTANCES; i++) {

			// Methods 1-6
			int k = 0;
			while (k < NUMBER_OF_METHODS - 2) {

				Method d5_methods[] = pointCP5_list[i].getClass().getDeclaredMethods();

				startTime = System.nanoTime();
				for (Method m : d5_methods) { 

					String methodName = m.getName(); 
					if (methodName.equals(METHODS[k])) { 

						m.invoke(pointCP5_list[i], null); 
					} 
				} 
				endTime = System.nanoTime();

				d5_method_time_list[k] += (double)(endTime - startTime);

				k++;
			}


			// Method 7
			Method d5_methods[] = pointCP5_list[i].getClass().getDeclaredMethods();

			startTime = System.nanoTime();
			for (Method m : d5_methods) { 

				String methodName = m.getName(); 
				if (methodName.equals(METHODS[k])) { 

					m.invoke(pointCP5_list[i], point3); 
				} 
			} 
			endTime = System.nanoTime();

			d5_method_time_list[k] += (double)(endTime - startTime);

			k++;


			// Method 8
			startTime = System.nanoTime();
			for (Method m : d5_methods) { 

				String methodName = m.getName(); 
				if (methodName.equals(METHODS[k])) { 

					m.invoke(pointCP5_list[i], angle); 
				} 
			} 
			endTime = System.nanoTime();

			d5_method_time_list[k] += (double)(endTime - startTime);
		}

		// Print instantiating time for both designs
		System.out.println("Design 1 instantiating time: "
				+ (double) (d1_instantiating_endTime - d1_instantiating_startTime) / 1000000000.0 + " seconds");
		System.out.println("Design 5 instantiating time: "
				+ (double) (d5_instantiating_endTime - d5_instantiating_startTime) / 1000000000.0 + " seconds\n");
		
		
		// Calculate average time for each method in Design 5
		double d1_method_average = 0;
		double d1_method_average_total = 0;

		double d5_method_average = 0;
		double d5_method_average_total = 0;

		for (int i = 0; i < NUMBER_OF_METHODS; i++) {

			d1_method_average += d1_method_time_list[i] / NUMBER_OF_INSTANCES;
			System.out.println("\nDesign 1 " + METHODS[i] + " average time: " + d1_method_average + " nanoseconds");
			d1_method_average_total += d1_method_average;


			d5_method_average += d5_method_time_list[i] / NUMBER_OF_INSTANCES;
			System.out.println("Design 5 " + METHODS[i] + " average time: " + d5_method_average + " nanoseconds");
			d5_method_average_total += d5_method_average;
		}
		
		System.out.println("\n\nDesign 1 methods average time total: " + d1_method_average_total + " nanoseconds");
		System.out.println("Design 5 methods average time total: " + d5_method_average_total + " nanoseconds");
	}
}