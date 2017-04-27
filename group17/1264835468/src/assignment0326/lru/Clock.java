package assignment0326.lru;

import java.util.*;
import java.util.stream.Collectors;

/**
 *  Created by Administrator on 2017/3/29.
 */
public class Clock {
    private PageFrame[] pages;
    private int capacity;
    private int accessPtr;
    private int size;
    public Clock(int capacity) {
        this.capacity = capacity;
        pages = new PageFrame[capacity];
        accessPtr = 0;
        size=0;
    }

    private void access(int pageNum) {
        int index = search(pageNum);
        if(index==-1){
            if(size<capacity){
                add(new PageFrame(pageNum));
            }else{
                pages[findLRUIndex()].pageNum=pageNum;
            }
        }else {
            pages[findLRUIndex()].accessFlag=true;
        }
        accessPtr = (accessPtr + 1) % capacity;
    }

    private int search(int pageNum) {
        for (int i = 0; i <size; i++) {
            if(pages[i].pageNum==pageNum)
                return i;
        }
        return -1;
    }

    private void add(PageFrame pageFrame) {
        pages[size++]=pageFrame;
    }

    private int findLRUIndex() {
        while (pages[accessPtr].shouldGiveAnotherChance()) {
            accessPtr = (accessPtr + 1) % capacity;
        }
        return accessPtr;
    }

    private static class PageFrame {
        int pageNum;
        boolean accessFlag;

        public PageFrame(int pageNum) {
            this.pageNum = pageNum;
            accessFlag = true;
        }

        public boolean shouldGiveAnotherChance() {
            if (accessFlag) {
                accessFlag = false;
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args) {

        List<String> strings=new ArrayList<>();
        for (int i = 0; i <10; i++) {
            strings.add(String.valueOf(new Random().nextInt(3000)));
        }
        System.out.println(strings);
        System.out.println(strings.stream().map(s-> s.charAt(0)).sorted().distinct().limit(5).collect(Collectors.toList()));
    }

    public int findMinDifference(List<String> timePoints) {
        List<Integer> list=new ArrayList<>();

        for (String s:timePoints) {
            list.add(parse(s));
        }
        Collections.sort(list);
        int min=Integer.MAX_VALUE;
        for (int i = 0; i < list.size()-1; i++) {
            min=Math.min(min,Math.min(Math.abs(list.get(i+1)-list.get(i)),24*60-Math.abs(list.get(i+1)-list.get(i))));
        }
        return min;
    }

    private Integer parse(String s) {
        return Integer.parseInt(s.substring(0, 2)) * 60 + Integer.parseInt(s.substring(3, 5));
    }
}
