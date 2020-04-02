package com.eonsahead.swing;

/**
 * A class to model a 4X4 vector.
 *
 * For the most part, I rewrote this class from the ground-up, so as to "learn
 * through my fingers" once again. I did this primarily for key methods such as
 * multiply. I am adding the rotation around the axes also.
 *
 * @author marcus
 */
public final class Matrix4X4 {

    private final double[][] elements;

    public Matrix4X4() {
        this.elements = new double[4][4];
        this.identity();
    }

    public final void identity() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == j) {
                    this.set(i, j, 1.0);
                }//if
                else {
                    this.set(i, j, 0.0);
                }//else
            }//for j
        }//for i
    }//identity

    public void set(int row, int column, double value) {
        this.elements[row][column] = value;
    }

    public double get(int row, int column) {
        return this.elements[row][column];
    }

    private String rowToString(int row) {
        StringBuilder result = new StringBuilder();
        result.append("( ");
        for (int i = 0; i < 3; i++) {
            result.append(this.get(row, i));
            result.append(",");
        } // for
        result.append(this.get(row, 3));
        result.append(" )");
        return result.toString();
    } // rowToString( int )

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[ ");
        for (int i = 0; i < 4; i++) {
            result.append(rowToString(i));
        } //
        result.append(" ]");
        return result.toString();
    } // toString()

    public Matrix4X4 multiply(Matrix4X4 otherMatrix) {
        Matrix4X4 product = new Matrix4X4();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                double sum = 0.0;
                for (int k = 0; k < 4; k++) {
                    sum += this.get(i, k) * otherMatrix.get(k, j);
                }
                product.set(i, j, sum);
            }
        }
        return product;
    }

    public Vector multiplyVector (Vector vector) {
        double[] vectorValues = new double[4];
        for (int i = 0; i < 4; i++) {
            double sum = 0.0;
            for (int k =0; k<4; k++) {
                sum += this.get(i, k) * vector.getU1();
                sum += this.get(i, k) * vector.getU2();
                sum += this.get(i, k) * vector.getU3();
                sum += this.get(i, k) * vector.getU4();
            } // for k
            vectorValues[i] = sum;
        } // for i
        return new Vector(vectorValues[0], vectorValues[1], vectorValues[2],
                vectorValues[3]);
    } // multiply( Vector )


    public static void main(String[] args) {
        Matrix4X4 identity = new Matrix4X4();
        System.out.println("identity = " + identity);

        Matrix4X4 product = identity.multiply(identity);
        System.out.println("product = " + product);

        Matrix4X4 ccw = new Matrix4X4();
        ccw.rotationZ(Math.PI / 4);
        System.out.println("counter-clockwise rotation = " + ccw);

        Matrix4X4 cw = new Matrix4X4();
        cw.rotationZ(-Math.PI / 4);
        System.out.println("clockwise rotation = " + cw);

        Matrix4X4 netRotation = ccw.multiply(cw);
        System.out.println("net rotation = " + netRotation);
    } // main( String [] )

    public void rotationZ(double angle) {
        this.identity();
        this.set(0, 0, Math.cos(angle));
        this.set(0, 1, -Math.sin(angle));
        this.set(1, 0, Math.sin(angle));
        this.set(1, 1, Math.cos(angle));
    } // rotationZ( double )
    
    public void rotationX(double angle){
        this.identity();
        this.set(1, 1, Math.cos(angle));
        this.set(1, 2, -Math.sin(angle));
        this.set(2, 1, Math.sin(angle));
        this.set(2, 2, Math.cos(angle));
    }
    
    
    public void rotationY(double angle){
        this.identity();
        this.set(0, 0, Math.cos(angle));
        this.set(0, 2, Math.sin(angle));
        this.set(2, 0, -Math.sin(angle));
        this.set(2, 2, Math.cos(angle));
    }
    
    public void scale(double factorX, double factorY, double factorZ){
        this.identity();
        this.set(0, 0, factorX);
        this.set(1, 1, factorY);
        this.set(2, 2, factorZ);
    }


}//class
