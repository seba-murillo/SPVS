import model.Game;
import org.assertj.swing.core.ComponentFinder;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.finder.JOptionPaneFinder;
import org.assertj.swing.fixture.*;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.SPVSutils;
import view.InputFrame;
import view.Screen;

import javax.swing.*;
import java.awt.*;

import static org.assertj.swing.finder.WindowFinder.findDialog;
import static org.assertj.swing.launcher.ApplicationLauncher.application;
import static org.assertj.swing.finder.WindowFinder.findFrame;

public class SystemTest extends AssertJSwingJUnitTestCase{
	private FrameFixture window;
	private JPanelFixture game_panel;
	private JButtonFixture button_prev;
	private JButtonFixture button_play;
	private JButtonFixture button_next;


	@Override
	protected void onSetUp() {
		InputFrame frame = GuiActionRunner.execute(() -> new InputFrame());
		window = new FrameFixture(robot(), frame);
		window.show();
	}

	private void addEntitiy(int type, int x, int y){
		// cell_%dx%d
		JPanelFixture cell = game_panel.panel(String.format("cell_%dx%d", x, y));
		cell.rightClick();
	}

	@BeforeEach
	void setup() {
		InputFrame frame = GuiActionRunner.execute(InputFrame::new);
		//window = new FrameFixture(frame);
		window = new FrameFixture(frame);
		window.show(); // shows the frame to test
	}

	@Test
	void full_system_test(){
		SPVSutils.log("Starting test...");
		window.textBox("field_width").enterText("10");
		window.textBox("field_height").enterText("10");
		window.textBox("field_duration").enterText("10");
		window.button("button_start").click();
		SPVSutils.log("start buttong clicked!");
		delay(1);
		game_panel = window.panel("panel_game");
		button_prev = window.panel("panel_control").button("btn_prev");
		button_play = window.panel("panel_control").button("btn_play");
		button_next = window.panel("panel_control").button("btn_next");
		SPVSutils.log("game_panel: " + game_panel);
		SPVSutils.log("button_prev: " + button_prev);
		SPVSutils.log("button_play: " + button_play);
		SPVSutils.log("button_next: " + button_next);
		addEntitiy(0, 1, 1);
	}

	void delay(double sec){
		try{
			Thread.sleep((long) (1000 * sec));
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

}
