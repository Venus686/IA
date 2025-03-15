public class Main {
    public static void main(String[] args) {
        Laberinto lab = new Laberinto();
        lab.cargar("maze2.txt"); // Cargar el laberinto desde un archivo
        System.out.println("Laberinto Generado: ");
        lab.Pintar();

        // Crear y ejecutar el Agente Reactivo
        System.out.println("Agente Reactivo: ");
        AgenteReactivo ag = new AgenteReactivo();
        ag.Mover(lab);

        // Mostrar la posición final del agente
        System.out.println("Posición Final: ");
        ag.PosFinal();

        // Calcular y mostrar la posición de la salida
        int salidaFila = lab.getAlto() - 2;
        int salidaColumna = lab.getAncho() - 2;
        System.out.println("Salida en: (" + salidaFila + ", " + salidaColumna + ")");

        // Verificar si se encontró la salida
        if (ag.encontrarSalida()) {
            System.out.println("Solución encontrada");
        } else {
            System.out.println("No hay solución o se ha llegado al número límite de iteraciones.");
        }

        // Mostrar el número de acciones realizadas
        int acciones = ag.getMovimientoRealizado();
        System.out.println("Acciones: " + acciones);

        // Mostrar el laberinto recorrido
        System.out.println("Laberinto recorrido: ");
        lab.Pintar();
    }
}