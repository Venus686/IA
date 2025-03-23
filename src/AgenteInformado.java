
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class AgenteInformado implements Agente {
    private int movRealizado;
    private boolean salida;
    private Random random;
    private int x, y;

    public AgenteInformado() {
        this.movRealizado = 0;
        this.salida = false;
        this.random = new Random();
    }

    @Override
    public void ResolverLaberinto(Laberinto lab) {
        x = 1;
        y = 1;
        lab.Visitado(x, y);

        while (!salida && movRealizado < 1000) {
            Percibir percepcion = new Percibir(lab.getLaberinto(), x, y);
            int[] movimientos = percepcion.movimiento();
            char[][] laberinto = lab.getLaberinto();

            List<Integer> dirNoVisitada = new ArrayList<>();
            List<Integer> dirPosible = new ArrayList<>();

            for (int dir = 0; dir < 4; dir++) {
                if (movimientos[dir] == 2) {  // Priorizar la salida si está cerca
                    mover(lab, dir);
                    salida = true;
                    return;
                } else if (movimientos[dir] == 1) {  // Movimiento posible
                    int fila = x, columna = y;
                    switch (dir) {
                        case 0: fila--; break;  // Arriba
                        case 1: fila++; break;  // Abajo
                        case 2: columna--; break;  // Izquierda
                        case 3: columna++; break;  // Derecha
                    }
                    if (laberinto[fila][columna] == ' ') {  // No visitada
                        dirNoVisitada.add(dir);
                    }
                    dirPosible.add(dir);
                }
            }

            if (!dirNoVisitada.isEmpty()) {  // Si hay no visitadas, elige una aleatoria
                mover(lab, dirNoVisitada.get(random.nextInt(dirNoVisitada.size())));
            } else if (!dirPosible.isEmpty()) {  // Si todas han sido visitadas, mueve aleatoriamente
                mover(lab, dirPosible.get(random.nextInt(dirPosible.size())));
            }
        }
    }

    private void mover(Laberinto lab, int direccion) {
        switch (direccion) {
            case 0: x--; break;
            case 1: x++; break;
            case 2: y--; break;
            case 3: y++; break;
        }
        lab.Visitado(x, y);
        movRealizado++;
    }

    @Override
    public int getMovimientoRealizado() {
        return movRealizado;
    }

    @Override
    public boolean encontrarSalida() {
        return salida;
    }

    @Override
    public void PosFinal() {
        System.out.println("(" + x + ", " + y + ")");
    }
}




