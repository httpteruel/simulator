public class Cache extends Memory {
    private int associativity;
    private int setSize;
    private int lineSize;
    private String writePolicy; // Write-through ou Write-back
    private Memory nextLevel; // Próximo nível da hierarquia

    public Cache(String name, int latency, int associativity, int setSize, int lineSize, String writePolicy, Memory nextLevel) {
        super(name, latency);
        this.associativity = associativity;
        this.setSize = setSize;
        this.lineSize = lineSize;
        this.writePolicy = writePolicy;
        this.nextLevel = nextLevel;
    }

    




@Override
    public int access(int address, boolean isWrite) {
        // Simula a lógica de acesso a cache, incluindo hits e misses
        boolean hit = checkCache(address); // Simulação simplificada do acesso à cache
        if (hit) {
            System.out.println("Cache hit: " + name);
            return latency; // Latência local da cache
        } else {
            System.out.println("Cache miss: " + name);
            return latency + nextLevel.access(address, isWrite); // Soma a latência com o próximo nível
        }
    }
    private boolean checkCache(int address) {
        // Lógica fictícia para verificar se o dado está na cache (hit ou miss)
        // Pode ser substituído por lógica mais complexa depois
        return Math.random() > 0.5; // 50% de chance de hit
    }
    // Getters e Setters para os atributos
}
