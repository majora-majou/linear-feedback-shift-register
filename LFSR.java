import java.util.Arrays;

public class LFSR {

    private static byte[] b = "apple".getBytes();
    private static long n = 0x12345678;

    static byte[] Crypt(byte[] data, long initialValue) {

        int lsb;
        int tap, tap1, tap2, tap3, tap4, tap5, tap6, tap7, tap8, tap9, tap10, tap11;
        long maxLongVal = 0x80000000; // 100000000000....
        long feedback = 0x87654321;

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < 8; j++) {
                lsb = (int) initialValue & 1; // Holds the least significant bit.
                tap = (int) (initialValue >> 31) & 1;
                tap1 = (int) (initialValue >> 26) & 1;
                tap2 = (int) (initialValue >> 25) & 1;
                tap3 = (int) (initialValue >> 24) & 1;
                tap4 = (int) (initialValue >> 22) & 1;
                tap5 = (int) (initialValue >> 21) & 1;
                tap6 = (int) (initialValue >> 18) & 1;
                tap7 = (int) (initialValue >> 16) & 1;
                tap8 = (int) (initialValue >> 14) & 1;
                tap9 = (int) (initialValue >> 9) & 1;
                tap10 = (int) (initialValue >> 8) & 1;
                tap11 = (int) (initialValue >> 5) & 1;
                int msb = lsb ^ tap ^ tap1 ^ tap2 ^ tap3 ^ tap4 ^ tap5 ^ tap6 ^ tap7 ^ tap8 ^ tap9 ^ tap10 ^ tap11;
                initialValue >>= 1;
                if (msb == 1) {
                    initialValue |= maxLongVal;
                }
                if (lsb == 1) {
                    initialValue ^= feedback;
                }
            }
            data[i] ^= initialValue & 255; // Saves the least significant byte after each eigth step.
        }
        return data;
    }
    public static void main(String[] args) {
        System.out.println(Arrays.toString(Crypt(b, n)));
        StringBuilder sb = new StringBuilder();
        for (byte c : b) {
            sb.append(String.format("%02X ", c));
        }
        System.out.println(sb.toString());
    }
}