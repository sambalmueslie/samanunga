package de.sambalmueslie.samanunga.mgr;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.sambalmueslie.samanunga.mgr.api.BusinesObjectMgrListener;
import de.sambalmueslie.samanunga.mgr.api.BusinessObject;
import de.sambalmueslie.samanunga.mgr.api.BusinessObjectListener;
import de.sambalmueslie.samanunga.mgr.api.Manager;

public abstract class BaseManager<T extends BusinessObject> extends Observable<BusinesObjectMgrListener<T>> implements Manager<T> {
	private class ObjectChangeForwarder implements BusinessObjectListener {

		@Override
		public void changed(String id) {
			final T obj = objects.get(id);
			if (obj == null) return;
			notifyChanged(obj);
		}

	}

	/** logger. */
	private static Logger logger = LogManager.getLogger(BaseManager.class);

	@Override
	public final void dipose() {
		super.dispose();
		objects.values().forEach(BusinessObject::dispose);
		objects.clear();
	}

	@Override
	public final Optional<T> get(String id) {
		return Optional.ofNullable(objects.get(id));
	}

	@Override
	public final Collection<T> getAll() {
		return Collections.unmodifiableCollection(objects.values());
	}

	protected final void add(T obj) {
		if (obj == null) {
			logger.error("Cannot add null object");
			return;
		}
		if (obj.getId() == null) {
			logger.error("Cannot add object with null id");
			return;
		}
		obj.register(objectChangeForwarder);
		objects.put(obj.getId(), obj);
		notifyAdded(obj);
	}

	protected final void remove(String id) {
		if (id == null) return;
		final T obj = objects.remove(id);
		if (obj == null) return;
		obj.unregister(objectChangeForwarder);
		notifyRemoved(obj);
	}

	protected final void remove(T obj) {
		if (obj == null) return;
		remove(obj.getId());
	}

	protected final void removeAll() {
		final List<T> objs = new LinkedList<>(objects.values());
		objects.clear();
		objs.forEach(this::notifyRemoved);
	}

	private void notifyAdded(T obj) {
		notify(l -> l.added(obj));
	}

	private void notifyChanged(T obj) {
		notify(l -> l.changed(obj));
	}

	private void notifyRemoved(T obj) {
		notify(l -> l.removed(obj));
	}

	/** the {@link ObjectChangeForwarder}. */
	private final ObjectChangeForwarder objectChangeForwarder = new ObjectChangeForwarder();
	/** the {@link BusinessObject}s by key. */
	private final Map<String, T> objects = new LinkedHashMap<>();
}
