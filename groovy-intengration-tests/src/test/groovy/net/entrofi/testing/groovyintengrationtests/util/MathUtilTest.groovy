package net.entrofi.testing.groovyintengrationtests.util

import org.junit.Test

import static org.junit.Assert.assertEquals

class MathUtilTest {


    @Test
    void "sum of 2 and 3 should return 5"() {
        int result = 5;
        assertEquals(5, MathUtil.sum(2, 3))
    }
}
