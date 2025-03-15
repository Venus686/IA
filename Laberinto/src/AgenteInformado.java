import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AgenteInformado implements Agente {
    private int movimientosRealizados; // Contador de movimientos
    private boolean salidaEncontrada;  // Indica si el agente encontró la salida
    private boolean[][] visitado;      // Matriz para rastrear casillas visitadas
    private Random random;
    int xFin, yFin;

    public AgenteInformado() {
        this.movimientosRealizados = 0;
        this.salidaEncontrada = false;
        this.random = new Random();
    }

    @Override
    public void Mover(Laberinto lab) {
        int fila = 1; // Posición inicial (entrada)
        int columna = 1;
        visitado = new boolean[lab.getAlto()][lab.getAncho()];
        visitado[fila][columna] = true; // Marcamos la posición inicial como visitada

        while (!salidaEncontrada && movimientosRealizados < 1000) {
            // Obtenemos las percepciones del agente (movimientos posibles)
            Percibir percepcion = new Percibir(lab.getLaberinto(), fila, columna);
            int[] movimientos = percepcion.movimiento();

            // Creamos una lista de direcciones no visitadas
            List<Integer> direccionesNoVisitadas = new ArrayList<>();
            for (int dir = 0; dir < 4; dir++) {
                if (movimientos[dir] == 1) { // Si la dirección es válida
                    int nuevaFila = fila;
                    int nuevaColumna = columna;

                    // Calculamos la nueva posición según la dirección
                    switch (dir) {
                        case 0: nuevaFila--; break; // Arriba
                        case 1: nuevaFila++; break; // Abajo
                        case 2: nuevaColumna--; break; // Izquierda
                        case 3: nuevaColumna++; break; // Derecha
                    }

                    // Si la nueva posición no ha sido visitada, la añadimos a la lista
                    if (!visitado[nuevaFila][nuevaColumna]) {
                        direccionesNoVisitadas.add(dir);
                    }
                }
            }

            // Si hay direcciones no visitadas, elegimos una aleatoria
            if (!direccionesNoVisitadas.isEmpty()) {
                int direccion = direccionesNoVisitadas.get(random.nextInt(direccionesNoVisitadas.size()));

                // Movemos al agente en la dirección elegida
                switch (direccion) {
                    case 0: fila--; break; // Arriba
                    case 1: fila++; break; // Abajo
                    case 2: columna--; break; // Izquierda
                    case 3: columna++; break; // Derecha
                }

                // Marcamos la nueva posición como visitada
                visitado[fila][columna] = true;
                movimientosRealizados++;
                lab.Visitado(fila, columna);

                // Verificamos si la nueva posición es la salida
                if (lab.Salida(fila, columna)) {
                    salidaEncontrada = true;
                }
            }
            // Si no hay direcciones no visitadas, elegimos una dirección válida aleatoria
            else {
                List<Integer> direccionesValidas = new ArrayList<>();
                for (int dir = 0; dir < 4; dir++) {
                    if (movimientos[dir] == 1) { // Si la dirección es válida
                        direccionesValidas.add(dir);
                    }
                }

                // Si hay direcciones válidas, elegimos una aleatoria
                if (!direccionesValidas.isEmpty()) {
                    int direccion = direccionesValidas.get(random.nextInt(direccionesValidas.size()));

                    // Movemos al agente en la dirección elegida
                    switch (direccion) {
                        case 0: fila--; break; // Arriba
                        case 1: fila++; break; // Abajo
                        case 2: columna--; break; // Izquierda
                        case 3: columna++; break; // Derecha
                    }

                    // Marcamos la nueva posición como visitada
                    visitado[fila][columna] = true;
                    movimientosRealizados++;
                    lab.Visitado(fila, columna);

                    // Verificamos si la nueva posición es la salida
                    if (lab.Salida(fila, columna)) {
                        salidaEncontrada = true;
                    }
                }
            }
        }
        xFin=fila;
        xFin=columna;

    }

    @Override
    public int getMovimientoRealizado() {
        return movimientosRealizados;
    }

    @Override
    public boolean encontrarSalida() {
        return salidaEncontrada;
    }
    @Override
    public void PosFinal() {
        System.out.println("(" + xFin + ", " + yFin+ ")");
    }
}
