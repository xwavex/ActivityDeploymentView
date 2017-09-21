package application;


import java.util.Random;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXMasonryPane;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class CoreController {

	@FXML
	private JFXButton core_reload;

	@FXML
	private GridPane core_parentgrid;

	private Main main;

	public void setMain(Main main) {
		this.main = main;
		initializeParentGridPane(4);
	}

	public void initializeParentGridPane(int cores) {
		int dimY = cores / (int) Math.sqrt(cores);
		int dimX = 0;
		// Calc only dimensions
		for (int i = 0; i < cores; i++) {
			int x = 0;
			int y = 0;
			if ((i % dimY) > 0) {
				x = i / dimY;
				y = i % dimY;

			} else {
				x = i / dimY;
				y = 0;
			}
			if (x > dimX) {
				dimX = x;
			}
		}
		dimX++;

		for (int i = 0; i < dimX; i++) {
			ColumnConstraints tmpC = new ColumnConstraints();
			tmpC.setHgrow(Priority.ALWAYS);
			tmpC.setPercentWidth(100 / dimX);
			core_parentgrid.getColumnConstraints().add(tmpC);
		}
		for (int j = 0; j < dimY; j++) {
			RowConstraints tmpR = new RowConstraints();
			tmpR.setVgrow(Priority.ALWAYS);
			tmpR.setPercentHeight(100 / dimY);
			core_parentgrid.getRowConstraints().add(tmpR);
		}

		for (int i = 0; i < cores; i++) {
			int x = 0;
			int y = 0;
//			Label lbl = new Label("asdC " + i);


			if ((i % dimY) > 0) {
				x = i / dimY;
				y = i % dimY;

			} else {
				x = i / dimY;
				y = 0;
			}
//			GridPane.setConstraints(lbl, x, y);// column/row
//			core_parentgrid.getChildren().add(lbl);

			JFXMasonryPane internalMasonry1 = new JFXMasonryPane();
			internalMasonry1.setStyle("-fx-background-color: green;");
			internalMasonry1.setCellWidth(10);
			internalMasonry1.setCellHeight(10);

			Random r = new Random();
			for (int k = 0; k < 5; k++) {
				//dummy
				Label lbl = new Label("lll " + k);
//				lbl.setPrefSize(20, 20);
				lbl.setStyle("-fx-background-color:rgb(" + r.nextInt(255) + "," + r.nextInt(255) + "," + r.nextInt(255) + ");");
				internalMasonry1.getChildren().add(lbl);
			}
			GridPane.setConstraints(internalMasonry1, x, y);// column/row
			core_parentgrid.getChildren().add(internalMasonry1);
		}
		System.out.println("Rows " + core_parentgrid.getRowConstraints().size());
		System.out.println("Cols " + core_parentgrid.getColumnConstraints().size());



	}

}