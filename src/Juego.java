import java.util.ArrayList;
import java.util.Scanner;

public class Juego {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Mazo mazo = new Mazo();

        // Crear el mazo y barajarlo
        agregarCartasAlMazo(mazo);
        mazo.barajar();

        // Elegir jugadores
        ArrayList<Jugador> jugadores = asignarJugadores(scanner);

        // Validar que hay suficientes cartas para repartir
        int numCartas = obtenerNumeroCartas(scanner);
        if (numCartas * jugadores.size() > mazo.cartasDisponibles()) {
            System.out.println("No hay suficientes cartas para repartir.");
            return; // Salir si no hay suficientes cartas
        }

        // Repartir cartas
        mazo.repartirCartas(jugadores, numCartas);

        // Menú de interacción
        menuInteraccion(mazo, jugadores, scanner);
    }

    //  Metodo para agregar cartas al mazo usando el enum Palo
    private static void agregarCartasAlMazo(Mazo mazo) {
        for (Palo palo : Palo.values()) {
            for (int valor = 1; valor <= 13; valor++) {
                mazo.agregarCarta(new Carta(palo, valor));
            }
        }
        System.out.println("Se agregaron las cartas al mazo.");
    }

    // Metodo para asignar jugadores
    private static ArrayList<Jugador> asignarJugadores(Scanner scanner) {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        int numJugadores;

        // Asegurarnos de que haya al menos 1 jugador y no más que el número de cartas
        while (true) {
            System.out.print("¿Cuántos jugadores habrá? (mínimo 1): ");
            numJugadores = scanner.nextInt();
            scanner.nextLine();  // Limpiar buffer

            if (numJugadores >= 1) {
                break;
            } else {
                System.out.println("Debe haber al menos 1 jugador.");
            }
        }

        for (int i = 1; i <= numJugadores; i++) {
            System.out.print("Nombre del jugador " + i + ": ");
            String nombre = scanner.nextLine();
            jugadores.add(new Jugador(nombre));
        }

        return jugadores;
    }

    // Metodo para obtener el número de cartas que se repartirán
    private static int obtenerNumeroCartas(Scanner scanner) {
        int numCartas;
        while (true) {
            System.out.print("¿Cuántas cartas quieres repartir a cada jugador?: ");
            numCartas = scanner.nextInt();
            scanner.nextLine();  // Limpiar buffer

            if (numCartas >= 1) {
                break;
            } else {
                System.out.println("El número de cartas debe ser al menos 1.");
            }
        }
        return numCartas;
    }

    // Menú para la interacción del juego
    private static void menuInteraccion(Mazo mazo, ArrayList<Jugador> jugadores, Scanner scanner) {
        while (true) {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Barajar mazo");
            System.out.println("2. Tomar carta del mazo");
            System.out.println("3. Mostrar cartas de un jugador");
            System.out.println("4. Mostrar cartas en el mazo");
            System.out.println("5. Volver el mazo a su estado original");
            System.out.println("6. Regresar una carta al mazo");
            System.out.println("7. Salir");
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> {
                    mazo.barajar();
                    System.out.println("El mazo ha sido barajado.");
                }
                case 2 -> {
                    System.out.print("¿Qué jugador tomará la carta?: ");
                    String nombre = scanner.nextLine();
                    Jugador jugador = jugadores.stream()
                            .filter(j -> j.getNombre().equalsIgnoreCase(nombre))
                            .findFirst()
                            .orElse(null);

                    if (jugador != null) {
                        Carta carta = mazo.tomarCarta();
                        if (carta != null) {
                            jugador.addCarta(carta); // Agrega la carta al jugador
                            System.out.println(nombre + " tomó la carta: " + carta);
                        } else {
                            System.out.println("No hay más cartas en el mazo.");
                        }
                    } else {
                        System.out.println("Jugador no encontrado.");
                    }
                }
                case 3 -> {
                    System.out.print("Nombre del jugador: ");
                    String nombre = scanner.nextLine();
                    Jugador jugador = jugadores.stream()
                            .filter(j -> j.getNombre().equalsIgnoreCase(nombre))
                            .findFirst()
                            .orElse(null);

                    if (jugador != null) {
                        System.out.println("\nCartas de " + jugador.getNombre() + ":");
                        for (Carta carta : jugador.getCartas()) {
                            System.out.println("  - " + carta);
                        }
                    } else {
                        System.out.println("Jugador no encontrado.");
                    }
                }
                case 4 -> {
                    System.out.println("\nCartas en el mazo:");
                    int totalCartas = mazo.cartasDisponibles();
                    if (totalCartas == 0) {
                        System.out.println("El mazo está vacío.");
                    } else {
                        for (Carta carta : mazo.getCartas()) {
                            System.out.println("  - " + carta);
                        }
                    }
                }
                case 5 -> {
                    mazo.regresarMazoOriginal(jugadores);
                }
                case 6 -> {
                    System.out.print("¿Qué carta quieres regresar al mazo (Ejemplo: 1 Corazones)? ");
                    String cartaInput = scanner.nextLine();
                    String[] partes = cartaInput.split(" ");
                    int valor = Integer.parseInt(partes[0]);
                    Palo palo = Palo.valueOf(partes[1].toUpperCase());

                    Carta carta = new Carta(palo, valor);

                    // Variable para saber si la carta fue regresada
                    boolean cartaRegresada = false;

                    // Buscar al jugador que tiene la carta
                    for (Jugador jugador : jugadores) {
                        if (jugador.getCartas().contains(carta)) {
                            // Eliminar la carta del jugador
                            jugador.getCartas().remove(carta);
                            mazo.regresarCartaAlMazo(carta);
                            cartaRegresada = true;
                            System.out.println("La carta " + carta + " ha sido regresada al mazo.");
                            break; // Ya no es necesario seguir buscando
                        }
                    }

                    // Si la carta no fue regresada, informamos al usuario
                    if (!cartaRegresada) {
                        System.out.println("La carta no fue encontrada en ninguna mano de los jugadores.");
                    }
                }


                case 7 -> {
                    System.out.println("Saliendo del juego...");
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }
}
