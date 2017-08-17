#Singleton in JDK

## java.lang.Runtime
    
    private static Runtime currentRuntime = new Runtime();
 
    public static Runtime getRuntime() {
        return currentRuntime;
    }
    
## java.awt.Desktop
    
    private DesktopPeer peer;

    private Desktop() {
        peer = Toolkit.getDefaultToolkit().createDesktopPeer(this);
    }
    
    
    public static synchronized Desktop getDesktop(){
        Desktop desktop = (Desktop)context.get(Desktop.class);
        
        if (desktop == null) {
            desktop = new Desktop();
            context.put(Desktop.class, desktop);
        }
        
        return desktop;
    }
    
## java.lang.System

    private static volatile SecurityManager security = null;
    
    public static SecurityManager getSecurityManager() {
        return security;
    }
   