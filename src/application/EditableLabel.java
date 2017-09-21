package application;

import java.util.regex.Pattern;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.css.PseudoClass;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class EditableLabel extends StackPane {

	private boolean isDoubleField = false;
	Pattern validDoubleText = Pattern.compile("((\\d*)|(\\d+\\.\\d*))");
	TextFormatter<Double> doubleTextFormatter;

	private boolean isIntegerField = false;
	Pattern validIntegerText = Pattern.compile("\\d*");
	TextFormatter<Integer> integerTextFormatter;

	private Label label;
	private TextField field;
	private Boolean focusTraversable;

	private boolean editable = true;
//	private Font font;

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public boolean isReallyEditable() {
		return this.editable;
	}

	private StringProperty text = new SimpleStringProperty();

	public EditableLabel() {
		init("");
	}

	public EditableLabel(String text) {
		init(text);
	}

	public String getText() {
		return text.get();
	}

	public StringProperty getTextProperty() {
		return text;
	}

	public void setText(String text) {
		this.text.set(text);
		label.setText(text);
	}

	private void init(String text) {
		this.text.set(text);

//		font = Font.loadFont(getClass().getClassLoader().getResource("resources/fonts/Sawasdee.ttf").toExternalForm(), 20.0);

		label = new Label(text);
		label.setMaxWidth(Double.MAX_VALUE);
		field = new TextField(text);


		label.getStyleClass().add("editable-label");
		field.getStyleClass().add("editable-label");

		focusTraversable = false;
		label.textProperty().bindBidirectional(field.textProperty());

		label.setOnMouseClicked(this::handleMouseClicked);
		field.setOnKeyPressed(this::handleKeyPressed);

		this.focusedProperty().addListener((observable, oldValue, newValue) -> handleFocusChange(newValue));
		this.focusTraversableProperty()
				.addListener((observable, oldValue, newValue) -> handleFocusTraversableChange(newValue));

		doubleTextFormatter = new TextFormatter<Double>(new DoubleStringConverter(), 0.0,
	            change -> {
	                String newText = change.getControlNewText() ;
	                if (validDoubleText.matcher(newText).matches()) {
	                    return change ;
	                } else return null ;
	            });

		integerTextFormatter = new TextFormatter<Integer>(new IntegerStringConverter(), 0,
	            change -> {
	                String newText = change.getControlNewText() ;
	                if (validIntegerText.matcher(newText).matches()) {
	                    return change ;
	                } else return null ;
	            });


//		// force the field to be numeric only
//	    field.textProperty().addListener(new ChangeListener<String>() {
//	        @Override
//	        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//	        	if (isIntegerField) {
//		            if (!newValue.matches("-?((\\d*)|(\\d+\\.\\d*))")) {
//		                field.setText(newValue.replaceAll("[^\\d]", ""));
//		            }
//	        	} else if (isDoubleField) {
//
//	        	}
//	        }
//	    });

		setNormalField();
		loadView();
	}

	private void handleMouseClicked(MouseEvent event) {
		if (event.getClickCount() == 2 && !isEditable()) {
			enterEditableMode();
		}
	}

	private void loadView() {
		this.getChildren().add(label);
		this.getChildren().add(field);
		exitEditableMode();
	}

	private void handleKeyPressed(KeyEvent event) {
		switch (event.getCode()) {
		case ENTER:
			if ((field.getText() == null) || (field.getText().equals(""))) {
				field.setText(text.get());
			} else {
				text.set(field.getText());
				EditableLabelEvent ev = new EditableLabelEvent(this, this);
				this.fireEvent(ev);
			}
			exitEditableMode();
			break;
		case ESCAPE:
			field.setText(text.get());
			exitEditableMode();
			break;
		default:
			break;
		}
	}

	private void exitEditableMode() {
		field.setEditable(false);
		field.deselect();
		field.setVisible(false);
		field.pseudoClassStateChanged(PseudoClass.getPseudoClass("editable"), false);
		label.setVisible(true);
	}

	private void enterEditableMode() {
		if (!editable) return;

		field.setEditable(true);
		// field.deselect();
		Platform.runLater(new Runnable() {
		     @Override
		     public void run() {
		    	 field.requestFocus();
		     }
		});
		label.setVisible(false);
		field.pseudoClassStateChanged(PseudoClass.getPseudoClass("editable"), true);
		field.setVisible(true);
	}

	private void handleFocusChange(Boolean newValue) {
		if (!newValue) {
			text.set(field.getText());
			exitEditableMode();
		} else if (focusTraversable) {
			enterEditableMode();
		}
	}

	private void handleFocusTraversableChange(Boolean newValue) {
		focusTraversable = newValue;
	}

	public boolean isEditable() {
		if (field.isVisible()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected void layoutChildren() {
		label.setStyle(label.getStyle()+" "+this.getStyle());
		field.setStyle(field.getStyle()+" "+this.getStyle());

		layoutInArea(label, 0, 0, getWidth(), getHeight(), 0, HPos.LEFT, VPos.CENTER);
		layoutInArea(field, 0, 0, getWidth(), getHeight(), 0, HPos.LEFT, VPos.CENTER);
		// getChildren().stream().forEach((node) -> {
		// layoutInArea(node, 0, 0, getWidth(), getHeight(), 0, HPos.LEFT,
		// VPos.CENTER);
		// });
	}

	public boolean isDoubleField() {
		return isDoubleField;
	}

	public void setDoubleField(boolean isDoubleField) {
		if (isDoubleField) {
			this.isIntegerField = false;
			field.setTextFormatter(doubleTextFormatter);
			field.setText(text.get());
			label.setText(text.get());
		}
		this.isDoubleField = isDoubleField;
	}

	public boolean isIntegerField() {
		return isIntegerField;
	}

	public void setIntegerField(boolean isIntegerField) {
		if (isIntegerField) {
			this.isDoubleField = false;
			field.setTextFormatter(integerTextFormatter);
			field.setText(text.get());
			label.setText(text.get());
		}
		this.isIntegerField = isIntegerField;
	}

	public void setNormalField() {
		field.setTextFormatter(null);
		field.setText(text.get());
		label.setText(text.get());
		this.isIntegerField = false;
		this.isDoubleField = false;
	}

	public void refresh() {
		field.setText(text.get());
		label.setText(text.get());
	}
}
