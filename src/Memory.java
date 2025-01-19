public abstract class Memory {
    protected String name;
    protected final int latency;
    Information info = new Information();

    public Memory(String name, int latency) {
        this.name = name;
        this.latency = latency;
    }
    
    public abstract int access(Integer address, boolean isWrite); 

    public abstract void showEstatistics();
    
    public String getName() {
        return name;
    }
    public int getLatency() {
        return latency;
    }
}
