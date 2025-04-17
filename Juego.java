import java.util.ArrayList;
import java.util.Scanner;

public class Juego {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Mazo mazo = new Mazo();

        // Crear el mazo y barajarlo
        agregarCartasAlMazo(mazo);

        // Test de visualización eliminada porque daba error
        // mazo.barajar();

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

    private static void agregarCartasAlMazo(Mazo mazo) {
        for (Palo palo : Palo.values()) {
            for (int valor = 1; valor <= 13; valor++) {
                mazo.agregarCarta(new Carta(palo, valor));
            }
        }
        System.out.println("Se agregaron las cartas al mazo.");
    }

    private static ArrayList<Jugador> asignarJugadores(Scanner scanner) {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        int numJugadores;

        while (true) {
            System.out.print("¿Cuántos jugadores habrá? (mínimo 1): ");
            numJugadores = scanner.nextInt();
            scanner.nextLine();

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

    private static int obtenerNumeroCartas(Scanner scanner) {
        int numCartas;
        while (true) {
            System.out.print("¿Cuántas cartas quieres repartir a cada jugador?: ");
            numCartas = scanner.nextInt();
            scanner.nextLine();

            if (numCartas >= 1) {
                break;
            } else {
                System.out.println("El número de cartas debe ser al menos 1.");
            }
        }
        return numCartas;
    }

    private static void menuInteraccion(Mazo mazo, ArrayList<Jugador> jugadores, Scanner scanner) {
        while (true) {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Barajar mazo");
            System.out.println("2. Tomar carta del mazo");
            System.out.println("3. Mostrar cartas de un jugador");
            System.out.println("4. Mostrar cartas en el mazo");
            System.out.println("5. Volver el mazo a su estado original");
            System.out.println("6. Regresar una carta al mazo");
            System.out.println("7. Visualizar carta");
            System.out.println("8. Visualizar carta con diseño estético");
            System.out.println("9. Salir");
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
                            jugador.addCarta(carta);
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
                case 6 -> regresarCartaAlMazo(mazo, jugadores, scanner);
                case 8 -> {
                    System.out.println("Ingrese el valor de la carta (1-13): ");
                    int valor = scanner.nextInt();
                    System.out.println("Ingrese el palo (CORAZONES, TREBOLES, DIAMANTES, PICAS): ");
                    Palo palo = Palo.valueOf(scanner.next().toUpperCase());
                    Carta carta = new Carta(palo, valor);
                    Visualizador.visualizarCarta(carta);
                }
                case 9 -> {
                    System.out.println("¡Gracias por jugar!");
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    private static void regresarCartaAlMazo(Mazo mazo, ArrayList<Jugador> jugadores, Scanner scanner) {
        System.out.print("¿Qué carta quieres regresar al mazo (Ejemplo: 1 CORAZONES)? ");
        String cartaInput = scanner.nextLine();
        String[] partes = cartaInput.split(" ");
        int valor = Integer.parseInt(partes[0]);
        Palo palo = Palo.valueOf(partes[1].toUpperCase());

        Carta carta = new Carta(palo, valor);

        boolean cartaRegresada = false;

        for (Jugador jugador : jugadores) {
            if (jugador.getCartas().contains(carta)) {
                jugador.getCartas().remove(carta);
                mazo.regresarCartaAlMazo(carta);
                cartaRegresada = true;
                System.out.println("La carta " + carta + " ha sido regresada al mazo.");
                break;
            }
        }

        if (!cartaRegresada) {
            System.out.println("La carta no fue encontrada en ninguna mano de los jugadores.");
        }
    }

    private static void visualizarCarta(Mazo mazo, Scanner scanner) {
        System.out.print("¿Qué carta quieres visualizar (Ejemplo: 1 CORAZONES)? ");
        String cartaInput = scanner.nextLine();
        String[] partes = cartaInput.split(" ");

        try {
            int valor = Integer.parseInt(partes[0]);
            Palo palo = Palo.valueOf(partes[1].toUpperCase());

            Carta carta = new Carta(palo, valor);
            Visualizador.visualizarCarta(carta);
            System.out.println("Visualizando: " + carta.toString());
        } catch (Exception e) {
            System.out.println("Formato inválido. Usa el formato: NUMERO PALO (Ejemplo: 1 CORAZONES)");
        }
    }
}
