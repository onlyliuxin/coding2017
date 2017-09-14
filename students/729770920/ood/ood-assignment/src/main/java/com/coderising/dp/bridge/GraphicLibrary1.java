package bridge;

public class GraphicLibrary1 implements GraphicLibrary {

    @Override
    public void drawRectangle(int x1, int y1, int x2, int y2) {
        draw_a_line(x1, y1, x1, y2);
        draw_a_line(x1, y2, x2, y2);
        draw_a_line(x2, y2, x2, y1);
        draw_a_line(x2, y1, x1, y1);
    }

    @Override
    public void drawCircle(int x, int y, int r) {
        draw_a_circle(x, y, r);
    }

	private void draw_a_line(int x1, int y1, int x2, int y2) {
		
	}

	private void draw_a_circle(int x, int y, int r) {
		
	}

}
