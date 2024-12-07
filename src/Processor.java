import java.util.Random;

public class Processor {
    private Memory memoryHierarchy; 
    private int bufferSize;
    private int stride; 
    private int accessCount;

    public Processor(Memory memoryHierarchy, int bufferSize, int stride, int accessCount) {
        this.memoryHierarchy = memoryHierarchy;
        this.bufferSize = bufferSize;
        this.stride = stride;
        this.accessCount = accessCount;
    }

    public void runSequentialAccess() {
        int address = 0;
        Random r = new Random();
        for (int i = 0; i < accessCount; i++) {
            // System.out.println("Acesso ao endereço: " + address);
            memoryHierarchy.access(address, r.nextBoolean());
            address = (address + stride) % bufferSize; 
        }
    }

    public void runRandomAccess() {
        Random r = new Random();
        for (int i = 0; i < accessCount; i++) {
            int address = r.nextInt(bufferSize+1);
            memoryHierarchy.access(address, r.nextBoolean()); 
        }
    }
}
