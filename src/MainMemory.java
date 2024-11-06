public class MainMemory extends Memory {
    public MainMemory(String name, int latency) {
        super(name, latency);
    }
    @Override
    public int access(int address, boolean isWrite) {
        // Simula o acesso à memória principal, sempre retorna a latência fixa
        System.out.println("Acessando memória principal: " + name);
        return latency;
    }
}
