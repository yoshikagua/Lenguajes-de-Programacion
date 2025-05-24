package Test.TestInstrucciones;
import Main.Computador.Assembler;
import Main.Computador.Computer;
import Main.Computador.Opcodes;

public class TestInstruccionesControlFlujo {
    public static void main(String[] args) {
        Computer comp;
        boolean ok;

        // SIZ (salta si FLAGS==1)
        comp = new Computer();
        comp.FLAGS = 1;
        comp.registers[1] = 77;
        long[] progSIZ = {
            Assembler.instr(Opcodes.SIZ, 0, 0, 0, 16),
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0),
            Assembler.instr(Opcodes.GUARDAR, 1, 0, 0, 0x5000),
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0)
        };
        comp.loadProgram(progSIZ, 0);
        comp.execute();
        ok = comp.memory[0x5000 / Computer.WORD_SIZE] == 77;
        System.out.println("SIZ: " + (ok ? "OK" : "FALLÓ") + "\n");

        // JL (salta si R1 < R2)
        comp = new Computer();
        long[] progJL = {
            Assembler.movi(1, 5),
            Assembler.movi(2, 10),
            Assembler.instr(Opcodes.JL, 1, 2, 0, 32),
            Assembler.movi(3, 999),
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0)
        };
        comp.loadProgram(progJL, 0);
        comp.execute();
        ok = comp.registers[3] == 0;
        System.out.println("JL: " + (ok ? "OK" : "FALLÓ") + "\n");
    }
}