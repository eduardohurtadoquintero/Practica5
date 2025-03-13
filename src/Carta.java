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
}
