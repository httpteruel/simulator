public class Processor {
    private Memory memoryHierarchy; // Primeiro nível da hierarquia
    private int bufferSize;
    private int stride; // Apenas para o padrão sequencial
    private int accessCount;

    public Processor(Memory memoryHierarchy, int bufferSize, int stride, int accessCount) {
        this.memoryHierarchy = memoryHierarchy;
        this.bufferSize = bufferSize;
        this.stride = stride;
        this.accessCount = accessCount;
    }

    public void runSequentialAccess() {
        int address = 0;
        for (int i = 0; i < accessCount; i++) {
            System.out.println("Acesso sequencial ao endereço: " + address);
            memoryHierarchy.access(address, false); // Acesso de leitura
            address = (address + stride) % bufferSize; // Reinicia quando atingir o buffer
        }
    }

    public void runRandomAccess() {
        for (int i = 0; i < accessCount; i++) {
            int address = (int) (Math.random() * bufferSize);
            System.out.println("Acesso aleatório ao endereço: " + address);
            memoryHierarchy.access(address, false); // Acesso de leitura
        }
    }
}
