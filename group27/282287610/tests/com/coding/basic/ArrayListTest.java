package com.coding.basic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {

    @Test
    void arrayListTest() {
        ArrayList myArrayList = new ArrayList();
        myArrayList.add(1);
        assertEquals(2,myArrayList.get(0));
    }



}