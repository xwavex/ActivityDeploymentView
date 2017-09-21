package application;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

public class EditableLabelEvent extends Event {
	/**
	 *
	 */
	private static final long serialVersionUID = 3869472423264630474L;

	public static final EventType<EditableLabelEvent> EDITABLELABEL_UPDATED =
            new EventType<>(Event.ANY, "EDITABLELABEL_ALL");

	private String text;

	public EditableLabelEvent() {
        super(EDITABLELABEL_UPDATED);
    }

	 public EditableLabelEvent(Object source, EventTarget target) {
         super(source, target, EDITABLELABEL_UPDATED);

         this.setText(((EditableLabel) source).getText());
     }

     @Override
     public EditableLabelEvent copyFor(Object newSource, EventTarget newTarget) {
         return (EditableLabelEvent) super.copyFor(newSource, newTarget);
     }

     @Override
     public EventType<? extends EditableLabelEvent> getEventType() {
         return (EventType<? extends EditableLabelEvent>) super.getEventType();
     }

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
