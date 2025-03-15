import java.util.Random;
import java.io.*;

public class Laberinto {

    private int ancho, alto;
    private char [][]laberinto;

    public Laberinto() {
        ancho = 10;
        alto = 10;
        this.laberinto = new char[ancho][alto];
        generar(ancho, alto);
    }

    public Laberinto(int x, int y) {
        this.ancho = x;
        this.alto = y;
        this.laberinto = new char[ancho][alto];
        generar(ancho, alto);
    }
    public char[][] getLaberinto() {
        return laberinto;
    }
    public int getAncho() {
        return ancho;
    }
    public int getAlto() {
        return alto;
    }
    public boolean Salida(int x, int y) {
        return laberinto[x][y] == 'S';
    }
    public void Visitado(int x, int y) {
        if(laberinto[x][y] != 'S' && laberinto[x][y] != 'E') {
            laberinto[x][y] = '.';
        }
    }

    public void generar(int x, int y) {
        this.ancho = x;
        this.alto = y;
        this.laberinto = new char[alto][ancho]; // Cambiar la dimensión
        Random rand = new Random();

        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                if (i == 0 || i == alto - 1 || j == 0 || j == ancho - 1) {
                    laberinto[i][j] = '#';
                } else {
                    if (rand.nextBoolean()) { //genera un booleano con 50% de ser verdadero y 50% falso
                        laberinto[i][j] = ' ';
                    } else {
                        laberinto[i][j] = '#';
                    }
                }
            }
        }
        laberinto[1][1] = 'E';
        laberinto[alto - 2][ancho - 2] = 'S';
    }

    public void cargar(String archivo) {
        try {
            File file = new File(archivo);
            if (!file.exists()) {
                throw new FileNotFoundException("El archivo no existe: " + archivo);
            }

            // Primera lectura: contar filas y columnas
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String linea;
                int filas = 0;
                int columnas = 0;

                while ((linea = br.readLine()) != null) {
                    filas++;
                    if (columnas == 0) {
                        columnas = linea.length(); // Establecer el ancho basado en la primera línea
                    } else if (linea.length() != columnas) {
                        throw new IOException("Todas las filas deben tener la misma cantidad de columnas.");
                    }
                }

                // Inicializar la matriz laberinto
                this.alto = filas;
                this.ancho = columnas;
                this.laberinto = new char[alto][ancho];
            }

            // Segunda lectura: llenar la matriz
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String linea;
                int fila = 0;
                boolean entradaEncontrada = false;
                boolean salidaEncontrada = false;

                while ((linea = br.readLine()) != null) {
                    for (int columna = 0; columna < ancho; columna++) {
                        laberinto[fila][columna] = linea.charAt(columna);

                        // Verificar si es la entrada o la salida
                        if (laberinto[fila][columna] == 'E') {
                            entradaEncontrada = true;
                        }
                        if (laberinto[fila][columna] == 'S') {
                            salidaEncontrada = true;
                        }
                    }
                    fila++;
                }

                // Verificar si se encontró la entrada y la salida
                if (!entradaEncontrada || !salidaEncontrada) {
                    throw new IOException("El laberinto debe tener una entrada 'E' y una salida 'S'.");
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar el laberinto: " + e.getMessage());
            // O lanzar una excepción personalizada
            throw new RuntimeException("Error en el laberinto", e);
        }
    }

    public void Pintar() {
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                System.out.print(laberinto[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Laberinto lab = new Laberinto();
        lab.cargar("maze1.txt");
        lab.Pintar();
    }
}