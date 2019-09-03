package lab1.display;

import lab1.utils.graphics.Rotator;
import lab1.utils.matrices.MatricesUtils;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings extends JFrame implements Runnable{

    public static final double[] X_DEFAULT = {0.25d, 0.25d, -0.25d, -0.25d, 0.25d, 0.25d, -0.25d, -0.25d, -0.25d, -0.25d, -0.25d, -0.25d, 0.25d, 0.25d, 0.25d, 0.25d, -0.25d, -0.25d, 0.25d, 0.25d, -0.25d, -0.25d, 0.25d, 0.25d};
    public static final double[] Y_DEFAULT = {0.25d, -0.25d, -0.25d, 0.25d, 0.25d, -0.25d, -0.25d, 0.25d, -0.25d, -0.25d, 0.25d, 0.25d, -0.25d, -0.25d, 0.25d, 0.25d, 0.25d, 0.25d, 0.25d, 0.25d, -0.25d, -0.25d, -0.25d, -0.25d};
    public static final double[] Z_DEFAULT = {0.25d, 0.25d, 0.25d, 0.25d, -0.25d, -0.25d, -0.25d, -0.25d, 0.25d, -0.25d, -0.25d, 0.25d, 0.25d, -0.25d, -0.25d, 0.25d, -0.25d, 0.25d, 0.25d, -0.25d, -0.25d, 0.25d, 0.25d, -0.25d};

    public double[] x = X_DEFAULT.clone();
    public double[] y = Y_DEFAULT.clone();
    public double[] z = Z_DEFAULT.clone();

    public static final double[] X_AXIS_DEFAULT = {0.0d, 0.0d, 0.0d, 0.0d, 10.0d, 0.0d};
    public static final double[] Y_AXIS_DEFAULT = {0.0d, 0.0d, 10.0d, 0.0d, 0.0d, 0.0d};
    public static final double[] Z_AXIS_DEFAULT = {10.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d};


    public double[] xAxis = X_AXIS_DEFAULT.clone();
    public double[] yAxis = Y_AXIS_DEFAULT.clone();
    public double[] zAxis = Z_AXIS_DEFAULT.clone();

    public int xAngle;
    public int yAngle;
    public int zAngle;

    private JLabel xLable;
    private JLabel yLable;
    private JLabel zLable;

    private JSlider xSlider = new JSlider(-180, 180);
    private JSlider ySlider = new JSlider(-180, 180);
    private JSlider zSlider = new JSlider(-180, 180);

    private JButton commit;


    private JCheckBox xBox = new JCheckBox();
    private JCheckBox yBox = new JCheckBox();
    private JCheckBox zBox = new JCheckBox();

    public Settings(){

        super("Settings");

        xLable = new JLabel("x angle: 0");
        yLable = new JLabel("y angle: 0");
        zLable = new JLabel("z angle: 0");

        commit = new JButton("Default");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new GridLayout(4,3));

        this.add(xLable);
        this.add(xSlider);
        this.add(xBox);
        this.add(yLable);
        this.add(ySlider);
        this.add(yBox);
        this.add(zLable);
        this.add(zSlider);
        this.add(zBox);
        this.add(commit);

        xSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                xAngle = xSlider.getValue();

                if (xBox.isSelected()){
                    if (yBox.isSelected()){
                        yAngle = xAngle;
                        ySlider.setValue(xAngle);
                        yLable.setText("y angle: " + yAngle);
                    } else {
                        yAngle = ySlider.getValue();
                    }
                    if (zBox.isSelected()){
                        zAngle = xAngle;
                        zSlider.setValue(xAngle);
                        zLable.setText("z angle: " + zAngle);
                    } else {
                        zAngle = zSlider.getValue();
                    }
                } else {
                    yAngle = ySlider.getValue();
                    zAngle = zSlider.getValue();
                }

                xLable.setText("x angle: " + xAngle);

                double[][] coords = MatricesUtils.vectorsToMatrix(X_DEFAULT, Y_DEFAULT, Z_DEFAULT);
                double[][] axisCoords = MatricesUtils.vectorsToMatrix(X_AXIS_DEFAULT, Y_AXIS_DEFAULT, Z_AXIS_DEFAULT);

                coords = MatricesUtils.multiply(coords, Rotator.makeXTransitionMatrix(xAngle));
                coords = MatricesUtils.multiply(coords, Rotator.makeYTransitionMatrix(yAngle));
                coords = MatricesUtils.multiply(coords, Rotator.makeZTransitionMatrix(zAngle));

                axisCoords = MatricesUtils.multiply(axisCoords, Rotator.makeXTransitionMatrix(xAngle));
                axisCoords = MatricesUtils.multiply(axisCoords, Rotator.makeYTransitionMatrix(yAngle));
                axisCoords = MatricesUtils.multiply(axisCoords, Rotator.makeZTransitionMatrix(zAngle));

                for (int i = 0; i < coords.length; i++){
                    x[i] = coords[i][0];
                    y[i] = coords[i][1];
                    z[i] = coords[i][2];
                }

                for (int i = 0; i < axisCoords.length; i++){
                    xAxis[i] = axisCoords[i][0];
                    yAxis[i] = axisCoords[i][1];
                    zAxis[i] = axisCoords[i][2];
                }
            }
        });

        ySlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                yAngle = ySlider.getValue();

                if (yBox.isSelected()){
                    if (xBox.isSelected()){
                        xAngle = yAngle;
                        xSlider.setValue(yAngle);
                        xLable.setText("x angle: " + xAngle);
                    } else {
                        xAngle = xSlider.getValue();
                    }
                    if (zBox.isSelected()){
                        zAngle = yAngle;
                        zSlider.setValue(yAngle);
                        zLable.setText("z angle: " + zAngle);
                    } else {
                        zAngle = zSlider.getValue();
                    }
                } else {
                    xAngle = xSlider.getValue();
                    zAngle = zSlider.getValue();
                }

                yLable.setText("y angle: " + yAngle);

                double[][] coords = MatricesUtils.vectorsToMatrix(X_DEFAULT, Y_DEFAULT, Z_DEFAULT);
                double[][] axisCoords = MatricesUtils.vectorsToMatrix(X_AXIS_DEFAULT, Y_AXIS_DEFAULT, Z_AXIS_DEFAULT);

                coords = MatricesUtils.multiply(coords, Rotator.makeXTransitionMatrix(xAngle));
                coords = MatricesUtils.multiply(coords, Rotator.makeYTransitionMatrix(yAngle));
                coords = MatricesUtils.multiply(coords, Rotator.makeZTransitionMatrix(zAngle));

                axisCoords = MatricesUtils.multiply(axisCoords, Rotator.makeXTransitionMatrix(xAngle));
                axisCoords = MatricesUtils.multiply(axisCoords, Rotator.makeYTransitionMatrix(yAngle));
                axisCoords = MatricesUtils.multiply(axisCoords, Rotator.makeZTransitionMatrix(zAngle));

                for (int i = 0; i < coords.length; i++){
                    x[i] = coords[i][0];
                    y[i] = coords[i][1];
                    z[i] = coords[i][2];
                }

                for (int i = 0; i < axisCoords.length; i++){
                    xAxis[i] = axisCoords[i][0];
                    yAxis[i] = axisCoords[i][1];
                    zAxis[i] = axisCoords[i][2];
                }
            }
        });

        zSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                zAngle = zSlider.getValue();

                if (zBox.isSelected()){
                    if (yBox.isSelected()){
                        yAngle = zAngle;
                        ySlider.setValue(zAngle);
                        yLable.setText("y angle: " + yAngle);
                    } else {
                        yAngle = ySlider.getValue();
                    }
                    if (xBox.isSelected()){
                        xAngle = zAngle;
                        xSlider.setValue(zAngle);
                        xLable.setText("x angle: " + xAngle);
                    } else {
                        xAngle = xSlider.getValue();
                    }
                } else {
                    yAngle = ySlider.getValue();
                    xAngle = xSlider.getValue();
                }

                zLable.setText("z angle: " + zAngle);

                double[][] coords = MatricesUtils.vectorsToMatrix(X_DEFAULT, Y_DEFAULT, Z_DEFAULT);
                double[][] axisCoords = MatricesUtils.vectorsToMatrix(X_AXIS_DEFAULT, Y_AXIS_DEFAULT, Z_AXIS_DEFAULT);

                coords = MatricesUtils.multiply(coords, Rotator.makeXTransitionMatrix(xAngle));
                coords = MatricesUtils.multiply(coords, Rotator.makeYTransitionMatrix(yAngle));
                coords = MatricesUtils.multiply(coords, Rotator.makeZTransitionMatrix(zAngle));

                axisCoords = MatricesUtils.multiply(axisCoords, Rotator.makeXTransitionMatrix(xAngle));
                axisCoords = MatricesUtils.multiply(axisCoords, Rotator.makeYTransitionMatrix(yAngle));
                axisCoords = MatricesUtils.multiply(axisCoords, Rotator.makeZTransitionMatrix(zAngle));

                for (int i = 0; i < coords.length; i++){
                    x[i] = coords[i][0];
                    y[i] = coords[i][1];
                    z[i] = coords[i][2];
                }

                for (int i = 0; i < axisCoords.length; i++){
                    xAxis[i] = axisCoords[i][0];
                    yAxis[i] = axisCoords[i][1];
                    zAxis[i] = axisCoords[i][2];
                }
            }
        });

        commit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                x = X_DEFAULT.clone();
                y = Y_DEFAULT.clone();
                z = Z_DEFAULT.clone();

                xAxis = X_AXIS_DEFAULT.clone();
                yAxis = Y_AXIS_DEFAULT.clone();
                zAxis = Z_AXIS_DEFAULT.clone();

                xAngle = 0;
                yAngle = 0;
                zAngle = 0;

                xSlider.setValue(0);
                ySlider.setValue(0);
                zSlider.setValue(0);

                xLable.setText("x angle: 0");
                yLable.setText("y angle: 0");
                zLable.setText("z angle: 0");
            }
        });

        this.pack();
        this.setVisible(true);
    }

    @Override
    public void run() {
        while(true){

        }
    }

}

