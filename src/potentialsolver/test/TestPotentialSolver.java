package potentialsolver.test;

import java.util.*;

import potentialsolver.PotentialSolver;

public class TestPotentialSolver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		PotentialSolver p = new PotentialSolver();
		/**
		 * System.out.println(p.getAngularGridSpacing());
		 * System.out.println(p.getRadialGridSpacing());
		 * System.out.println(p.getAngularGrid().length);
		 * System.out.println(p.getRadialGrid().length);
		 * System.out.println(p.maxNorm);
		 */

		// double[][] temp = p.doIteration(); // setBC();

		// new double[p.getRadialGrid().length];

		// temp = p.getRadialGrid();

		// p.getAngularGrid();
		// p.getRadialGrid();
		// p.getAngularGridSpacing();
		// p.getRadialGridSpacing();

		//p.doIteration();
		
		p.Solver(100, 100, 1.0);

		// p.setBC();

		// p.doIteration();

	}
}
