public interface Agente {
    void Mover(Laberinto lab);
    int getMovimientoRealizado();
    boolean encontrarSalida();
    void PosFinal();
}
