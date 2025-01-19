import java.util.Random;

public class Processor {

    public void runSequentialAccess(Memory memoryHierarchy, int bufferSize, int stride, int accessCount) {
        System.out.println("Execução com padrão sequencial:");
        int address = 0;
        Random r = new Random();
        for (int i = 0; i < accessCount; i++) {
            memoryHierarchy.access(address, r.nextBoolean());
            address = (address + stride) % bufferSize; 
        }
        memoryHierarchy.showEstatistics();
    }

    public void runRandomAccess(Memory memoryHierarchy, int bufferSize, int accessCount) {
        System.out.println("\nExecução com padrão aleatório:");
        Random r = new Random();
        for (int i = 0; i < accessCount; i++) {
            int address = r.nextInt(bufferSize+1);
            memoryHierarchy.access(address, r.nextBoolean()); 
        }
        memoryHierarchy.showEstatistics();
    }

    private static int extract_bits (int source, int bstart, int blength)
    {
        int mask = (1 << blength) - 1;
        return ((source >> bstart) & mask);
    }
}
