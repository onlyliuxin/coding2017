package me.lzb.boom;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author LZB
 */
public class BoomTest {

    private MemoryBoom boom;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void instantiate() throws Exception {
        boom = new MemoryBoom();
    }

    @Test
    public void metaspaceBoomTest() {
        boom.metaspaceBoom();
    }

    @Test
    public void outOfMemoryBoomTest() {
        thrown.expect(OutOfMemoryError.class);
        thrown.expectMessage("Java heap space");
        boom.outOfMemoryBoom();
    }


    @Test
    public void stackOverflowBoomTest() {
        thrown.expect(StackOverflowError.class);
        boom.stackOverflowBoom();
    }
}
