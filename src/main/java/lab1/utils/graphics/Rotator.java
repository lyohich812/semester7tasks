package lab1.utils.graphics;

import lab1.utils.matrices.MatricesUtils;

public class Rotator {

    public static double[][] rotate(double[][] coords, double[][] trans){
        if(MatricesUtils.canMultiply(coords, trans)){
            return MatricesUtils.multiply(coords, trans);
        }
        return coords;
    }

    public static double[][] makeZTransitionMatrix(int angle){

        double[][] matrix = emptyMatrix();

        double k = Math.PI / 180;

        matrix[0][0] = Math.cos(angle * k);
        matrix[1][1] = Math.cos(angle * k);

        matrix[0][1] = Math.sin(angle * k);
        matrix[1][0] = - Math.sin(angle * k);

        return matrix;
    }

    public static double[][] makeYTransitionMatrix(int angle){

        double[][] matrix = emptyMatrix();

        double k = Math.PI / 180;

        matrix[1][1] = Math.cos(angle * k);
        matrix[2][2] = Math.cos(angle * k);

        matrix[1][2] = Math.sin(angle * k);
        matrix[2][1] = - Math.sin(angle * k);

        return matrix;

    }

    public static double[][] makeXTransitionMatrix(int angle){
        double[][] matrix = emptyMatrix();

        double k = Math.PI / 180;

        matrix[0][0] = Math.cos(angle * k);
        matrix[2][2] = Math.cos(angle * k);

        matrix[0][2] = - Math.sin(angle * k);
        matrix[2][0] = Math.sin(angle * k);

        return matrix;
    }

    private static double[][] emptyMatrix() {

        double[][] matrix = new double[4][4];

        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                if (i==j){
                    matrix[i][j] = 1.0d;
                } else {
                    matrix[i][j] = 0.0d;
                }
            }
        }

        return matrix;
    }

}
