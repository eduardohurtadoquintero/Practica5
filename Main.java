public class Main {
    public static void main(String[] args) {
        Square square = new Square();
        square.changeColor("blue");
        square.makeVisible();
        Circle circle = new Circle();
        circle.changeColor("red");
        circle.makeVisible();
        Person person = new Person();
        person.changeColor("green");
        person.makeVisible();
    }
}
