package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;

public class MainWindowController {
	@FXML
    private JFXButton sub_back;

    @FXML
    private Label sub_lbl;

    @FXML
    private JFXButton sub_reload;

    @FXML
    private JFXListView<Label> sub_selection;

    @FXML
    private JFXListView<Label> sub_content;

    @FXML
    private JFXButton sub_upup;

    @FXML
    private JFXButton sub_up;

    @FXML
    private JFXButton sub_down;

    @FXML
    private JFXButton sub_downdown;

    @FXML
    void backBtnClicked(ActionEvent event) {

    }

    @FXML
    void downBtnClicked(ActionEvent event) {

    }

    @FXML
    void downdownBtnClicked(ActionEvent event) {

    }

    @FXML
    void reloadBtnClicked(ActionEvent event) {
    	initializeListViewContent();
    }

    @FXML
    void upBtnClicked(ActionEvent event) {

    }

    @FXML
    void upupBtnClicked(ActionEvent event) {

    }

    @FXML
    void selection_on_d_detected(MouseEvent event) {
    	System.out.println("selection_on_d_detected");
    }

    @FXML
    void selection_on_d_done(DragEvent event) {
    	System.out.println("selection_on_d_done");
    }

    @FXML
    void selection_on_d_dropped(DragEvent event) {
    	System.out.println("selection_on_d_dropped");
    }

    @FXML
    void selection_on_m_d_release(MouseDragEvent event) {
    	System.out.println("selection_on_m_d_release");
    }

    @FXML
    void content_on_d_detected(MouseEvent event) {
    	System.out.println("content_on_d_detected");
    }

    @FXML
    void content_on_d_done(DragEvent event) {
    	System.out.println("content_on_d_done");
    }

    @FXML
    void content_on_d_dropped(DragEvent event) {
    	System.out.println("content_on_d_dropped");
    }

    @FXML
    void content_on_d_entered(DragEvent event) {
    	System.out.println("content_on_d_entered");
    }

    @FXML
    void content_on_d_exited(DragEvent event) {
    	System.out.println("content_on_d_exited");
    }

    @FXML
    void content_on_d_over(DragEvent event) {
    	System.out.println("content_on_d_over");
    }

    @FXML
    void content_on_m_d_entered(MouseDragEvent event) {
    	System.out.println("content_on_m_d_entered");
    }

    @FXML
    void content_on_m_d_exited(MouseDragEvent event) {
    	System.out.println("content_on_m_d_exited");
    }

    @FXML
    void content_on_m_d_over(MouseDragEvent event) {
    	System.out.println("content_on_m_d_over");
    }

    @FXML
    void content_on_m_d_release(MouseDragEvent event) {
    	System.out.println("content_on_m_d_release");
    }


	private Main main;

	public void setMain(Main main) {
		this.main = main;
	}

	public void initializeListView() {
		for (int i = 0; i < 7; i++) {
			Label lbl = new Label("Item "+i);
			try {
				lbl.setGraphic(new ImageView(new Image(new FileInputStream(Main.class.getResource("up-arrow.png").getFile()))));
				sub_selection.getItems().add(lbl);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public void initializeListViewContent() {
		for (int i = 0; i < 2; i++) {
			Label lbl = new Label("Content? "+i);
			try {
				lbl.setGraphic(new ImageView(new Image(new FileInputStream(Main.class.getResource("up-up-arrow.png").getFile()))));
				sub_content.getItems().add(lbl);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
