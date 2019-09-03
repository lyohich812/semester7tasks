package lab1.utils.matrices;

public class MatricesUtils {

    public static boolean canMultiply(double[][] a, double[][] b){
        if (b.length == a[0].length){
            return true;
        } else {
            return false;
        }
    }

    public static double[][] multiply(double[][] a, double[][] b){

        double[][] result = new double[a.length][b[0].length];

        for(int i = 0; i < a.length; i++){
            for (int j = 0; j < b[0].length; j++){

                double sum = 0;

                for(int k = 0; k < a[0].length; k++){
                    sum += a[i][k] * b[k][j];
                }

                result[i][j] = sum;

            }
        }

        return result;
    }

    public static double[][] vectorsToMatrix(double[] x, double[] y, double[] z){
        double[][] result = new double[x.length][4];

        for (int i = 0; i < x.length; i++){
            result[i][0] = x[i];
            result[i][1] = y[i];
            result[i][2] = z[i];
            result[i][3] = 1.0d;
        }

        return result.clone();
    }


    // public static double[] mergeCoords(double[] x, double[] y){
    //     double[] result = new double[x.length+y.length];
    //     for (int i = 0; i < x.length; i += 2){
    //         result[i] = x[i];
    //         result[i+1] = y[i];
    //     }
    //     return result;
    // }

    public static void main(String[] args) {
        double[][] a = {{1,2},{2,1},{1,2}};
        double[][] b = {{1,1},{0,1}};

        if (canMultiply(a,b)){
            System.out.println(multiply(a,b));
        } else {
            System.out.println("boop!");
        }

        System.out.println("bye");
    }


}
