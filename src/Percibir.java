public class Percibir {
    private char[][] laberinto;
    private int fila;
    private int columna;

    public Percibir(char[][] laberinto, int x, int y) {
        this.laberinto = laberinto;
        this.fila = x;
        this.columna = y;
    }

    public int[] movimiento() {
        int[] movimiento = new int[5];
        if (this.fila > 0 && this.laberinto[this.fila - 1][this.columna] != '#') {
            if (this.laberinto[this.fila - 1][this.columna] == 'S') {
                movimiento[0] = 2;
            } else {
                movimiento[0] = 1;
            }
        } else {
            movimiento[0] = 0;
        }

        if (this.fila < this.laberinto.length - 1 && this.laberinto[this.fila + 1][this.columna] != '#') {
            if (this.laberinto[this.fila + 1][this.columna] == 'S') {
                movimiento[1] = 2;
            } else {
                movimiento[1] = 1;
            }
        } else {
            movimiento[1] = 0;
        }

        if (this.columna > 0 && this.laberinto[this.fila][this.columna - 1] != '#') {
            if (this.laberinto[this.fila][this.columna - 1] == 'S') {
                movimiento[2] = 2;
            } else {
                movimiento[2] = 1;
            }
        } else {
            movimiento[2] = 0;
        }

        if (this.columna < this.laberinto[0].length - 1 && this.laberinto[this.fila][this.columna + 1] != '#') {
            if (this.laberinto[this.fila][this.columna + 1] == 'S') {
                movimiento[3] = 2;
            } else {
                movimiento[3] = 1;
            }
        } else {
            movimiento[3] = 0;
        }

        if (movimiento[0] == 0 && movimiento[1] == 0 && movimiento[2] == 0 && movimiento[3] == 0) {
            movimiento[4] = 1;
        } else {
            movimiento[4] = 0;
        }

        return movimiento;
    }
}



