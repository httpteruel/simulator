public class Main {
    public static void main(String[] args) {
        MainMemory mainMemory = new MainMemory("Memória Principal", 200);
        
        // Cache l2Cache = new Cache("Cache L2", 4, 4, 512, 32, false, mainMemory, false);
        // Cache l1Cache = new Cache("Cache L1", 1, 4, 32, 64, true, l2Cache, false);
        Cache l2Cache = new Cache("Cache L2", 4, 4, 9, 5, false, mainMemory, true);
        Cache l1Cache = new Cache("Cache L1", 1, 4, 5, 6, true, l2Cache, true);

        System.out.println("tamanho da memória L1: "+l1Cache.getMemorySize()+" tamanho dos sets L1: "+l1Cache.getSetSize()+" tamanho da linha L1: "+Cache.getLineSize());
        System.out.println("tamanho da memória L2: "+l2Cache.getMemorySize()+" tamanho dos sets L2: "+l2Cache.getSetSize()+" tamanho da linha L2: "+Cache.getLineSize());

        Processor processor = new Processor();
        
        l1Cache.fillCache();
        l2Cache.fillCache();
        
        // processor.runSequentialAccess(l1Cache, 20000, 32, 200);
        
        processor.runRandomAccess(l1Cache, 20000, 200);
    }
}
