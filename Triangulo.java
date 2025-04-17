public class Triangulo extends Triangle {
    public Triangulo() {
        super();
    }

    public void moveTo(int x, int y) {
        makeInvisible();
        xPosition = x;
        yPosition = y;
        makeVisible();
    }
}
