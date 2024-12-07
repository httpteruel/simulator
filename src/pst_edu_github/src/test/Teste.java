package pst_edu_github.src.test;

public class Teste {
    public static void main(String[] args) {
        MainMemory mainMemory = new MainMemory("Memória Principal", 100);


        Cache l2Cache = new Cache("Cache L2", 20, 4, 64, 32, false, mainMemory);
        Cache l1Cache = new Cache("Cache L1", 10, 4, 32, 32, true, l2Cache);
    }
}
