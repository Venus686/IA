import java.util.Random;
public class AgenteReactivo implements Agente {
    private int movRealizado;
    private boolean salida;
    int xFin, yFin;

    public AgenteReactivo() {
        this.salida = false;
        this.movRealizado = 0;
    }
    @Override
    public void Mover(Laberinto lab) {
        int x=1;
        int y=1;
        Random rand = new Random();

        while(!salida && movRealizado<1000) {
            Percibir p= new Percibir(lab.getLaberinto(),x,y);
            int[] mov= p.movimiento();
            if(mov[4]==1){
                System.out.println("No hay movimientos.\n");
                break;
            }
            boolean mover=false;
            while(!mover) {
                int direccion = rand.nextInt(4);
                if(mov[direccion]==1){
                    switch(direccion) {
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
                    lab.Visitado(x,y);
                    if(lab.Salida(x,y)) {
                        salida = true;
                    }
                    mover=true;
                }
            }
        }
        xFin=x;
        yFin=y;
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
        System.out.println("(" + xFin + ", " + yFin + ")");
    }
}
