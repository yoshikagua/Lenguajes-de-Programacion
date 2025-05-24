package Test.TestInstrucciones;
import Main.Computador.Assembler;
import Main.Computador.Computer;
import Main.Computador.Opcodes;

public class TestInstruccionesEntradaSalida {
    public static void main(String[] args) {
        Computer comp;
        boolean ok;

        // OUT (simulado)
        comp = new Computer();
        long[] progOUT = {
            Assembler.movi(1, 1234),
            Assembler.instr(Opcodes.OUT, 0, 1, 0, 0),
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0)
        };
        comp.loadProgram(progOUT, 0);
        comp.execute();
        // OUT solo imprime, no cambia registros, así que solo confirmamos que no hubo error
        System.out.println("OUT: OK\n");

        // IN (simulado, valor fijo 0)
        comp = new Computer();
        long[] progIN = {
            Assembler.instr(Opcodes.IN, 2, 0, 0, 0),
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0)
        };
        comp.loadProgram(progIN, 0);
        comp.execute();
        ok = comp.registers[2] == 0;
        System.out.println("IN: " + (ok ? "OK" : "FALLÓ") + "\n");
    }
}