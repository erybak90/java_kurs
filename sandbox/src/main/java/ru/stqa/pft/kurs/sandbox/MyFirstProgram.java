package ru.stqa.pft.kurs.sandbox;

public class MyFirstProgram {
    public static void main(String[] args) {
      hello("world");
      hello("user");
      hello("Ewelina");

      Square s = new Square(5);
      System.out.println("powierzchnia kwadratu o boku " + s.l + " = " + s.area());

      Rectangle r = new Rectangle (4,6 );
      System.out.println("powierzchnia prostokątu o boku " + r.a + " i " + r.b + " = " + r.area());

    }
    public static void hello (String somebody) {
      System.out.println("hello, " + somebody + "!");
}


}
