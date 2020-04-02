package com.eonsahead.swing;


public class Vector {

    private final double u1;
    private final double u2;
    private final double u3;
    private final double u4;

    public Vector(double u1, double u2, double u3, double u4) {
        this.u1 = u1;
        this.u2 = u2;
        this.u3 = u3;
        this.u4 = u4;
    } // Vector( double, double, double, double )


    public Vector add(Vector v) {
        return new Vector(this.getU1()+v.getU1(), this.getU2()+v.getU2(),
                this.getU3()+v.getU3(), this.getU4()+v.getU4());
    } // add( Vector2D )

    
    public Vector scale(double factor1, double factor2, double factor3,
            double factor4) {
        return new Vector(factor1 * this.u1, factor2 * this.u2, 
        factor3 * this.u3, factor4 * this.u4);
    } // scale( double )

    
    public Vector scale(double factor) {
        return new Vector(factor * this.u1, factor * this.u2, 
        factor * this.u3, factor * this.u4);
    } // scale( double )

    
    public Vector rotateX(double angle) {
        Matrix4X4 rotationMatrix = new Matrix4X4();
        rotationMatrix.rotationX(angle);
        return rotationMatrix.multiplyVector(this);
    } // rotate( double )

    
    
    public double dot(Vector v) {
        double u1Dot = this.getU1() * v.getU1();
        double u2Dot = this.getU2() * v.getU2();
        double u3Dot = this.getU3() * v.getU3();
        double u4Dot = this.getU4() * v.getU4();
        return u1Dot + u2Dot + u3Dot + u4Dot;
    } // dotproduct ( Vector2D )

   
    public double magnitude() {
        double u1Sq = this.getU1() * this.getU1();
        double u2Sq = this.getU2() * this.getU2();
        double u3Sq = this.getU3() * this.getU3();
        double u4Sq = this.getU4() * this.getU4();
        return Math.pow(u1Sq + u2Sq +u3Sq + u4Sq, 0.25);
    } // magnitude ( Vector2D )

   
    public double getU1() {
        return u1;
    } // getX()

    
    public double getU2() {
        return u2;
    } // getY()
    
    public double getU3(){
        return u3;
    }
    
    public double getU4(){
        return u4;
    }


    
    @Override
    public String toString() {
        return "(" + getU1() + ", " + getU2() + ", " + getU3() + ", " + getU4()
                + ")";
    } // toString()



} // Vector
