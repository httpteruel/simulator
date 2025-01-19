public class MainMemory extends Memory {
    Information info = new Information();

    public MainMemory(String name, int latency) {
        super(name, latency);
    }

    @Override
    public int access(Integer address, boolean isWrite) {
        if(isWrite) {
            info.write();
            return latency;
        }
        else {
            info.read();
            return latency;
        }
    }

    @Override
    public void showEstatistics() {
        System.out.println("----------------------------------------------------");
        System.out.println("Stats of "+ name);
        System.out.println(name + " read Accesses "+ info.getTimesRead());
        System.out.println(name + " write Acesses "+ info.getTimesWritten());
        System.out.println("----------------------------------------------------");
        info.reset();
    }
}