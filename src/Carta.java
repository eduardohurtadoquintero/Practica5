import java.util.Objects;

public class Carta {
    public Palo palo;
    public int valor;

    public Carta(Palo palo, int valor) {
        this.palo = palo;
        this.valor = valor;
    }

    @Override
    public String toString() {
        String valorStr;
        switch (valor) {
            case 1 -> valorStr = "A";
            case 11 -> valorStr = "J";
            case 12 -> valorStr = "Q";
            case 13 -> valorStr = "K";
            default -> valorStr = String.valueOf(valor);
        }
        return valorStr + " de " + palo;
    }

    @Override
    public boolean equals(Object obj) {
        // Si las cartas son del mismo tipo y tienen los mismos valores de palo y valor
        if (this == obj) return true; // Si es la misma instancia
        if (obj == null || getClass() != obj.getClass()) return false;
        Carta carta = (Carta) obj;
        return valor == carta.valor && palo == carta.palo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(palo, valor);
    }
}
