public class Cuadrado extends Square {
    public Cuadrado() {
        super();
    }

    public void moveTo(int x, int y) {
        makeInvisible();
        xPosition = x;
        yPosition = y;
        makeVisible();
    }
}
