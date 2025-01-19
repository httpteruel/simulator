import java.time.LocalTime;

public class Linha {
    int tag;
    String status = "Inválido";
    LocalTime lastTimeUsed = LocalTime.now();

    public void usage(){
        lastTimeUsed = LocalTime.now();
    }  

    public static int compareLastUsedLine(Linha... lines) {
        LocalTime oldest = lines[0].lastTimeUsed;
        int lineNumber = 0;
        for (int i = 0; i<lines.length;i++) {
            if(lines[i].lastTimeUsed.isBefore(oldest)) {
                oldest = lines[i].lastTimeUsed;
                lineNumber = i;
            }
        }
        return lineNumber;
    }

    public void update(int tag, boolean leitura, boolean writeThrough) {
        this.tag = tag;
        usage();
        if(leitura || writeThrough) status = "Válido";
        else status = "Sujo";
    }

    public void print() {
        System.out.println(this.tag);
        System.out.println(this.status);
        System.out.println(this.lastTimeUsed);
    }

}
