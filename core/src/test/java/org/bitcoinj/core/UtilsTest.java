/**
 * Copyright 2011 Thilo Planz
 * Copyright 2014 Andreas Schildbach
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.bitcoinj.core;

import java.math.BigInteger;
import java.util.Date;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilsTest {

    @Test
    public void testReverseBytes() {
        assertArrayEquals(new byte[]{1, 2, 3, 4, 5}, Utils.reverseBytes(new byte[]{5, 4, 3, 2, 1}));
    }

    @Test
    public void testReverseDwordBytes() {
        assertArrayEquals(new byte[]{1, 2, 3, 4, 5, 6, 7, 8}, Utils.reverseDwordBytes(new byte[]{4, 3, 2, 1, 8, 7, 6, 5}, -1));
        assertArrayEquals(new byte[]{1, 2, 3, 4}, Utils.reverseDwordBytes(new byte[]{4, 3, 2, 1, 8, 7, 6, 5}, 4));
        assertArrayEquals(new byte[0], Utils.reverseDwordBytes(new byte[]{4, 3, 2, 1, 8, 7, 6, 5}, 0));
        assertArrayEquals(new byte[0], Utils.reverseDwordBytes(new byte[0], 0));
    }

    @Test
    public void testMaxOfMostFreq() throws Exception {
        assertEquals(0, Utils.maxOfMostFreq());
        assertEquals(0, Utils.maxOfMostFreq(0, 0, 1));
        assertEquals(2, Utils.maxOfMostFreq(1, 1, 2, 2));
        assertEquals(1, Utils.maxOfMostFreq(1, 1, 2, 2, 1));
        assertEquals(-1, Utils.maxOfMostFreq(-1, -1, 2, 2, -1));
    }

    @Test
    public void compactEncoding() throws Exception {
        assertEquals(new BigInteger("1234560000", 16), Utils.decodeCompactBits(0x05123456L));
        assertEquals(new BigInteger("c0de000000", 16), Utils.decodeCompactBits(0x0600c0de));
        assertEquals(0x05123456L, Utils.encodeCompactBits(new BigInteger("1234560000", 16)));
        assertEquals(0x0600c0deL, Utils.encodeCompactBits(new BigInteger("c0de000000", 16)));
    }

    @Test
    public void dateTimeFormat() {
        assertEquals("2014-11-16T10:54:33Z", Utils.dateTimeFormat(1416135273781L));
        assertEquals("2014-11-16T10:54:33Z", Utils.dateTimeFormat(new Date(1416135273781L)));
    }
   // NIMBLECOIN
   @Test
    public void testMPI() throws Exception {
    	byte[] mpi = {0, 0, 0, 29, -1, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    	BigInteger value = Utils.decodeMPI(mpi, true);
    	byte[] mpi2 = Utils.encodeMPI(value, true);
        assertArrayEquals(mpi, mpi2);
    	
        value = BigInteger.valueOf(-1);
    	mpi = Utils.encodeMPI(value, true);
    	BigInteger value2 = Utils.decodeMPI(mpi, true);
        assertEquals(value, value2);

        value = BigInteger.valueOf(-128);
    	mpi = Utils.encodeMPI(value, true);
    	value2 = Utils.decodeMPI(mpi, true);
        assertEquals(value, value2);

    }    
   // NIMBLECOIN
    @Test
    public void testCompactBits() throws Exception {
    	long value = 0x1dffffffl;
        assertEquals(value, Utils.encodeCompactBits(Utils.decodeCompactBits(value)));
    	value = 0x1fffffffl;
        assertEquals(value, Utils.encodeCompactBits(Utils.decodeCompactBits(value)));
    	value = 0x8fffffffl;
        assertEquals(value, Utils.encodeCompactBits(Utils.decodeCompactBits(value)));
    }
    // NIMBLECOIN
    @Test
    public void testIsPowerOf2() throws Exception {
        assertEquals(true, Utils.isPowerOf2(1));
        assertEquals(true, Utils.isPowerOf2(2));
        assertEquals(true, Utils.isPowerOf2(4));
        assertEquals(true, Utils.isPowerOf2(8));
        assertEquals(true, Utils.isPowerOf2(16));
        assertEquals(true, Utils.isPowerOf2(32));
        assertEquals(false, Utils.isPowerOf2(5));
        assertEquals(false, Utils.isPowerOf2(24));
    }
}
