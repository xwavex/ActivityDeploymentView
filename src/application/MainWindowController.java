package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

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
	private JFXButton sub_add;

	@FXML
	private JFXButton sub_remove;

	@FXML
	private HBox hb_period;

	@FXML
	private HBox hb_priority;

	@FXML
	void backBtnClicked(ActionEvent event) {

	}

	@FXML
	void downBtnClicked(ActionEvent event) {
		ObservableList<Label> content_sel_items = sub_content.getSelectionModel().getSelectedItems();
		if (content_sel_items.size() == 1) {
			Label saveLbl = content_sel_items.get(0);
			int index = sub_content.getItems().indexOf(saveLbl);
			if (index + 1 <= (sub_content.getItems().size() - 1)) {
				sub_content.getItems().remove(saveLbl);
				sub_content.getItems().add(index + 1, saveLbl);
				sub_content.getSelectionModel().clearAndSelect(index + 1);
			}
		} else if (content_sel_items.size() > 1) {
			LinkedHashMap<Label, Integer> map = new LinkedHashMap<Label, Integer>();
			for (Label saveLbl : content_sel_items) {
				map.put(saveLbl, sub_content.getItems().indexOf(saveLbl));
			}
			for (HashMap.Entry<Label, Integer> entry : map.entrySet()) {
				if (entry.getValue() + 1 <= (sub_content.getItems().size() - 1)) {
					// TODO make this better :D
				} else {
					return;
				}
			}
			sub_content.getSelectionModel().clearSelection();
			for (HashMap.Entry<Label, Integer> entry : map.entrySet()) {
				if (entry.getValue() + 1 <= (sub_content.getItems().size() - 1)) {
					sub_content.getItems().remove(entry.getKey());
					sub_content.getItems().add(entry.getValue() + 1, entry.getKey());
					sub_content.getSelectionModel().select(entry.getValue() + 1);
				}
			}
		}
	}

	@FXML
	void downdownBtnClicked(ActionEvent event) {
		ObservableList<Label> content_sel_items = sub_content.getSelectionModel().getSelectedItems();
		if (content_sel_items.size() == 1) {
			Label saveLbl = content_sel_items.get(0);
			sub_content.getItems().remove(saveLbl);
			sub_content.getItems().add(saveLbl);
			sub_content.getSelectionModel().clearAndSelect(sub_content.getItems().size() - 1);
		} else if (content_sel_items.size() > 1) {
			LinkedHashMap<Label, Integer> map = new LinkedHashMap<Label, Integer>();
			int biggestIndex = -1;
			for (Label saveLbl : content_sel_items) {
				int index = sub_content.getItems().indexOf(saveLbl);
				if (index > biggestIndex) {
					biggestIndex = index;
				}
				map.put(saveLbl, index);
			}
			int shiftIndex = sub_content.getItems().size() - biggestIndex - 1;
			sub_content.getSelectionModel().clearSelection();

			List<Label> keyList = new ArrayList<Label>(map.keySet());
			for (int i = keyList.size() - 1; i >= 0; i--) {
				Integer val = map.get(keyList.get(i));
				Label safelbl = keyList.get(i);
				if (val + shiftIndex <= (sub_content.getItems().size() - 1)) {
					sub_content.getItems().remove(safelbl);
					sub_content.getItems().add(val + shiftIndex, safelbl);
					sub_content.getSelectionModel().select(val + shiftIndex);
				} else {
					return;
				}
			}
		}
	}

	@FXML
	void reloadBtnClicked(ActionEvent event) {
		initializeListViewContent();
	}

	@FXML
	void upBtnClicked(ActionEvent event) {
		ObservableList<Label> content_sel_items = sub_content.getSelectionModel().getSelectedItems();
		if (content_sel_items.size() == 1) {
			Label saveLbl = content_sel_items.get(0);
			int index = sub_content.getItems().indexOf(saveLbl);
			if (index - 1 >= 0) {
				sub_content.getItems().remove(saveLbl);
				sub_content.getItems().add(index - 1, saveLbl);
				sub_content.getSelectionModel().clearAndSelect(index - 1);
			}
		} else if (content_sel_items.size() > 1) {
			LinkedHashMap<Label, Integer> map = new LinkedHashMap<Label, Integer>();
			for (Label saveLbl : content_sel_items) {
				map.put(saveLbl, sub_content.getItems().indexOf(saveLbl));
			}
			sub_content.getSelectionModel().clearSelection();
			for (HashMap.Entry<Label, Integer> entry : map.entrySet()) {
				if (entry.getValue() - 1 >= 0) {
					sub_content.getItems().remove(entry.getKey());
					sub_content.getItems().add(entry.getValue() - 1, entry.getKey());
					sub_content.getSelectionModel().select(entry.getValue() - 1);
				} else {
					return;
				}
			}
		}
	}

	@FXML
	void upupBtnClicked(ActionEvent event) {
		ObservableList<Label> content_sel_items = sub_content.getSelectionModel().getSelectedItems();
		if (content_sel_items.size() == 1) {
			Label saveLbl = content_sel_items.get(0);
			sub_content.getItems().remove(saveLbl);
			sub_content.getItems().add(0, saveLbl);
			sub_content.getSelectionModel().clearAndSelect(0);
		} else if (content_sel_items.size() > 1) {
			LinkedHashMap<Label, Integer> map = new LinkedHashMap<Label, Integer>();
			int smallestIndex = 1000000;
			for (Label saveLbl : content_sel_items) {
				int index = sub_content.getItems().indexOf(saveLbl);
				if (index < smallestIndex) {
					smallestIndex = index;
				}
				map.put(saveLbl, index);
			}
			int shiftIndex = smallestIndex;
			sub_content.getSelectionModel().clearSelection();
			for (HashMap.Entry<Label, Integer> entry : map.entrySet()) {
				if (entry.getValue() - shiftIndex >= 0) {
					sub_content.getItems().remove(entry.getKey());
					sub_content.getItems().add(entry.getValue() - shiftIndex, entry.getKey());
					sub_content.getSelectionModel().select(entry.getValue() - shiftIndex);
				} else {
					return;
				}
			}
		}
	}

	@FXML
	void removeBtnClicked(ActionEvent event) {
		ObservableList<Label> content_sel_items = sub_content.getSelectionModel().getSelectedItems();
		sub_selection.getItems().addAll(content_sel_items);
		sub_content.getItems().removeAll(content_sel_items);
		sub_content.getSelectionModel().clearSelection();
	}

	@FXML
	void addBtnClicked(ActionEvent event) {
		ObservableList<Label> content_sel_items = sub_content.getSelectionModel().getSelectedItems();

		ObservableList<Label> selection_sel_items = sub_selection.getSelectionModel().getSelectedItems();

		if (selection_sel_items.isEmpty()) {
			// error nothing to add
			return;
		} else {
			if (content_sel_items.isEmpty()) {
				// insert at bottom
				sub_content.getItems().addAll(selection_sel_items);
				sub_selection.getItems().removeAll(selection_sel_items);
				sub_selection.getSelectionModel().clearSelection();
			} else {
				// multiple selected use last one
				int indexOfLastSelectedItem = sub_content.getItems()
						.indexOf(content_sel_items.get(content_sel_items.size() - 1));
				sub_content.getItems().addAll(indexOfLastSelectedItem + 1, selection_sel_items);
				sub_selection.getItems().removeAll(selection_sel_items);
				sub_selection.getSelectionModel().clearSelection();
			}
		}
	}

	private Main main;

	private EditableLabel period_elbl;
	private EditableLabel priority_elbl;

	public void setMain(Main main) {
		this.main = main;

		period_elbl = new EditableLabel();
		if (period_elbl != null) {
			period_elbl.setText("0.0");
		}
		hb_period.getChildren().add(period_elbl);

		priority_elbl = new EditableLabel();
		if (priority_elbl != null) {
			priority_elbl.setText("0");
		}
		hb_priority.getChildren().add(priority_elbl);

		sub_selection.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		sub_content.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}

	public void initializeListView() {
		for (int i = 0; i < 7; i++) {
			Label lbl = new Label("Item " + i);
			try {
				lbl.setGraphic(new ImageView(
						new Image(new FileInputStream(Main.class.getResource("up-arrow.png").getFile()))));
				sub_selection.getItems().add(lbl);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public void initializeListViewContent() {
		for (int i = 0; i < 2; i++) {
			Label lbl = new Label("Content? " + i);
			try {
				lbl.setGraphic(new ImageView(
						new Image(new FileInputStream(Main.class.getResource("up-up-arrow.png").getFile()))));
				sub_content.getItems().add(lbl);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
