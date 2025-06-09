package Test.TestInstrucciones;
import Main.Computador.Assembler;
import Main.Computador.Computer;
import Main.Computador.Opcodes;

public class TestSumaTresValores {
    public static void main(String[] args) {
        Computer comp;
        boolean ok;

        // SUMAR
        comp = new Computer();
        long[] progSUMAR = {
            Assembler.movi(1, 7),
            Assembler.movi(2, 9),
            Assembler.movi(3, 11),
            Assembler.instr(Opcodes.SUMAR, 4, 1, 2, 0),
            Assembler.instr(Opcodes.SUMAR, 5, 4, 3, 0),
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0)
        };
        comp.loadProgram(progSUMAR, 0);
        comp.execute();
        ok = comp.registers[5] == 27;
        System.out.println("SUMAR: " + (ok ? "OK" : "FALLÓ") + "\n");
    }
}