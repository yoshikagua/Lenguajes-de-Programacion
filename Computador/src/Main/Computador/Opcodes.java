package Main.Computador;

public class Opcodes {
    public static final int HALT = 0;         // Detiene la CPU
    public static final int SUMAR = 1;        // Suma: R1 = R2 + R3 o R1 = R2 + ext
    public static final int RESTAR = 2;       // Resta: R1 = R2 - R3 o R1 = R2 - ext
    public static final int MULTIPLICAR = 3;  // Multiplica: R1 = R2 * R3 o R1 = R2 * ext
    public static final int DIVIDIR = 4;      // Divide: R1 = R2 / R3 o R1 = R2 / ext
    public static final int AND = 5;          // AND lógico: R1 = R2 & R3
    public static final int OR = 6;           // OR lógico: R1 = R2 | R3
    public static final int XOR = 7;          // XOR lógico: R1 = R2 ^ R3
    public static final int NOT = 8;          // NOT lógico: R1 = ~R2
    public static final int SHL = 9;          // Shift left: R1 = R2 << ext
    public static final int SHR = 10;         // Shift right: R1 = R2 >> ext
    public static final int CARGAR = 11;      // Cargar de memoria: R1 = Mem[R2 + R3*8]
    public static final int GUARDAR = 12;     // Guardar en memoria: Mem[R2 + R3*8] = R1
    public static final int PUSH = 13;        // Apilar: push R1 en la pila
    public static final int POP = 14;         // Desapilar: pop a R1 desde la pila
    public static final int LEA = 15;         // Cargar dirección efectiva: R1 = R2 + ext
    public static final int SALTAR = 16;      // Salto incondicional: PC = ext
    public static final int SIZ = 17;         // Salta si Zero: si FLAGS==1, PC=ext
    public static final int SINZ = 18;        // Salta si no Zero: si FLAGS==0, PC=ext
    public static final int JG = 19;          // Salta si mayor: si R1 > R2, PC=ext
    public static final int JL = 20;          // Salta si menor: si R1 < R2, PC=ext
    public static final int JGE = 21;         // Salta si mayor o igual: si R1 >= R2, PC=ext
    public static final int JLE = 22;         // Salta si menor o igual: si R1 <= R2, PC=ext
    public static final int CALL = 23;        // Llama subrutina: guarda PC y salta a ext
    public static final int RET = 24;         // Retorna de subrutina: PC = valor en pila
    public static final int CMP = 25;         // Compara: actualiza FLAGS según R1 - R2
    public static final int IN = 26;          // Entrada: lee valor externo a R1
    public static final int OUT = 27;         // Salida: escribe R2 a dispositivo externo
    public static final int NOP = 28;         // No Operation: no hace nada
    public static final int CLI = 29;         // Deshabilita interrupciones
    public static final int STI = 30;         // Habilita interrupciones
    public static final int MOV = 31;         // Copia: R1 = R2
    public static final int MOVI = 32;        // Carga inmediato: R1 = ext
    public static final int CLEAR = 33;       // Limpia registro: R1 = 0
    public static final int SWAP = 34;        // Intercambia: R1 <-> R2
    public static final int NEGAR = 35;       // Negación aritmética: R1 = -R1
    public static final int INCREMENTAR = 36; // Incrementa: R1 = R1 + ext
    public static final int DECREMENTAR = 37; // Decrementa: R1 = R1 - ext
    public static final int ROTAR_I = 38;     // Rotar izquierda: R1 = rotl(R2, ext)
    public static final int ROTAR_D = 39;     // Rotar derecha: R1 = rotr(R2, ext)
    public static final int BUSCAR_BIT = 40;  // Busca primer bit activo en R2, resultado en R1
    public static final int SET_BIT = 41;     // Pone a 1 el bit 'ext' de R1
    public static final int LIMPIAR_BIT = 42; // Pone a 0 el bit 'ext' de R1

}