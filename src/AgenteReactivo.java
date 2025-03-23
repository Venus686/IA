import java.util.Random;
public class AgenteReactivo implements Agente {
    private int movRealizado;
    private boolean salida;
    int xFin, yFin;
    int x,y;

    public AgenteReactivo() {
        this.salida = false;
        this.movRealizado = 0;
        this.x = 1;
        this.y = 1;
    }

    public void Mover(Laberinto lab) {
        if (salida || movRealizado >= 1000) {
            return;
        }
        Percibir p= new Percibir(lab.getLaberinto(),x,y);
        int[] mov= p.movimiento();

        if(mov[4]==1) {
            System.out.println("No hay movimientos.");
            salida = true;
            return;
        }
        boolean mover=false;
        for (int i = 0; i < 4; i++) {
            if (mov[i] == 2) {
                switch (i) {
                    case 0:
                        x--;
                        break;
                    case 1:
                        x++;
                        break;
                    case 2:
                        y--;
                        break;
                    case 3:
                        y++;
                        break;
                }
                salida = true;
                movRealizado++;
                lab.Visitado(x, y);
                mover = true;
                break;
            }
        }

        Random rand = new Random();
        while(!mover) {
            int direccion = rand.nextInt(4);
            if (mov[direccion] == 1) {
                switch (direccion) {
                    case 0:
                        x--;
                        break;
                    case 1:
                        x++;
                        break;
                    case 2:
                        y--;
                        break;
                    case 3:
                        y++;
                        break;
                }
                movRealizado++;
                lab.Visitado(x, y);
                mover = true;
            }
        }
        xFin=x;
        yFin=y;
    }

    @Override
    public void ResolverLaberinto(Laberinto lab) {
        while (!salida && movRealizado < 1000) {
            Mover(lab);
        }
    }
    @Override
    public int getMovimientoRealizado() {
        return movRealizado;
    } // devuelve las acciones que ha realizado

    @Override
    public boolean encontrarSalida() {
        return salida;
    }
    @Override
    public void PosFinal() {
        System.out.println("(" + xFin + ", " + yFin + ")"); // Posicion en la que ha finalizado la ejecución del programa
    }
}

