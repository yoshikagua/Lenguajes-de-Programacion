package Test.TestInstrucciones;
import Main.Computador.Assembler;
import Main.Computador.Computer;
import Main.Computador.Opcodes;

public class TestInstruccionesControlSalto {
    public static void main(String[] args) {
        Computer comp;
        boolean ok;

        // SALTAR
        comp = new Computer();
        long[] progSALTAR = {
            Assembler.instr(Opcodes.SALTAR, 0, 0, 0, 24), // Salta a PC=24
            Assembler.movi(1, 999), // PC=8
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0), // PC=16
            Assembler.movi(1, 123), // PC=24 (aquí debe saltar)
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0) // PC=32
        };
        comp.loadProgram(progSALTAR, 0);
        comp.execute();
        ok = comp.registers[1] == 123;
        System.out.println("SALTAR: " + (ok ? "OK" : "FALLÓ") + "\n");

        // SINZ (salta si FLAGS != 0)
        comp = new Computer();
        comp.FLAGS = 0; // <-- Cambia esto a 0
        long[] progSINZ = {
            Assembler.instr(Opcodes.SINZ, 0, 0, 0, 24), // Salta a PC=24
            Assembler.movi(1, 999), // PC=8
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0), // PC=16
            Assembler.movi(1, 321), // PC=24 (aquí debe saltar)
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0) // PC=32
        };
        comp.loadProgram(progSINZ, 0);
        comp.execute();
        ok = comp.registers[1] == 321;
        System.out.println("SINZ: " + (ok ? "OK" : "FALLÓ") + "\n");

        // JG (salta si R1 > R2)
        comp = new Computer();
        long[] progJG = {
            Assembler.movi(1, 10), // PC=0
            Assembler.movi(2, 5),  // PC=8
            Assembler.instr(Opcodes.JG, 1, 2, 0, 40), // Salta a PC=40
            Assembler.movi(3, 999), // PC=24
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0), // PC=32
            Assembler.movi(3, 111), // PC=40
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0) // PC=48 (aquí debe saltar)
        };
        comp.loadProgram(progJG, 0);
        comp.execute();
        ok = comp.registers[3] == 111;
        System.out.println("JG: " + (ok ? "OK" : "FALLÓ") + "\n");

        // JGE (salta si R1 >= R2)
        comp = new Computer();
        long[] progJGE = {
            Assembler.movi(1, 10), // PC=0
            Assembler.movi(2, 10), // PC=8
            Assembler.instr(Opcodes.JGE, 1, 2, 0, 40), // Salta a PC=40
            Assembler.movi(3, 999), // PC=24
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0), // PC=32
            Assembler.movi(3, 222), // PC=40 (aquí debe saltar)
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0) // PC=48
        };
        comp.loadProgram(progJGE, 0);
        comp.execute();
        ok = comp.registers[3] == 222;
        System.out.println("JGE: " + (ok ? "OK" : "FALLÓ") + "\n");

        // JLE (salta si R1 <= R2)
        comp = new Computer();
        long[] progJLE = {
            Assembler.movi(1, 5), // PC=0
            Assembler.movi(2, 10), // PC=8
            Assembler.instr(Opcodes.JLE, 1, 2, 0, 40), // Salta a PC=40
            Assembler.movi(3, 999), // PC=24
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0), // PC=32
            Assembler.movi(3, 333), // PC=40 (aquí debe saltar)
            Assembler.instr(Opcodes.HALT, 0, 0, 0, 0) // PC=48
        };
        comp.loadProgram(progJLE, 0);
        comp.execute();
        ok = comp.registers[3] == 333;
        System.out.println("JLE: " + (ok ? "OK" : "FALLÓ") + "\n");
    }
}