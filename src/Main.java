import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Seleccione un laberinto:");
        System.out.println("1. maze1.txt");
        System.out.println("2. maze2.txt");
        System.out.println("3. maze3.txt");
        System.out.println("4. Generar un laberinto");
        System.out.println("5. Salir");
        System.out.print("Ingrese opción: ");
        int opcion = sc.nextInt();

        Laberinto lab = new Laberinto();
        String fichero = "";

        if (opcion == 1) {
            fichero = "maze1.txt";
        } else if (opcion == 2) {
            fichero = "maze2.txt";
        } else if (opcion == 3) {
            fichero = "maze3.txt";
        } else if (opcion == 4) {
            System.out.print("Ingrese el número de filas: ");
            int filas = sc.nextInt();
            System.out.print("Ingrese el número de columnas: ");
            int columnas = sc.nextInt();
            lab.generar(filas, columnas);
            System.out.println("\nLaberinto Generado:");
            lab.Pintar();
        } else if (opcion == 5) {
            System.out.println("Saliendo...");
            sc.close();
            return;
        } else {
            System.out.println("Opción no válida.");
            sc.close();
            return;
        }


        if (opcion != 4) {
            lab.cargar(fichero);
            System.out.println("\nLaberinto Generado:");
            lab.Pintar();
        }


        System.out.println("\nSeleccione el número de agentes:");
        System.out.println("1. Un agente");
        System.out.println("2. Dos agentes");
        System.out.print("Escribe una opción: ");
        int numAgentes = sc.nextInt();

        if (numAgentes == 1) {

            System.out.println("\nSeleccione el tipo de agente:");
            System.out.println("1. Agente Reactivo");
            System.out.println("2. Agente Informado");
            System.out.print("Escribe una opción: ");
            int tipoAgente = sc.nextInt();

            if (tipoAgente == 1) {

                System.out.println("\nAgente Reactivo: ");
                AgenteReactivo ag = new AgenteReactivo();
                ag.ResolverLaberinto(lab);

                lab.Pintar();
                System.out.println("\nPosición Final:");
                ag.PosFinal();

                int salidaFila = lab.getAlto() - 2;
                int salidaColumna = lab.getAncho() - 2;
                System.out.println("Salida en: (" + salidaFila + ", " + salidaColumna + ")");

                if (ag.encontrarSalida()) {
                    System.out.println("Solución encontrada.");
                } else {
                    System.out.println("No hay solución o se ha llegado al límite de iteraciones.");
                }

                System.out.println("Acciones: " + ag.getMovimientoRealizado());
            } else if (tipoAgente == 2) {
                // Ejecutar Agente Informado
                System.out.println("\nAgente Informado: ");
                AgenteInformado ai = new AgenteInformado();
                ai.ResolverLaberinto(lab);

                // Mostrar resultados
                lab.Pintar();
                System.out.println("\nPosición Final:");
                ai.PosFinal();

                int salidaFila = lab.getAlto() - 2;
                int salidaColumna = lab.getAncho() - 2;
                System.out.println("Salida en: (" + salidaFila + ", " + salidaColumna + ")");

                if (ai.encontrarSalida()) {
                    System.out.println("Solución encontrada.");
                } else {
                    System.out.println("No hay solución o se ha llegado al límite de iteraciones.");
                }

                System.out.println("Acciones: " + ai.getMovimientoRealizado());
            } else {
                System.out.println("Opción no válida. Debes elegir 1 (Reactivo) o 2 (Informado).");
            }
        } else if (numAgentes == 2) {

            System.out.println("\n2 Agentes Reactivos: ");
            AgenteReactivo ag1 = new AgenteReactivo();
            AgenteReactivo ag2 = new AgenteReactivo();
            boolean encontrado = false;

            while (!encontrado && (ag1.getMovimientoRealizado() < 1000 || ag2.getMovimientoRealizado() < 1000)) {
                if (!ag1.encontrarSalida() && ag1.getMovimientoRealizado() < 1000) {
                    ag1.Mover(lab);
                    if (ag1.encontrarSalida()) {
                        encontrado = true;
                        System.out.println("El primer agente ha resuelto el laberinto.");
                    }
                }
                if (!encontrado && !ag2.encontrarSalida() && ag2.getMovimientoRealizado() < 1000) {
                    ag2.Mover(lab);
                    if (ag2.encontrarSalida()) {
                        encontrado = true;
                        System.out.println("El segundo agente ha resuelto el laberinto.");
                    }
                }
            }

            if (!encontrado) {
                System.out.println("Ningún agente ha resuelto el laberinto.");
            }

            lab.Pintar();
            System.out.println("\nPosición final de cada agente:");
            System.out.print("Agente 1: ");
            ag1.PosFinal();
            System.out.print("Agente 2: ");
            ag2.PosFinal();
        } else {
            System.out.println("Opción no válida. Debes elegir 1 (Un agente) o 2 (Dos agentes).");
        }
        sc.close();
    }
}