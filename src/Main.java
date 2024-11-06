public class Main {
    public static void main(String[] args) {
        // Configurando a memória principal
        MainMemory mainMemory = new MainMemory("Memória Principal", 100);

        // Configurando a hierarquia de caches
        Cache l2Cache = new Cache("Cache L2", 20, 4, 64, 32, "Write-back", mainMemory);
        Cache l1Cache = new Cache("Cache L1", 10, 4, 32, 32, "Write-through", l2Cache);

        // Configurando o processador
        Processor processor = new Processor(l1Cache, 256, 4, 10);

        // Executando padrão de acesso sequencial
        System.out.println("Execução com padrão sequencial:");
        processor.runSequentialAccess();

        // Executando padrão de acesso aleatório
        System.out.println("\nExecução com padrão aleatório:");
        processor.runRandomAccess();
    }
}
