package net.mirwaldt.logic.propositional.util;

import static net.mirwaldt.logic.propositional.util.PropositionUtils.fromBit;

public class BitUtils {
    public static long encode(long bits, long bitIndex, int bit) {
        if (bitIndex < 0 || 62 < bitIndex) {
            throw new IllegalArgumentException(
                    "Bit index must lie between [0, 62] and cannot be " + bitIndex);
        } else {
            if(fromBit(bit)) {
                return bits | 1 << bitIndex;
            } else {
                return bits & ~(1 << bitIndex);
            }
        }
    }

    public static int decode(long bits, long bitIndex) {
        if (bitIndex < 0 || 62 < bitIndex) {
            throw new IllegalArgumentException(
                    "Bit index must lie between [0, 62] and cannot be " + bitIndex);
        } else {
            long bitMask = 1L << bitIndex;
            return (int) ((bits & bitMask) >> bitIndex);
        }
    }
    
    public static long reverseBits(long bits, int maxNumberOfBits) {
        long result = bits;
        for (int bitIndex = 0; bitIndex < maxNumberOfBits / 2; bitIndex++) {
            int mirrorBitIndex = maxNumberOfBits - bitIndex - 1;
            int indexBit = decode(bits, bitIndex);
            int mirrorBit = decode(bits, mirrorBitIndex);
            result = encode(result, bitIndex, 0);
            result = encode(result, mirrorBitIndex, 0);
            result = encode(result, mirrorBitIndex, indexBit);
            result = encode(result, bitIndex, mirrorBit);
        }
        return result;
    }
}
