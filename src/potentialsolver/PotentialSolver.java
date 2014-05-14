package potentialsolver;

import java.math.*;

public class PotentialSolver {

	private static final double Inf = 100000000.0;
	
	/**
	 * Class to solve Laplace equation in polar coordinates
	 */

	private int nRadial = 500; // Number of radial grids
	private int nTheta = 500; // Number of angular grids
	private double rMax = 10.0; // maximum radius of the computational domain
	private double rCyl = 0.5; // radius of the cylinder
	private double U0 = 1.0;// Initial velocity
	private double PI = Math.PI; // constant

	private double iterNorm = 1.0d - 6; // Iteration stopping criterion
	private int maxIter = 10000; // Max number of iterations

	public double getRadialGridSpacing() {

		double dR = 0.0;
		dR = (rMax - rCyl) / (nRadial);
		return dR;

	}

	public double getThetaGridSpacing() {

		double dT = 0.0;
		dT = (2 * PI) / (nTheta);
		return dT;

	}

	public double[][] setBC() {

		/**
		 * Dirichlet Boundary Condition
		 */

		double[][] psi = new double[nRadial][nTheta];

		/**
		 * Initialize the array
		 */

		for (int i = 0; i < nRadial - 1; i++) {
			for (int j = 0; j < nTheta - 1; j++) {
				psi[i][j] = 0.0;
			}
		}

		double dT = getThetaGridSpacing();

		for (int j = 0; j < nTheta - 1; j++) {
			psi[nRadial - 1][j] = U0 * rMax * Math.sin(j * dT);
		}

		return psi;

	}

	public void doIteration() {

		double iterNorm = this.iterNorm; // receive the tolerance
		int maxIter = this.maxIter; // receive the max number of iterations
		double[][] psi = this.setBC(); // receive the psi array with BC's set

		double[][] dpsi = new double[nRadial][nTheta];

		/**
		 * Initialize the conditions for while loop
		 */

		double norm = Inf;
		int iter = 0;

		while (norm < iterNorm && iter < maxIter) {
			iter = iter + 1;
			norm = 0.0;

			/**
			 * Loop over all the grids
			 */

			for (int i = 1; i < nRadial - 2; i++) {
				for (int j = 1; j < nTheta - 2; j++) {
//	dpsi[i][j]=
				}
			}

		}

	}
	
	public static void main(String[] args) {
		
		
		
		System.out.println("Main method.");
		
		
		
		
	}
	
	
}
