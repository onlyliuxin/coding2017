package bridge;

public class GraphicLibrary2 implements GraphicLibrary {

    @Override
    public void drawRectangle(int x1, int y1, int x2, int y2) {
        drawLine(x1, x2, y1, y2);
        drawLine(x1, x2, y2, y2);
        drawLine(x2, x2, y2, y1);
        drawLine(x2, x1, y1, y1);
    }

    @Override
	public void drawCircle(int x,int y, int r) {
		
	}

	private void drawLine(int x1, int x2, int y1, int y2) {
	}

}
