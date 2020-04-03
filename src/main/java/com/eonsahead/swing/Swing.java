package com.eonsahead.swing;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Swing extends JFrame implements ActionListener {

    private final int FRAME_WIDTH = 800;
    private final int FRAME_HEIGHT = 400;
    private final String FRAME_TITLE = "Swing";
    private final int NUMBER_OF_COLOURS = 12;
    private final List<Color> palette = new ArrayList<>();
    private final List<Color> foregroundPalette = new ArrayList<>();
    private final SwingPanel panel;

    public Swing() {
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setTitle(FRAME_TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container pane = this.getContentPane();
        this.panel = new SwingPanel();
        pane.add(panel);

        Random rng = new Random();
        for (int i = 0; i < NUMBER_OF_COLOURS; i++) {
            int red = 64 + rng.nextInt(128);
            int green = 64 + rng.nextInt(128);
            int blue = 64 + rng.nextInt(128);
            Color colour = new Color(red, green, blue);
            palette.add(colour);
        } // for
        this.panel.setBackground(palette.get(0));

        for (int i = 0; i < NUMBER_OF_COLOURS; i++) {
            int red = rng.nextInt(256);
            int green = rng.nextInt(256);
            int blue = rng.nextInt(256);
            Color colour = new Color(red, green, blue);
            foregroundPalette.add(colour);
        } // for
        this.panel.setColour(foregroundPalette.get(0));

        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        JMenu colourMenu = new JMenu("Colour");
        menuBar.add(colourMenu);

        for (int i = 0; i < NUMBER_OF_COLOURS; i++) {
            String label = "Colour " + i;
            JMenuItem item = new JMenuItem(label);
            item.addActionListener(this);
            item.setActionCommand(label);
            colourMenu.add(item);
        } // for

        JMenu foregroundColour = new JMenu("Foreground");
        menuBar.add(foregroundColour);
        for (int i = 0; i < NUMBER_OF_COLOURS; i++) {
            String label = "Foreground " + i;
            JMenuItem item = new JMenuItem(label);
            item.addActionListener(this);
            item.setActionCommand(label);
            foregroundColour.add(item);
        } // for

        String[] shapes = {"Circle", "Square"};
        JMenu shape = new JMenu("Shape");
        menuBar.add(shape);
        for (String i : shapes) {
            JMenuItem item = new JMenuItem(i);
            item.addActionListener(this);
            item.setActionCommand(i);
            shape.add(item);
        }

        this.setVisible(true);
    } // Swing()

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.indexOf("Colour") >= 0) {
            String suffix = cmd.substring(6).trim();
            int index = Integer.parseInt(suffix);
            this.panel.setBackground(palette.get(index));
        } // if
        else if (cmd.indexOf("Foreground") >= 0) {
            String suffix = cmd.substring(10).trim();
            int index = Integer.parseInt(suffix);
            this.panel.setColour(foregroundPalette.get(index));
        } // if
        else if (cmd == "Circle") {
            this.panel.setShape(cmd);
        } // if
        else if (cmd == "Square") {
            this.panel.setShape(cmd);
        } //if

    } // actionPerformed( ActionEvent )

    public static void main(String[] args) {
        Swing swing = new Swing();
    } // main( String [] )

} // Swing
