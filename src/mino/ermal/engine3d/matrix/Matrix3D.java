package mino.ermal.engine3d.matrix;

import mino.ermal.engine3d.primitives.Vertex;

/**
 * User: mino.ermal(AT)gmail.com
 * Date: 10-dic-2006
 * Time: 13.31.37
 *
 * This is a Matrix3D class, which represents a 4-th order matrix.
 * This will be used as base class for other more specific transformation
 * matrices, like rotation matrix for example.
 * 
 */
public class Matrix3D {

    // for performance issues (being a key feature in 3D graphics)
    // fields are made pulbic for direct and faster access
    private double e11;
	private double e12;
	private double e13;
	private double e14;
	private double e21;
	private double e22;
	private double e23;
	private double e24;
	private double e31;
	private double e32;
	private double e33;
	private double e34;
	private double e41;
	private double e42;
	private double e43;
	private double e44;

    /**
     * Initializes an 0 filled matrix
     */
    public Matrix3D(){
		e11=0.0;
		e12=0.0;
		e13=0.0;
		e14=0.0;
		e21=0.0;
		e22=0.0;
		e23=0.0;
		e24=0.0;
		e31=0.0;
		e32=0.0;
		e33=0.0;
		e34=0.0;
		e41=0.0;
		e42=0.0;
		e43=0.0;
		e44=0.0;
	}

    /**
     * Initializes a matrix with the given parameter values
     * @param e11 row 1 column 1 value
     * @param e12 row 1 column 2 value
     * @param e13 row 1 column 3 value
     * @param e14 row 1 column 4 value
     * @param e21 row 2 column 1 value
     * @param e22 row 2 column 2 value
     * @param e23 row 2 column 3 value
     * @param e24 row 2 column 4 value
     * @param e31 row 3 column 1 value
     * @param e32 row 3 column 2 value
     * @param e33 row 3 column 3 value
     * @param e34 row 3 column 4 value
     * @param e41 row 4 column 1 value
     * @param e42 row 4 column 2 value
     * @param e43 row 4 column 3 value
     * @param e44 row 4 column 4 value
     */
    public Matrix3D(double e11,double e12,double e13,double e14,
	                double e21,double e22,double e23,double e24,
	                double e31,double e32,double e33,double e34,
	                double e41,double e42,double e43,double e44){
		this.e11 = e11;
		this.e12 = e12;
		this.e13 = e13;
		this.e14 = e14;
		this.e21 = e21;
		this.e22 = e22;
		this.e23 = e23;
		this.e24 = e24;
		this.e31 = e31;
		this.e32 = e32;
		this.e33 = e33;
		this.e34 = e34;
		this.e41 = e41;
		this.e42 = e42;
		this.e43 = e43;
		this.e44 = e44;
	}

    /**
     * Creates a copy of the matrix given as parameter
     * @param parentMatrix matrix to be copied
     */
    public Matrix3D(Matrix3D parentMatrix){
		this.e11 = parentMatrix.e11;
		this.e12 = parentMatrix.e12;
		this.e13 = parentMatrix.e13;
		this.e14 = parentMatrix.e14;
		this.e21 = parentMatrix.e21;
		this.e22 = parentMatrix.e22;
		this.e23 = parentMatrix.e23;
		this.e24 = parentMatrix.e24;
		this.e31 = parentMatrix.e31;
		this.e32 = parentMatrix.e32;
		this.e33 = parentMatrix.e33;
		this.e34 = parentMatrix.e34;
		this.e41 = parentMatrix.e41;
		this.e42 = parentMatrix.e42;
		this.e43 = parentMatrix.e43;
		this.e44 = parentMatrix.e44;
	}

    /**
     * Multiplies itself with another matrix
     * @param matrix the matrix to be multiplied with
     */
    public void multiply(Matrix3D matrix){
        //creates a copy of itself to use for the calulations
        Matrix3D a=new Matrix3D(this);

        /*
         matrix multiplication is made directly and not using for cycle which
         would take less code but become slower
         */
        this.e11=a.e11*matrix.e11 + a.e12*matrix.e21 + a.e13*matrix.e31 + a.e14*matrix.e41;
		this.e21=a.e21*matrix.e11 + a.e22*matrix.e21 + a.e23*matrix.e31 + a.e24*matrix.e41;
		this.e31=a.e31*matrix.e11 + a.e32*matrix.e21 + a.e33*matrix.e31 + a.e34*matrix.e41;
		this.e41=a.e41*matrix.e11 + a.e42*matrix.e21 + a.e43*matrix.e31 + a.e44*matrix.e41;

		this.e12=a.e11*matrix.e12 + a.e12*matrix.e22 + a.e13*matrix.e32 + a.e14*matrix.e42;
		this.e22=a.e21*matrix.e12 + a.e22*matrix.e22 + a.e23*matrix.e32 + a.e24*matrix.e42;
		this.e32=a.e31*matrix.e12 + a.e32*matrix.e22 + a.e33*matrix.e32 + a.e34*matrix.e42;
		this.e42=a.e41*matrix.e12 + a.e42*matrix.e22 + a.e43*matrix.e32 + a.e44*matrix.e42;

		this.e13=a.e11*matrix.e13 + a.e12*matrix.e23 + a.e13*matrix.e33 + a.e14*matrix.e43;
		this.e23=a.e21*matrix.e13 + a.e22*matrix.e23 + a.e23*matrix.e33 + a.e24*matrix.e43;
		this.e33=a.e31*matrix.e13 + a.e32*matrix.e23 + a.e33*matrix.e33 + a.e34*matrix.e43;
		this.e43=a.e41*matrix.e13 + a.e42*matrix.e23 + a.e43*matrix.e33 + a.e44*matrix.e43;

		this.e14=a.e11*matrix.e14 + a.e12*matrix.e24 + a.e13*matrix.e34 + a.e14*matrix.e44;
		this.e24=a.e21*matrix.e14 + a.e22*matrix.e24 + a.e23*matrix.e34 + a.e24*matrix.e44;
		this.e34=a.e31*matrix.e14 + a.e32*matrix.e24 + a.e33*matrix.e34 + a.e34*matrix.e44;
		this.e44=a.e41*matrix.e14 + a.e42*matrix.e24 + a.e43*matrix.e34 + a.e44*matrix.e44;
	}

    /**
     * Multiplies itself with a vertex, the most used operation
     * applied to all object vertexes during transformations
     * @param vertex the vertex to be transformed
     */
    public void multiply(Vertex vertex){
        //creates a copy of the old values necessary for the calculations
        double x=vertex.x;
        double y=vertex.y;
        double z=vertex.z;
        double w=vertex.w;

        //calculates the values updating directly the vertex given as parameter
        //and not by creating a new object, for memory and performance issues
        vertex.x=this.e11*x + this.e12*y + this.e13*z + this.e14*w;
		vertex.y=this.e21*x + this.e22*y + this.e23*z + this.e24*w;
		vertex.z=this.e31*x + this.e32*y + this.e33*z + this.e34*w;
		vertex.w=this.e41*x + this.e42*y + this.e43*z + this.e44*w;
	}

}
