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
        System.out.println();
    }

    public static void main(String[] args) {

        int v;
        int tamanhoDalinha = 64;
        int sets = 32;
        int bufferSize = 20000;
        int numBitsOffset = (int)(Math.log(tamanhoDalinha) / Math.log(2));
        int numBitsIndice = (int)(Math.log(sets) / Math.log(2));
        int numBitsTotal = (int)(Math.log(bufferSize) / Math.log(2));
        System.out.println("bits o "+numBitsOffset+" bits i "+numBitsIndice+" bits total "+numBitsTotal);
        if((Math.log(tamanhoDalinha) / Math.log(2)) %1 !=0) numBitsOffset ++;
        if((Math.log(sets) / Math.log(2)) %1 !=0) numBitsIndice ++;
        if((Math.log(bufferSize) / Math.log(2)) %1 !=0) numBitsTotal ++;
        int numBitsTag = numBitsTotal - numBitsIndice - numBitsOffset;
        int inicioTag = numBitsIndice+numBitsOffset;
        System.out.println("bits o "+numBitsOffset+" bits i "+numBitsIndice+" bits tag "+numBitsTag+" bits total "+numBitsTotal);

        // int g = 10;
        // print_bits(g);
        v = 0xFFA3F12F;
        print_bits(v);
        print_bits(extract_bits(v, 0, 31));
        int offset = extract_bits(v, 0, numBitsOffset);
        int indice = extract_bits(v, numBitsOffset, numBitsIndice);
        int tag = extract_bits(v, inicioTag, numBitsTag);
        // int a = extract_bits(v, 9, 6);
        // int c = extract_bits(v, 6, 3);
        // int d = extract_bits(v, 3, 3);
        // int e = extract_bits(v, 0, 3);
        System.out.println("offset "+ numBitsOffset);
        print_bits(offset);
        System.out.println("indice "+ numBitsIndice);
        print_bits(indice);
        System.out.println("tag "+numBitsTag);
        print_bits(tag);
        // System.out.println("e");
        // print_bits(e);
        // System.out.println("a "+a);
        // System.out.println("c" + c);
        // int b = a - 1;
        // print_bits(a);
        // print_bits(b);
        

        // v = set_bits(v, 31, 1, 0);
        // print_bits(v);
        // System.out.println();

        v = set_bits(v, 0, 31, 6);
        print_bits(v);
        System.out.println();

        // int v = 809;

        // v = 810;
        // print_bits(v);
        // System.out.println();

        // v = 910;
        // print_bits(extract_bits(v, 0,23 ));
        // System.out.println();

        // v = set_bits(v, 31, 1, 0);
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