import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mazo {
    private ArrayList<Carta> mazo;
    private ArrayList<Carta> mazoOriginal;

    public Mazo() {
        mazo = new ArrayList<>();
        mazoOriginal = new ArrayList<>();
    }

    public void agregarCarta(Carta carta) {
        mazo.add(carta);
        mazoOriginal.add(new Carta(carta.palo, carta.valor)); // Clonamos para evitar referencias compartidas
    }

    public void barajar() {
        Collections.shuffle(mazo); // Baraja directamente el mazo real
        System.out.println("El mazo ha sido barajado.");
    }

    public void regresarMazoOriginal(ArrayList<Jugador> jugadores) {
        // Primero, regresamos las cartas de los jugadores al mazo
        for (Jugador jugador : jugadores) {
            for (Carta carta : jugador.getCartas()) {
                mazo.add(carta); // Regresamos las cartas al mazo
            }
            jugador.getCartas().clear(); // Limpiamos las cartas del jugador
        }

        mazo.clear();
        for (Carta carta : mazoOriginal) {
            mazo.add(new Carta(carta.palo, carta.valor)); // Clonamos las cartas originales
        }
        System.out.println("El mazo ha vuelto a su estado original.");
    }

    public Carta tomarCarta() {
        if (!mazo.isEmpty()) {
            return mazo.remove(0); // Remueve la carta del mazo
        }
        return null;
    }

    public int cartasDisponibles() {
        return mazo.size(); // devuelve el tamaño real del mazo
    }

    public void repartirCartas(ArrayList<Jugador> jugadores, int numCartas) {
        if (jugadores.size() * numCartas > mazo.size()) {
            throw new IllegalArgumentException("No hay suficientes cartas para repartir.");
        }

        for (int i = 0; i < numCartas; i++) {
            for (Jugador jugador : jugadores) {
                Carta carta = tomarCarta();
                if (carta != null) {
                    jugador.addCarta(carta);
                }
            }
        }
    }

    public void regresarCartaAlMazo(Carta carta) {
        mazo.add(carta);
        System.out.println("La carta " + carta + " ha sido regresada al mazo.");
    }

    // Devuelve una vista de solo lectura para reflejar los cambios tras barajar
    public List<Carta> getCartas() {
        return Collections.unmodifiableList(mazo);
    }

    public void clear() {
        mazo.clear();
    }
}
