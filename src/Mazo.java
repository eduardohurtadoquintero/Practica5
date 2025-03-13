import java.util.ArrayList;
import java.util.Collections;

public class Mazo {
    private ArrayList<Carta> mazo;
    private ArrayList<Carta> mazoOriginal;  // Para guardar el mazo original

    public Mazo() {
        mazo = new ArrayList<>();
        mazoOriginal = new ArrayList<>();
    }

    public void agregarCarta(Carta carta) {
        mazo.add(carta);
        mazoOriginal.add(carta); // También agregamos la carta al mazo original
    }

    public void barajar() {
        Collections.shuffle(mazo);
    }

    public void regresarMazoOriginal() {
        mazo = new ArrayList<>(mazoOriginal);  // Restauramos el mazo original
        System.out.println("El mazo ha vuelto a su estado original.");
    }

    public Carta tomarCarta() {
        if (!mazo.isEmpty()) {
            return mazo.remove(0);
        }
        return null;
    }

    public int cartasDisponibles() {
        return mazo.size();
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

    // metodo para devolver una carta al mazo
    public void regresarCartaAlMazo(Carta carta) {
        mazo.add(carta);
        System.out.println("La carta " + carta + " ha sido regresada al mazo.");
    }
}
