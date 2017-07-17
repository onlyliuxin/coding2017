package work02.ocp;

public class Logger {
	
	public final int RAW_LOG = 1;
	public final int RAW_LOG_WITH_DATE = 2;
	public final int EMAIL_LOG = 1;
	public final int SMS_LOG = 2;
	public final int PRINT_LOG = 3;
	
	int type = 0;
	int method = 0;
			
	public Logger(int logType, int logMethod){
		this.type = logType;
		this.method = logMethod;		
	}

	Sender sender;
	Formatter formatter;

	public void log(String msg){

		String logMsg = formatter.formatMsg(msg);
		sender.send(logMsg);

	}
}

