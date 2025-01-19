
public class Cache extends Memory {
    private int associativity;
    private int setsize;
    static int lineSize;
    private boolean writeThrough; 
    private Memory nextLevel; 
    private int memorySize; 
    private Linha cache[][];
    private int totalLatency;
    Information info = new Information();

    public Cache(String name, int latency, int associativity, int setsize, int lineSize, boolean writeThrough, Memory nextLevel, boolean powerOfTwo) {
        super(name, latency);
        this.associativity = associativity;
        this.nextLevel = nextLevel;
        this.writeThrough = writeThrough;
        if(powerOfTwo){
            this.setsize = (int) Math.pow(2, setsize);
            Cache.lineSize = (int) Math.pow(2,lineSize);
            this.memorySize = (this.setsize * associativity * Cache.lineSize) / 1024;
            cache = new Linha [(this.setsize)][associativity];
        }
        else{
            this.setsize = setsize;
            Cache.lineSize = lineSize;
            this.memorySize = (setsize * associativity * lineSize) / 1024;
            cache = new Linha [setsize][associativity];
        }
    }

    public int getAssociativity() {
        return associativity;
    }

    public void setAssociativity(int associativity) {
        this.associativity = associativity;
    }

    public int getSetSize() {
        return setsize;
    }

    public void setSetSize(int setsize) {
        this.setsize = setsize;
    }

    public static int getLineSize() {
        return lineSize;
    }

    public static void setLineSize(int lineSize) {
        Cache.lineSize = lineSize;
    }

    public boolean getWritePolicy() {
        return writeThrough;
    }

    public void setWriteThrough(boolean writeThrough) {
        this.writeThrough = writeThrough;
    }

    public Memory getNextLevel() {
        return nextLevel;
    }

    public void setNextLevel(Memory nextLevel) {
        this.nextLevel = nextLevel;
    }

    public int getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(int memorySize) {
        this.memorySize = memorySize;
    }


    @Override
    public int access(Integer address, boolean isWrite) {
        int set = address % setsize; //vai ser o indice da linha la de extrai bit
        // System.out.println("set "+set);
        if(isWrite){
            info.write();
            for (int i = 0; i < associativity; i++) {
                if(cache[set][i].status.equals("Inválido")) {
                    // System.out.println("escrita " + getName());
                    // System.out.println("vzs q foi escrito "+ info.getTimesWritten());        
                    cache[set][i].update(address, isWrite, writeThrough);
                    if(writeThrough) totalLatency += nextLevel.access(address, isWrite);
                    totalLatency += latency;
                    return latency;
                }
            }
            int lru = Linha.compareLastUsedLine(cache[set]);
            if(!writeThrough){
                if(cache[set][lru].status.equals("Sujo")) {
                    totalLatency += nextLevel.access(cache[set][lru].tag, true);
                }
                cache[set][lru].update(address, isWrite, writeThrough);
                totalLatency += latency;
                return latency;
            }
            cache[set][lru].update(address, isWrite, writeThrough);
            totalLatency += totalLatency += nextLevel.access(cache[set][lru].tag, true);
            return latency;
        }
        else{
            info.read();
            for (int i = 0; i < associativity; i++) {
                // cache[set][0].print();
                if (cache[set][i].tag == address && !cache[set][i].status.equals("Inválido")) {
                    info.hit();
                    cache[set][i].usage();
                    // System.out.println("Cache hit: " + name);
                    // System.out.println("Address: " + address);
                    return latency;
                }
            } 
            // System.out.println("Cache miss: " + name);
            // System.out.println("Adress: " + address);
            // System.out.println("Content: " + cache[0][1]);
            info.miss();
            int lru = Linha.compareLastUsedLine(cache[set]);
            if(cache[set][lru].status.equals("Sujo")) {
                totalLatency += nextLevel.access(cache[set][lru].tag, true);
                //ver como vai ficar, pq se eu só enviar a tag, eu vou precisar fazer uma lógica pra pegar o indice que seria o set, pq eu provavelmente passaria o endereço e tiraria o indice e o tag em acess, mas liniha não tem indice, entendeu? 
            }
            cache[set][lru].update(address, isWrite, writeThrough);
            totalLatency += nextLevel.access(address, isWrite);
            return latency;
        }
    }

    public void fillCache() {
        for (int i = 0; i < cache.length; i++) {
            for (int j = 0; j < cache[i].length; j++) {
                cache[i][j] = new Linha();
            }
        }
    }

    public void printCache() {
        for (int i = 0; i < cache.length; i++) {
            System.out.print("{");
            for (int j = 0; j < cache[i].length; j++) {
                System.out.print(cache[i][j]+",");
            }
            System.out.println("}");
        }
    }

    public void printBloco(int set) {
        System.out.print("{");
        for (int j = 0; j < cache[set].length; j++) {
            System.out.print(cache[set][j]+",");
        }
        System.out.println("}");
    }

    
    @Override
    public void showEstatistics(){
        System.out.println("----------------------------------------------------");
        System.out.println("Total time required (cycles): "+ totalLatency);
        System.out.println("----------------------------------------------------");
        System.out.println("Stats of "+ name);
        System.out.println(name + " size "+ memorySize +" KB");
        System.out.println(name + " read Accesses "+ info.getTimesRead());
        System.out.println(name + " write Acesses "+ info.getTimesWritten());
        System.out.println(name + " hits "+ info.getHits());
        System.out.println(name + " miss "+ info.getMisses());
        nextLevel.showEstatistics();
        info.reset();
    }
}
