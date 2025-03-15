public class Percibir {
    private char [][]laberinto;
    private int fila;
    private int columna;

    public Percibir(char[][]laberinto,int x, int y) {
        this.laberinto = laberinto;
        this.fila = x;
        this.columna = y;
    }
    public int[]movimiento(){
        int[]movimiento=new int[5];
        if (fila > 0 && laberinto[fila-1][columna] != '#') {//ARRIBA
            movimiento[0] = 1;
        } else {
            movimiento[0] = 0;
        }
        if (fila < laberinto.length - 1 && laberinto[fila+1][columna] != '#') { //ABAJO
            movimiento[1] = 1;
        } else {
            movimiento[1] = 0;
        }

        if (columna > 0 && laberinto[fila][columna-1] != '#') {//IZQUIERDA
            movimiento[2] = 1;
        } else {
            movimiento[2] = 0;
        }

        if (columna<laberinto[0].length-1 && laberinto[fila][columna+1] != '#') {//DERECHA
            movimiento[3] = 1;
        } else {
            movimiento[3] = 0;
        }

        if (movimiento[0]==0 && movimiento[1]==0 && movimiento[2]==0 && movimiento[3] == 0) {
            movimiento[4] = 1; //N0 HAY MOVIMIENTO
        } else {
            movimiento[4] = 0;
        }

        return movimiento;
    }

}
