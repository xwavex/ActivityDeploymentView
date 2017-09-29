package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXMasonryPane;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

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
		core_parentgrid.setHgap(5);
		core_parentgrid.setVgap(5);

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
			// Label lbl = new Label("asdC " + i);

			if ((i % dimY) > 0) {
				x = i / dimY;
				y = i % dimY;

			} else {
				x = i / dimY;
				y = 0;
			}
			// GridPane.setConstraints(lbl, x, y);// column/row
			// core_parentgrid.getChildren().add(lbl);

			JFXMasonryPane internalMasonry1 = createMasonryPane();
			Random r = new Random();
			for (int k = 0; k < 4; k++) {
				// dummy
				Label lbl = new Label("vcbcbvcbcbcbcbvvbc " + k);
				lbl.setTooltip(new Tooltip("" + lbl.getText()));
				lbl.setPrefSize(100, 70);
				lbl.setAlignment(Pos.BOTTOM_CENTER);
				lbl.getStyleClass().add("activityLbl");
				internalMasonry1.getChildren().add(lbl);
			}
			internalMasonry1.setStyle("-fx-background: transparent; -fx-background-color: #00FA0000;");

			StackPane stp = new StackPane();

			Label lbl_coreIndex = new Label("C" + (i+1));
			lbl_coreIndex.getStyleClass().add("bkgrndCoreLbl");
			lbl_coreIndex.setFont(Font.font("Cambria", 144));

			ScrollPane sp1 = new ScrollPane();
			sp1.setFitToHeight(true);
			sp1.setFitToWidth(true);

			sp1.setContent(stp);

			stp.getChildren().add(lbl_coreIndex);
			stp.getChildren().add(internalMasonry1);

			GridPane.setConstraints(sp1, x, y);// column/row
			core_parentgrid.getChildren().add(sp1);

		}
		System.out.println("Rows " + core_parentgrid.getRowConstraints().size());
		System.out.println("Cols " + core_parentgrid.getColumnConstraints().size());

	}

	private JFXMasonryPane createMasonryPane() {
		JFXMasonryPane internalMasonry1 = new JFXMasonryPane();
		internalMasonry1.setStyle("-fx-background-color: white;");
		internalMasonry1.setCellWidth(100);
		internalMasonry1.setCellHeight(30);
		// internalMasonry1.setCellWidth(100);
		// internalMasonry1.setCellHeight(70);

		// internalMasonry1.getChildren().addListener((ListChangeListener.Change<?
		// extends Node> c) -> {
		// // change cell width/height depending on amount of children!
		// int numChildren = internalMasonry1.getChildrenUnmodifiable().size();
		// int max = 12;
		// System.out.println("Set to : " + (10 * 12 / numChildren));
		//// internalMasonry1.setCellWidth(10 * 12 / numChildren);
		//// internalMasonry1.setCellHeight(10 * 12 / numChildren);
		// internalMasonry1.setCellWidth(100);
		// internalMasonry1.setCellHeight(30);
		// });

		// TODO geht nicht wegen wechselwirkung beim layouten!!!!
		// internalMasonry1.widthProperty().addListener((o, oldVal, newVal) -> {
		// int numChildren = internalMasonry1.getChildrenUnmodifiable().size();
		// if (newVal.doubleValue()/numChildren > 100) {
		// internalMasonry1.setCellWidth(100);
		// } else {
		// internalMasonry1.setCellWidth(newVal.doubleValue()/numChildren);
		// }
		// System.out.println("CellWidth: " + internalMasonry1.getCellWidth() +
		// " by " + numChildren);
		// });
		//
		// internalMasonry1.heightProperty().addListener((o, oldVal, newVal) ->
		// {
		// int numChildren = internalMasonry1.getChildrenUnmodifiable().size();
		// if (newVal.doubleValue()/numChildren > 70) {
		// internalMasonry1.setCellHeight(70);
		// } else {
		// internalMasonry1.setCellHeight(newVal.doubleValue()/numChildren);
		// }
		// System.out.println("CellHeight: " + internalMasonry1.getCellHeight()
		// + " by " + numChildren);
		// });

		return internalMasonry1;
	}

}
