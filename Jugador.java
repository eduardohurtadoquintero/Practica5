import java.util.ArrayList;
public class Jugador {

    public String nombre;
    private ArrayList<Carta> cartas;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.cartas = new ArrayList<>();
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    public void addCarta(Carta carta) {
        cartas.add(carta);
    }

    public void infoJugador() {
        System.out.println("Jugador: " + nombre);
        System.out.println("Cartas: ");
        for (Carta carta : cartas) {
            System.out.println(carta);
        }
    }

}
