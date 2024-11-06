public abstract class Memory {
    protected String name;
    protected int latency; // em ciclos

    public Memory(String name, int latency) {
        this.name = name;
        this.latency = latency;
    }
    public abstract int access(int address, boolean isWrite); // Método abstrato para acessar a memória
    public String getName() {
        return name;
    }
    public int getLatency() {
        return latency;
    }
}
