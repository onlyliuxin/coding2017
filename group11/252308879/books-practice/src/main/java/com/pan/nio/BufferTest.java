package com.pan.nio;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * Created by Pan on 2017/6/4.
 */
public class BufferTest {

    public static void main(String[] args) {

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // =========== buffer 初始化值 ========
        System.out.println(buffer.capacity());
        System.out.println(buffer.limit());
        System.out.println(buffer.position());


    }


}
