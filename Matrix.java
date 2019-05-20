public class Matrix{

    static final int MATRIX_SIZE = 3;

    public static void main(String[] args) {

        int[][] primeraMatriz = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] segundaMatriz = {{9,8,7},{6,5,4},{3,2,1}};
        int[][] matrizFinal = new int[3][3];

        for (int index = 0; index < 3; index++) {
            new MakeOperation(primeraMatriz, segundaMatriz, index, MATRIX_SIZE, matrizFinal).run();
        }
        System.out.println("Matriz final: ");

    }
}

interface PrintMethods{
    default void print(Object arg){
        System.out.print(arg);
    }
    default void printnl(Object arg){
        System.out.print(arg);
    }
}

class MakeOperation extends Thread implements PrintMethods{

    int[][] primeraMatriz;
    int[][] segundaMatriz;
    int[][] matrizFinal;
    int filaAOperar;
    int tamanioMatriz;
    public MakeOperation(int[][] primeraMatriz,int[][] segundaMatriz,int filaAOperar,int tamanioMatriz, int[][] matrizFinal){
        this.primeraMatriz = primeraMatriz;
        this.segundaMatriz = segundaMatriz;
        this.matrizFinal = matrizFinal;
        this.filaAOperar = filaAOperar;
        this.tamanioMatriz = tamanioMatriz;
    }

    @Override
    public void run() {
        super.run();
        //codigo para la multiplicaion
        for(int indexColumna = 0; indexColumna < tamanioMatriz; indexColumna++){
            matrizFinal[filaAOperar][indexColumna] = 0;
            for(int indexPos = 0; indexPos < tamanioMatriz; indexPos++){
                matrizFinal[filaAOperar][indexColumna] += primeraMatriz[indexColumna][indexPos] * segundaMatriz[indexColumna][indexPos];
            }
        }
        //
        stop();
    }

    public static void printMatrix(int[][] matrix) {
        for (int indexRow = 0; indexRow < Matrix.MATRIX_SIZE; indexRow++) {
            print("[");
            for (int indexColumna = 0; indexColumna < Matrix.MATRIX_SIZE; indexColumna++) {
                print(matrix[indexRow][indexColumna] + ",");
            }
            println("]");
        }
    }
}
