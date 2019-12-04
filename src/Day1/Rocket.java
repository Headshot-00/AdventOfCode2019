package Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Rocket {
    private int total;

    public Rocket() {
        this.total = 0;
    }

    public void calculate(int mass) {
        mass /= 3;
        mass -= 2;
        int fuel = 0;
        while(mass > 0) {
            fuel += mass;
            mass /= 3;
            mass -= 2;
        }
        this.total += fuel;
    }

    public int getTotal() {
        return this.total;
    }

    public static void main(String[] args) throws FileNotFoundException {

        Rocket rocket = new Rocket();
        File file = new File("day1Input.txt");
        Scanner sc = new Scanner(file);
        while(sc.hasNextLine()) {
            rocket.calculate(Integer.parseInt(sc.nextLine()));
        }
        System.out.println(rocket.getTotal());
    }
}
