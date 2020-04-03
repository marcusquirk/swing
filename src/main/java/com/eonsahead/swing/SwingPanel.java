package com.eonsahead.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SwingPanel extends JPanel implements ActionListener {

    private double centerX = 0.0;
    private double centerY = 0.0;
    private double radius = 0.2;
    private final double[] direction = new double[2];
    private Color colour = Color.red;
    private String shapeDraw = "Circle";
    Random rng = new Random();

    public SwingPanel() {
        Timer timer = new Timer(30, this);
        timer.start();
        direction[0] = 0.005 + (rng.nextDouble() / 40);
        direction[1] = 0.005 + (rng.nextDouble() / 40);
    } // SwingPanel()

    public double getCenterX() {
        return this.centerX;
    } // getCenterX()

    public void setCenterX(double x) {
        this.centerX = x;
    } // setCenterX( double )

    public double getCenterY() {
        return this.centerY;
    } // getCenterY()

    public void setCenterY(double y) {
        this.centerY = y;
    } // setCenterY( double )

    public double getRadius() {
        return this.radius;
    } // getRadius()

    public void setRadius(double r) {
        this.radius = r;
    } // setRadius( double )

    public Color getColour() {
        return this.colour;
    } // getColour()

    public void setColour(Color c) {
        this.colour = c;
    } // setColour( Color )
    
    public void setShape(String s){
        this.shapeDraw = s;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

        int w = this.getWidth();
        int h = this.getHeight();

        AffineTransform transform = new AffineTransform();
        AffineTransform scaling = new AffineTransform();
        scaling.setToScale(w / 2, h / 2);
        AffineTransform translation = new AffineTransform();
        translation.setToTranslation(1.0 - radius / 2, 1.0 - radius / 2);

        transform.concatenate(scaling);
        transform.concatenate(translation);

        if (shapeDraw == "Circle") {
            double d = this.radius;
            double ulx = this.centerX;
            double uly = this.centerY;
            Ellipse2D.Double circle = new Ellipse2D.Double(ulx, uly, d, d * 2);

            Shape shape = transform.createTransformedShape(circle);
            g2D.setColor(colour);
            g2D.fill(shape);
        }//if
        else if (shapeDraw == "Square"){
            Rectangle2D.Double square = new Rectangle2D.Double(centerX, centerY,
                    radius, radius*2);
            Shape shape = transform.createTransformedShape(square);
            g2D.setColor(colour);
            g2D.fill(shape);
            
        }
    } // paintComponent( Graphics )

    @Override
    public void actionPerformed(ActionEvent event) {

        this.centerY = this.centerY + (direction[0]);
        this.centerX = this.centerX + (direction[1]);
        if (Math.abs(this.centerY + radius) > 1) {
            direction[0] = -direction[0];
        } // if
        if (Math.abs(this.centerX) > 1) {
            direction[1] = -direction[1];
        }
        this.repaint();
    } // actionPerformed( ActionEvent )

    public static void main(String[] args) {
        System.out.println("Colour");
    }

} // SwingPanel
