import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Visualizador {
    private static JFrame frame;
    private static CartaPanel cartaPanel;

    static {
        frame = new JFrame("Visualizador de Carta");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(800, 600);
        cartaPanel = new CartaPanel();
        frame.add(cartaPanel);
    }

    public static void carta(Carta c, Posicion p) {
        cartaPanel.setCarta(c, p);
        frame.setVisible(true);
        cartaPanel.repaint();
    }
}

class CartaPanel extends JPanel {
    private Carta carta;
    private Posicion pos;

    public void setCarta(Carta c, Posicion p) {
        this.carta = c;
        this.pos = p;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (carta != null && pos != null) {
            g.drawRect(pos.getX(), pos.getY(), 70, 100);
            
            // Obtener el símbolo según el palo
            String simbolo = switch (carta.palo) {
                case CORAZONES -> "♥";
                case DIAMANTES -> "♦";
                case TREBOLES -> "♣";
                case PICAS -> "♠";
            };
            
            // Convertir el valor a una representación más amigable
            String valor = switch (carta.valor) {
                case 1 -> "A";
                case 11 -> "J";
                case 12 -> "Q";
                case 13 -> "K";
                default -> String.valueOf(carta.valor);
            };
            
            // Dibujar el valor y el símbolo
            g.drawString(valor + simbolo, pos.getX() + 10, pos.getY() + 50);
        }
    }
}
