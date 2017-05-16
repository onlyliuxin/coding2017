package teePrivate.designPattern.chainResponsePattern;

/**
 * @author : 温友朝
 * @date : 2017/5/10
 */
public abstract class ConsumeHandler {
    private ConsumeHandler nextHandler;

    public ConsumeHandler getNextHandler() {
        return nextHandler;
    }

    public void setNextHandler(ConsumeHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    /** user申请人 free报销费用 */
    public abstract void doHandler(String user, double free);

    public static void main(String[] args){
//        ConcreteHandler handler1 = new ConcreteHandler();
//        ConcreteHandler handler2 = new ConcreteHandler();
//        handler1.setNextHandler(handler2);
//        handler1.doHandler();

        ProjectHandler projectHandler =new ProjectHandler();
        DeptHandler deptHandler =new DeptHandler();
        GeneralHandler generalHandler =new GeneralHandler();
        projectHandler.setNextHandler(deptHandler);
        deptHandler.setNextHandler(generalHandler);

//        projectHandler.doHandler("lwx", 450);
//        projectHandler.doHandler("lwx", 600);
//        projectHandler.doHandler("zy", 600);
        projectHandler.doHandler("zy", 1500);
//        projectHandler.doHandler("lwxzy", 1500);
    }
}

//项目经理
class ProjectHandler extends ConsumeHandler {

    @Override
    public void doHandler(String user, double free) {
        if (free < 500) {
            System.out.println("lwx给予报销:" + free);
        } else if (getNextHandler() != null) {
            getNextHandler().doHandler(user, free);
        }
     }
}
//部门经理
class DeptHandler extends ConsumeHandler {

     @Override
     public void doHandler(String user, double free) {
         System.out.println("getNextHandler -> " + getNextHandler() == null);
         if (free < 1000) {
             System.out.println("zy给予报销:" + free);
         } else if (getNextHandler() != null) {
             getNextHandler().doHandler(user, free);
         }
     }
 }
 //总经理
class GeneralHandler extends ConsumeHandler {

     @Override
     public void doHandler(String user, double free) {
         if (free >=1000) {
             System.out.println("lwxzy给予报销:" + free);
         } else if (getNextHandler() != null) {
             getNextHandler().doHandler(user, free);
         }
     }
}
