package net.mirwaldt.logic.propositional.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BitUtilsTest {
    @Test
    void test_encodeBit_wrongBit() {
        assertThrows(IllegalArgumentException.class, () -> BitUtils.encode(0, -1, 1));
        assertThrows(IllegalArgumentException.class, () -> BitUtils.encode(0, 63, 1));
    }
    
    @Test
    void test_encodeBit_setBit() {
        assertEquals(0x00_00_00_00_00_00_00_01L, BitUtils.encode(0, 0, 1));
        assertEquals(0x00_00_00_00_00_00_00_02L, BitUtils.encode(0, 1, 1));
        assertEquals(0x00_00_00_00_00_00_00_02L, BitUtils.encode(2, 1, 1));
        assertEquals(0x00_00_00_00_00_00_00_03L, BitUtils.encode(1, 1, 1));
    }

    @Test
    void test_encodeBit_resetBit() {
        assertEquals(0x00_00_00_00_00_00_00_00L, BitUtils.encode(0, 0, 0));
        assertEquals(0x00_00_00_00_00_00_00_00L, BitUtils.encode(1, 0, 0));
        assertEquals(0x00_00_00_00_00_00_00_00L, BitUtils.encode(2, 1, 0));
        assertEquals(0x00_00_00_00_00_00_00_01L, BitUtils.encode(3, 1, 0));
    }

    @Test
    void test_decodeBit_wrongBit() {
        assertThrows(IllegalArgumentException.class, () -> BitUtils.decode(0, -1));
        assertThrows(IllegalArgumentException.class, () -> BitUtils.decode(0, 63));
    }

    @Test
    void test_decodeBit() {
        assertEquals(0, BitUtils.decode(0x00_00_00_00_00_00_00_00L, 0));
        assertEquals(1, BitUtils.decode(0x00_00_00_00_00_00_00_01L, 0));
        assertEquals(0, BitUtils.decode(0x00_00_00_00_00_00_00_01L, 1));
        assertEquals(1, BitUtils.decode(0x00_00_00_00_00_00_00_02L, 1));
        assertEquals(1, BitUtils.decode(0x00_00_00_00_00_00_00_03L, 1));
    }

    @Test
    void test_reverse_4bits() {
        assertEquals(0x00_00_00_00_00_00_00_00L, BitUtils.reverse(0x00_00_00_00_00_00_00_00L, 4));
        assertEquals(0x00_00_00_00_00_00_00_08L, BitUtils.reverse(0x00_00_00_00_00_00_00_01L, 4));
        assertEquals(0x00_00_00_00_00_00_00_04L, BitUtils.reverse(0x00_00_00_00_00_00_00_02L, 4));
        assertEquals(0x00_00_00_00_00_00_00_0CL, BitUtils.reverse(0x00_00_00_00_00_00_00_03L, 4));

        assertEquals(0x00_00_00_00_00_00_00_01L, BitUtils.reverse(0x00_00_00_00_00_00_00_08L, 4));
        assertEquals(0x00_00_00_00_00_00_00_02L, BitUtils.reverse(0x00_00_00_00_00_00_00_04L, 4));
        assertEquals(0x00_00_00_00_00_00_00_03L, BitUtils.reverse(0x00_00_00_00_00_00_00_0CL, 4));
    }
}
