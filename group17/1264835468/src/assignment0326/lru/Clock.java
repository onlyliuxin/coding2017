package assignment0326.lru;

/**
 * Created by Administrator on 2017/3/29.
 */
public class Clock {
    private PageFrame[] pages;
    private int capacity;
    private int accessPtr;
    public Clock(int capacity) {
        this.capacity = capacity;
        pages = new PageFrame[capacity];
        accessPtr=0;
    }

    private void access(int pageNum){
        if (pages[accessPtr] == null) {
            pages[accessPtr] = new PageFrame(pageNum);
        }else{
            int indexToReplace=findLRUIndex();
            pages[indexToReplace].pageNum=pageNum;
        }
        accessPtr = (accessPtr + 1) % capacity;
    }

    private int findLRUIndex() {
        while(pages[accessPtr].shouldGiveAnotherChance()){
            accessPtr = (accessPtr + 1) % capacity;
        }
        return accessPtr;
    }

    private static class PageFrame{
        int pageNum;
        boolean accessFlag;

        public PageFrame(int pageNum) {
            this.pageNum = pageNum;
            accessFlag=true;
        }

        public boolean shouldGiveAnotherChance() {
            if(accessFlag){
                accessFlag=false;
                return true;
            }
            return false;
        }
    }
}
