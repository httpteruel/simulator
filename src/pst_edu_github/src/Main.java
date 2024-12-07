package pst_edu_github.src;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import static java.lang.Math.log;
public class Main {
    public static int extract_bits (int source, int bstart, int blength)
    {
        int mask = (1 << blength) - 1;
        return ((source >> bstart) & mask);
    }

    public static int set_bits (int source, int bstart, int blength, int value)
    {
        int mask = (1 << blength) - 1;
	    int shifted_mask = mask << bstart;
	    int safe_value = value & mask;

        return (source & ~shifted_mask) | (safe_value << bstart);
    }

    public static void print_bits (int value)
    {
        for (int i = 31; i >= 0; i--) {
            System.out.print((value >> i) & 0x01);
        }
    }

    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        int v;

        v = 810;
        print_bits(v);
        System.out.println();

        // v = 910;
        // print_bits(extract_bits(v, 0,15));
        // System.out.println();

 

        // v = set_bits(v, 22, 22, 809);
        // print_bits(v);

        // int num_bits_Offset = (int) (log(64)/log(2));
        // int num_bits_indice = (int) (log(8/log(2)));
        // int num_bits_tag = (int) 32 - num_bits_Offset - num_bits_indice; 
        // int indice = 810 % 8;	
        // int tag = (810 >> (byte) num_bits_tag); 
        // print_bits(tag);
        // v = set_bits(v, 31, 1, 810);
        // print_bits(v);
        // System.out.println();
        // System.out.println();
    }
}