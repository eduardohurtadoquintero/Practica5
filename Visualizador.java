import java.awt.*;
import javax.swing.*;

public class Visualizador {
    public static void visualizarCarta(Carta carta) {
        String palo = carta.palo.name();
        int valor = carta.valor;

        JFrame frame = new JFrame();
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Fondo blanco y contorno negro
                g.setColor(Color.WHITE);
                g.fillRect(80, 80, 120, 180);
                g.setColor(Color.BLACK);
                g.drawRect(80, 80, 120, 180);

                // Mostrar el número/letra
                String numero = switch (valor) {
                    case 1 -> "A";
                    case 11 -> "J";
                    case 12 -> "Q";
                    case 13 -> "K";
                    default -> String.valueOf(valor);
                };
                g.drawString(numero, 120, 100);
                g.drawString(numero, 120, 250);

                // Dibujar los símbolos
                if (palo.equals("CORAZONES") || palo.equals("TREBOLES")) {
                    g.setColor(palo.equals("CORAZONES") ? Color.RED : Color.BLACK);

                    // Torre de cuadrados: 3 abajo, 1 arriba al centro
                    g.fillRect(110, 170, 15, 15);
                    g.fillRect(125, 170, 15, 15);
                    g.fillRect(140, 170, 15, 15);
                    g.fillRect(125, 150, 15, 15);
                } else if (palo.equals("PICAS") || palo.equals("DIAMANTES")) {
                    g.setColor(palo.equals("PICAS") ? Color.BLACK : Color.RED);

                    // Trifuerza: dos triángulos abajo, uno arriba
                    // Izquierdo
                    g.fillPolygon(
                            new int[]{120, 130, 140},
                            new int[]{180, 160, 180}, 3
                    );
                    // Derecho
                    g.fillPolygon(
                            new int[]{140, 150, 160},
                            new int[]{180, 160, 180}, 3
                    );
                    // Superior
                    g.fillPolygon(
                            new int[]{130, 140, 150},
                            new int[]{155, 135, 155}, 3
                    );
                }
            }
        };

        frame.add(panel);
        frame.setVisible(true);
    }
}
