package com.coderising.mydp.composite;

import org.junit.Test;

/**
 * Created by thomas_young on 25/7/2017.
 */
public class CompositeTest {

    @Test
    public void testComposite() {
        Picture aPicture = new Picture();
        aPicture.add(new Line());
        aPicture.add(new Rectangle());

        Picture p = new Picture();
        p.add(new Text());
        p.add(new Line());
        p.add(new Square());

        aPicture.add(p);
        aPicture.add(new Picture());
        aPicture.add(new Line());
        aPicture.draw();
    }
}
