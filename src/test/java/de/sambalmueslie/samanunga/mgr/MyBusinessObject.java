package de.sambalmueslie.samanunga.mgr;

public class MyBusinessObject extends BaseBusinessObject<MyBusinessObject> {

	public MyBusinessObject(String id) {
		super(id);
	}

	public void update() {
		notify(l -> l.changed(getId()));
	}

}
