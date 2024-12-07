import java.util.Random;

public class Cache extends Memory {
    private int associativity;
    private int setsize;
    private static int lineSize;
    private boolean writeThrough; 
    private Memory nextLevel; 
    private int memorySize; 
    private boolean dirty;
    private int cache[][];
    Information info = new Information();
    private int lastUsedLine = 0;

    public Cache(String name, int latency, int associativity, int setsize, int lineSize, boolean writeThrough,
            Memory nextLevel) {
        super(name, latency);
        this.associativity = associativity;
        this.setsize = setsize;
        Cache.lineSize = lineSize;
        this.writeThrough = writeThrough;
        this.nextLevel = nextLevel;
        this.memorySize = (setsize * associativity * lineSize) / 1024;
        cache = new int[setsize][associativity];
    }

    public int getAssociativity() {
        return associativity;
    }

    public void setAssociativity(int associativity) {
        this.associativity = associativity;
    }

    public int getSetsize() {
        return setsize;
    }

    public void setSetsize(int setsize) {
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

    public boolean isDirty() {
        return dirty;
    }

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    @Override
    public int access(int address, boolean isWrite) {
        int set = address % setsize; 
        // System.out.println("set "+set);
        if(isWrite){
            if(dirty) nextLevel.access(address, isWrite);
            dirty = false;
            info.write();
            cache[set][lastUsedLine] = address;
            // System.out.println("escrita " + getName());
            // System.out.println("vzs q foi escrito "+ info.getTimesWritten());
            if(writeThrough) nextLevel.access(address, isWrite);
            else{
                if(lastUsedLine==associativity-1){
                    dirty = true;
                    nextLevel.access(address, isWrite);
                }
            }
            lastUsedLine = (lastUsedLine + 1) % associativity; 
            return latency;
        }
        else{
            info.read();
            for (int i = 0; i < cache[0].length; i++) {
                if (cache[set][i] == address) {
                    info.hit();
                    // System.out.println("Cache hit: " + name);
                    // System.out.println("Address: " + address);
                    return latency; // Latência local da cache
                }
            } 
            // System.out.println("Cache miss: " + name);
            // System.out.println("Adress: " + address);
            // System.out.println("Content: " + cache[0][1]);
            info.miss();
            return latency += nextLevel.access(address, isWrite);
        }
    }

    public void preencheCache(int bufferSize) {
        Random r = new Random();
        for (int i = 0; i < cache.length; i++) {
            for (int j = 0; j < cache[i].length; j++) {
                int end = 0; 
                do{
                    int low = 0;
                    int high = bufferSize;
                    end = r.nextInt(high-low) + low+1;
                }     
                while( end % setsize != i);
                cache[i][j] = end;
            }
        }
    }

    public void printCache(){
        for (int i = 0; i < cache.length; i++) {
            System.out.print("{");
            for (int j = 0; j < cache[i].length; j++) {
                System.out.print(cache[i][j]+",");
            }
            System.out.println("}");
        }
    }

    @Override
    public void showEstatistics(){
        System.out.println("----------------------------------------------------");
        System.out.println("Total time required (cycles): "+ latency);
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
