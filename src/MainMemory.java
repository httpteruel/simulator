public class MainMemory extends Memory {
    Information info = new Information();
    public MainMemory(String name, int latency) {
        super(name, latency);
    }
    @Override
    public int access(int address, boolean isWrite) {
        if(isWrite){
            info.write();
            // System.out.println("escrita " + getName());
            // System.out.println("vzs escruita prinn "+info.getTimesWritten());
            return latency;
        }
        else{
            // System.out.println("Acessando:" + name);
            info.read();
            return latency;
        }
    }
    @Override
    public void showEstatistics(){
        System.out.println("----------------------------------------------------");
        System.out.println("Stats of "+ name);
        System.out.println(name + " read Accesses "+ info.getTimesRead());
        System.out.println(name + " write Acesses "+ info.getTimesWritten());
        System.out.println("----------------------------------------------------");
        info.reset();
    }
}