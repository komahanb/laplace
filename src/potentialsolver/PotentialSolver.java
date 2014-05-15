package potentialsolver;

public class PotentialSolver {

	/**
	 * Class to solve Laplace equation in polar coordinates
	 */

	private static final double Inf = 100000000.0;
	private double rMax = 10.0; // maximum radius of the computational domain
	private double rCyl = 0.5; // radius of the cylinder
	private double PI = Math.PI; // constant
	private double maxNorm = 0.0001; // Iteration stopping criterion
	private int maxIter = 1000000; // Max number of iterations

	private int nRadial = getnRadial(); // Number of radial grids
	private int nTheta = getnTheta(); // Number of angular grids
	private double U0 = getU0(); // Initial velocity

	public int getnRadial() {
		return nRadial;
	}

	public int getnTheta() {
		return nTheta;
	}

	public double getU0() {
		return U0;
	}

	public void setnRadial(int nRadial) {
		this.nRadial = nRadial;
	}

	public void setnTheta(int nTheta) {
		this.nTheta = nTheta;
	}

	public void setU0(double u0) {
		U0 = u0;
	}

	public double getRadialGridSpacing() {

		double dR = 0.0;
		dR = (rMax - rCyl) / (nRadial - 1);
		return dR;

	}

	public double getAngularGridSpacing() {

		double dT = 0.0;
		dT = (2 * PI) / (nTheta - 1);
		return dT;

	}

	public double[] getRadialGrid() {

		double dR = getRadialGridSpacing();
		double[] R = new double[this.nRadial];
		for (int i = 0; i < this.nRadial; i++) {
			R[i] = rCyl + i * dR;
		}
		return R;
	}

	public double[] getAngularGrid() {
		double dT = getAngularGridSpacing();
		double[] Theta = new double[this.nTheta];
		for (int i = 0; i < this.nTheta; i++) {
			Theta[i] = i * dT;
		}
		return Theta;
	}

	public double[][] setBC() {

		/**
		 * Dirichlet Boundary Condition (Far-field) psi=U0*rMax*sin(theta) and 0
		 * else where.
		 */

		double[][] psi = new double[this.nRadial][this.nTheta];

		/**
		 * Initialize the array
		 */

		for (int i = 0; i <= nRadial - 1; i++) {
			for (int j = 0; j <= nTheta - 1; j++) {
				psi[i][j] = 0.0;
			}
		}

		double dT = getAngularGridSpacing();

		for (int j = 0; j <= nTheta - 1; j++) {
			psi[nRadial - 1][j] = U0 * rMax * Math.sin(j * dT);
		}

		return psi;

	}

	public double[][] doIteration() {

		double[][] psi = this.setBC(); // receive the psi array with BC's set
		psi = setBC(); // PSI has boundary conditions set

		double[][] dpsi = new double[nRadial][nTheta]; // Initialize with zeroes

		/**
		 * Initialize the conditions for while loop
		 */

		double dR = getRadialGridSpacing(); // radial grid spacing
		double dT = getAngularGridSpacing(); // angular grid spacing

		double[] R = getRadialGrid();
		double[] T = getAngularGrid();

		double den = 0.0; // temporary variable to store the denominator

		double norm = Inf; // convergence tolerance
		int iter = 0; // max number of iterations

		while (norm >= maxNorm & iter <= maxIter) {

			// (1) Find DPSI

			for (int j = 1; j <= nRadial - 2; j++) {

				den = (2.0 / (dR * dR)) + (2.0 / (R[j] * R[j] * dT * dT));

				for (int k = 1; k <= nTheta - 2; k++) {

					dpsi[j][k] = ((psi[j + 1][k] - psi[j - 1][k]) / (R[j] * dR)
							+ (psi[j + 1][k] + psi[j - 1][k]) / Math.pow(dR, 2) + (psi[j][k + 1] + psi[j][k - 1])
							/ Math.pow(R[j] * dT, 2))
							/ den - psi[j][k];

				}

			}

			// (2) Update PSI [PSI=PSI+DPSI]

			for (int j = 1; j <= nRadial - 1; j++) {
				for (int k = 1; k <= nTheta - 1; k++) {
					psi[j][k] = psi[j][k] + 1.0 * dpsi[j][k]; // ? Why SOR not
																// working?
				}
			}

			// (3) Calculate NORM

			norm = 0.0;
			for (int j = 1; j <= nRadial - 2; j++) {
				for (int k = 1; k <= nTheta - 2; k++) {
					norm = norm + dpsi[j][k] * dpsi[j][k];
				}
			}
			norm = Math.sqrt(norm);

			iter = iter + 1; // increase the iteration by one

			System.out.println("Iteration :" + iter + " Norm : " + norm);

		}
		return psi;
	}

	public void Solver(int nTheta, int nRadial, double U0) {

		this.setnRadial(nRadial);
		this.setnTheta(nTheta);
		this.setU0(U0);
		this.doIteration();

	}
}