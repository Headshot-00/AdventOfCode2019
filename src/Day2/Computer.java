package Day2;

public class Computer {
    
    private int[] mem;
    private int addr;
    private boolean fin;

    public Computer(int[] opcodes) {
        this.mem = opcodes;
        this.addr = 0;
        this.fin = false;
    }

    public int accMem(int pointer) {
        return this.mem[pointer];
    }

    public int[] memDump() {
        return mem;
    }

    public boolean isFin() {
        return fin;
    }

    public void iterate() {
        switch (mem[addr]) {
            case 1:
                add();
                addr += 4;
                break;
            case 2:
                multiply();
                addr += 4;
                break;
            case 99:
                fin = true;
                addr += 1;
                break;
            default:
                System.out.println("HALTING \n" +
                        "UNEXPECTED OPCODE IN MEMORY: " + mem[addr] + "\n" +
                        "AT ADDRESS: " + addr);
                throw new IllegalStateException("Unexpected value: " + mem[addr]);
        }
    }

    private void add() {
        int a, b, c;
        a = this.mem[addr +1];
        b = this.mem[addr +2];
        c = this.mem[addr +3];
        this.mem[c] = this.mem[a]+ this.mem[b];
        System.out.println("ADD [" + a + "] [" + b + "] [" + c + "] " + this.mem[c]);
    }

    private void multiply() {
        int a, b, c;
        a = this.mem[addr +1];
        b = this.mem[addr +2];
        c = this.mem[addr +3];
        this.mem[c] = this.mem[a]* this.mem[b];
        System.out.println("MUL [" + a + "] [" + b + "] [" + c + "] " + this.mem[c]);
    }
}
