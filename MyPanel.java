import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {

    // Método para dibujar la carta con el contorno
    public void drawCard(String text, int x, int y, Color textColor, int fontSize) {
        Graphics g = getGraphics();

        // Dibujar el contorno de la carta (rectángulo con bordes redondeados)
        g.setColor(Color.white);  // Color de la carta
        g.fillRoundRect(x, y, 120, 180, 30, 30);  // Rectángulo con bordes redondeados
        g.setColor(Color.black);  // Color del borde
        g.drawRoundRect(x, y, 120, 180, 30, 30);  // Dibujar borde de la carta

        // Dibujar el número o símbolo en el centro de la carta
        g.setColor(textColor);  // Establecer el color del texto
        g.setFont(new Font("Arial", Font.BOLD, fontSize));
        g.drawString(text, x + 45, y + 90);  // Colocar el texto en el centro de la carta
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Custom drawing logic goes here
    }
}
