public abstract class Memory {
    protected String name;
    protected int latency; // em ciclos

    public Memory(String name, int latency) {
        this.name = name;
        this.latency = latency;
    }
    public abstract int access(int address, boolean isWrite); 

    public abstract void showEstatistics();
    
    public String getName() {
        return name;
    }
    public int getLatency() {
        return latency;
    }
}
