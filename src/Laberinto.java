import java.util.Random;
import java.io.*;

public class Laberinto {

    private int ancho, alto;
    private char [][]laberinto; //creamos el lab con coordenadas x e y

    public Laberinto() {
        ancho = 10; //predeterminamos un tamaño de laberinto de 10x10
        alto = 10;
        this.laberinto = new char[ancho][alto];
        generar(ancho, alto);
    }

    public Laberinto(int x, int y) {
        this.ancho = x; // elegimos el tamaño del laberinto
        this.alto = y;
        this.laberinto = new char[ancho][alto];
        generar(ancho, alto);
    }

    //getters
    public char[][] getLaberinto() {
        return laberinto;
    }
    public int getAncho() {
        return ancho;
    }
    public int getAlto() {
        return alto;
    }
    // METODOS USADOS POR LOS AGENTES
    public boolean Salida(int x, int y) {
        return laberinto[x][y] == 'S';
    }// verifica si una posición que se le pasa por parámetro es la salida

    public void Visitado(int x, int y) {
        if(laberinto[x][y] != 'S' && laberinto[x][y] != 'E') {
            laberinto[x][y] = '.';
        } // marca con puntos las posiciones que han sido visitadas
    }

    public void generar(int x, int y) { // podríamos pasarle por párametros en el main el alto y ancho
        this.ancho = x;                 // del laberinto que queremos generar
        this.alto = y;
        this.laberinto = new char[alto][ancho];
        Random rand = new Random();

        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                if (i == 0 || i == alto - 1 || j == 0 || j == ancho - 1) {
                    laberinto[i][j] = '#';
                } else {
                    if (rand.nextBoolean()) { //genera un booleano con 50% de ser verdadero y 50% falso
                        laberinto[i][j] = ' '; // rellenamos el interior con espacios o #
                    } else {
                        laberinto[i][j] = '#';
                    }
                }
            }
        }
        laberinto[1][1] = 'E'; // Escribimos la entrada en el lugar correspondiente
        laberinto[alto-2][ancho-2] = 'S'; //A la salida le restamos 2 porque empezamos en 0 y le quitamos la pared
    }

    public void cargar(String archivo) {
        try {
            File file = new File(archivo);
            if (!file.exists()) { //verifica si el archivo existe y si no lanza la excepcion
                throw new FileNotFoundException("El archivo no existe: " + archivo);
            }

            try (BufferedReader br = new BufferedReader(new FileReader(file))) { //leemos el fichero
                String linea;
                int filas = 0;
                int columnas = 0;
                //contamos las filas y las columnas
                while ((linea = br.readLine()) != null) {
                    filas++;
                    if (columnas == 0) {
                        columnas = linea.length();
                    } else if (linea.length() != columnas) { //lanza una excepción cuando una fila tiene menos columnas que otra
                        throw new IOException("Las filas deben tener la misma cantidad de columnas.");
                    }
                }

                // Inicializo el laberinto
                this.alto = filas;
                this.ancho = columnas;
                this.laberinto = new char[alto][ancho];
            }

            // Vuelvo a leer el fichero
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String linea;
                int fila = 0;
                boolean entradaEncontrada = false;
                boolean salidaEncontrada = false;

                while ((linea = br.readLine()) != null) { //mientras no lleguemos al final del fichero
                    for (int columna = 0; columna < ancho; columna++) {
                        laberinto[fila][columna] = linea.charAt(columna); // coge los caracteres que hay en cada posición

                        // Mira si ha encontrado la entrada y la salida
                        if (laberinto[fila][columna] == 'E') {
                            entradaEncontrada = true;
                        }
                        if (laberinto[fila][columna] == 'S') {
                            salidaEncontrada = true;
                        }
                    }
                    fila++;
                }

                // Si el fichero no tiene entrada o salida se lanza una excepcion
                if (!entradaEncontrada || !salidaEncontrada) {
                    throw new IOException("El laberinto debe tener una entrada  salida.");
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar el laberinto: " + e.getMessage());
            // lanzamos una excepción
            throw new RuntimeException("Error en el laberinto", e);
        }
    }

    public void Pintar() { //dibujamos el laberinto
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                System.out.print(laberinto[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) { //main para probar los metodos de la clase
        Laberinto lab = new Laberinto();
        lab.cargar("maze1.txt");
        lab.Pintar();
        lab.generar(4,5);
        lab.Pintar();
    }
}
