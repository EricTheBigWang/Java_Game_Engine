package page;

import userGUI.Page;
import userGUI.text.Text;

import java.awt.*;

public class MyPage extends Page {
    private static int tick;
    private static int frame;

    private static Text text;

    public MyPage() {
        text = new Text("HELLO WORLD!", 0,0, "center");
    }

    @Override
    public void reset() {
        tick = 0;
        frame = 0;
    }

    public void update() {
        super.update();
        tick++;
        if (tick % 5 == 0)
            frame ++;
        tick %= 5;
        frame %= 8;
    }

    public void render(Graphics g) {
        super.render(g);
        text.render(g);
    }
}
