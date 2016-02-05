package ch11;

import java.util.BitSet;

public class UsageBitSet {
    public static void main(String[] args) {
        BitSet bitSet = new BitSet();
        long[] array = {1L, 21L, 3L};
        bitSet = BitSet.valueOf(array);
        System.out.println(bitSet);

        long[] tmp = bitSet.toLongArray();
        for (long number: tmp) {
            System.out.println(number);
        }

        System.out.println(bitSet.previousSetBit(1));
        System.out.println(bitSet.previousClearBit(66));
    }
}
