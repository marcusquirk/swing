package com.eonsahead.swing;

public class Vector {

    private final double u0;
    private final double u1;
    private final double u2;
    private final double u3;

    public Vector(double u0, double u1, double u2) {
        this.u0 = u0;
        this.u1 = u1;
        this.u2 = u2;
        this.u3 = 1.0;
    } // Vector( double, double, double )

    public Vector(double u0, double u1, double u2, double u3) {
        this.u0 = u0;
        this.u1 = u1;
        this.u2 = u2;
        this.u3 = u3;
    } // Vector( double, double, double, double )

    public Vector() {
        this.u0 = 0.0;
        this.u1 = 0.0;
        this.u2 = 0.0;
        this.u3 = 0.0;
    } // Vector()

    public Vector add(Vector v) {
        return new Vector(this.get(0) + v.get(0), this.get(1) + v.get(1),
                this.get(2) + v.get(2), this.get(3) + v.get(3));
    } // add( Vector2D )

    public Vector scale(double factor1, double factor2, double factor3,
            double factor4) {
        return new Vector(factor1 * this.u0, factor2 * this.u1,
                factor3 * this.u2, factor4 * this.u3);
    } // scale( double )

    public Vector scale(double factor) {
        return new Vector(factor * this.u0, factor * this.u1,
                factor * this.u2, factor * this.u3);
    } // scale( double )

    public Vector rotateX(double angle) {
        Matrix4X4 rotationMatrix = new Matrix4X4();
        rotationMatrix.rotationX(angle);
        return rotationMatrix.multiply(this);
    } // rotate( double )

    public double dot(Vector v) {
        double u0Dot = this.get(0) * v.get(0);
        double u1Dot = this.get(1) * v.get(1);
        double u2Dot = this.get(2) * v.get(2);
        double u3Dot = this.get(3) * v.get(3);
        return u0Dot + u1Dot + u2Dot + u3Dot;
    } // dotproduct ( Vector2D )

    public double magnitude() {
        double u0Sq = this.get(0) * this.get(0);
        double u1Sq = this.get(1) * this.get(1);
        double u2Sq = this.get(2) * this.get(2);
        double u3Sq = this.get(3) * this.get(3);
        return Math.sqrt(u0Sq + u1Sq + u2Sq + u3Sq);
    } // magnitude ( Vector2D )

    public double get(int component) {
        switch (component) {
            case 0:
                return this.u0;
            case 1:
                return this.u1;
            case 2:
                return this.u2;
            case 3:
                return this.u3;
            default:
                return this.u0;
        } //switch
    } // get()

    public Vector normalise() {
        double magnitude = this.magnitude();
        return new Vector(this.get(0) / magnitude, this.get(1) / magnitude,
                this.get(2) / magnitude, this.get(3) / magnitude);
    }
    
    

    @Override
    public String toString() {
        return "(" + get(0) + ", " + get(1) + ", " + get(2) + ", " + get(3)
                + ")";
    } // toString()
    
    public static void main(String[] args){
        Vector v1 = new Vector(7,8,2,0);
        Matrix4X4 m1 = new Matrix4X4();
        m1.set(1,3,7);
        m1.set(3,1,6);
        m1.set(2,3,-3);
        Vector v2 = v1.normalise();
        System.out.println(v2);
        System.out.println(v2.magnitude());
        System.out.println(m1);
        System.out.println(v1);
        System.out.println(m1.multiply(v1));
    }

} // Vector
