package Day2;

import java.util.Arrays;

public class RunComputer {
    public static void main(String[] args) throws IllegalStateException {
        String memoryString = "1,0,0,3,1,1,2,3,1,3,4,3,1,5,0,3,2,13,1,19,1,10,19,23,1,6,23,27,1,5,27,31,1,10,31,35,2,10,35,39,1,39,5,43,2,43,6,47,2,9,47,51,1,51,5,55,1,5,55,59,2,10,59,63,1,5,63,67,1,67,10,71,2,6,71,75,2,6,75,79,1,5,79,83,2,6,83,87,2,13,87,91,1,91,6,95,2,13,95,99,1,99,5,103,2,103,10,107,1,9,107,111,1,111,6,115,1,115,2,119,1,119,10,0,99,2,14,0,0";
        String[] memoryStrings = memoryString.split(",");

        int[] memory = stringsToIntegers(memoryStrings);

        for(int NOUN = 0; NOUN <= 99; NOUN++) {
            memory[1] = NOUN;
            for(int VERB = 0; VERB <= 99; VERB++) {
                memory[2] = VERB;

                int[] mem = memory.clone();
                int res = runComputer(mem);
                if(res == 19690720) {
                    System.out.println(
                            "RESULT DETERMINED " + "\n" +
                            "NOUN: " + NOUN + "\n" +
                            "VERB: " + VERB
                    );

                    System.exit(99);
                }
            }
        }
        System.out.println("ERR: RES NOT FND");
    }

    public static int[] stringsToIntegers(String[] strings) {
        int l = strings.length;
        int[] result = new int[l];
        for(int i = 0; i < l; i++) {
            result[i] = Integer.parseInt(strings[i]);
        }
        return result;
    }

    public static int runComputer(int[] mem) {
        Computer computer = new Computer(mem);

        while(!computer.isFin()) {
            computer.iterate();
        }
        //System.out.println("EOF" + "\n");
        return computer.accMem(0);
    }
}
