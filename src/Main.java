public class Main {
    public static void main(String[] args) {
        MainMemory mainMemory = new MainMemory("Memória Principal", 200);
        
        Cache l2Cache = new Cache("Cache L2", 4, 4, 512, 64, false, mainMemory);
        Cache l1Cache = new Cache("Cache L1", 1, 4, 32, 64, true, l2Cache);

        //Memory hierarquia [] = {l1Cache, l2Cache,mainMemory};

        Processor processor = new Processor(l1Cache, 20000, 32, 100000);
        
        //l1Cache.preencheCache(1000);
        //l2Cache.preencheCache(1000);

        
        // System.out.println("Execução com padrão sequencial:");
        // processor.runSequentialAccess();
        // l1Cache.showEstatistics();
        
        System.out.println("\nExecução com padrão aleatório:");
        processor.runRandomAccess();
        l1Cache.showEstatistics();

        
        // l1Cache.printCache();
        // System.out.println("--------------------------------------------------------");
        // l2Cache.printCache();
        // System.out.println("--------------------------------------------------------");
    }
}
