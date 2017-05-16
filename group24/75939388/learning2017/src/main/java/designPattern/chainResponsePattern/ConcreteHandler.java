package designPattern.chainResponsePattern;

/**
 * @author : 温友朝
 * @date : 2017/5/10
 */
public class ConcreteHandler extends Handler {
    public void doHandler() {
        if(getNextHandler() != null){
            System.out.println("还有责任链");
            getNextHandler().doHandler();
        }else{
            System.out.println("自己处理");
        }
    }
}
