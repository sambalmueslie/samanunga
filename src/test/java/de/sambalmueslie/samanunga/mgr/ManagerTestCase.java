package de.sambalmueslie.samanunga.mgr;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.Test;

import de.sambalmueslie.samanunga.mgr.api.BusinesObjectMgrListener;
import de.sambalmueslie.samanunga.mgr.api.BusinessObjectListener;

public class ManagerTestCase {

	@Test
	public void testInvalidValues() {
		final MyManager manager = new MyManager();
		manager.add(null);
		manager.add(new MyBusinessObject(null));
		manager.remove((String) null);
		manager.remove((MyBusinessObject) null);

		assertFalse(manager.get(null).isPresent());
		assertTrue(manager.getAll().isEmpty());
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testListener() {
		final BusinesObjectMgrListener<MyBusinessObject> mgrListener = mock(BusinesObjectMgrListener.class);
		final BusinessObjectListener objListener = mock(BusinessObjectListener.class);
		final MyManager manager = new MyManager();

		manager.register(mgrListener);

		final MyBusinessObject bo = new MyBusinessObject("bo-0");
		bo.register(objListener);
		manager.add(bo);

		verify(mgrListener).added(bo);

		bo.update();
		verify(objListener).changed(bo.getId());
		verify(mgrListener).changed(bo);

		manager.remove(bo);
		verify(mgrListener).removed(bo);

		verifyNoMoreInteractions(objListener, mgrListener);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testRemoveAll() {
		final BusinesObjectMgrListener<MyBusinessObject> mgrListener = mock(BusinesObjectMgrListener.class);
		final MyManager manager = new MyManager();
		manager.register(mgrListener);

		final MyBusinessObject bo0 = new MyBusinessObject("bo-0");
		final MyBusinessObject bo1 = new MyBusinessObject("bo-1");
		final MyBusinessObject bo2 = new MyBusinessObject("bo-2");

		manager.add(bo0);
		manager.add(bo1);
		manager.add(bo2);

		verify(mgrListener).added(bo0);
		verify(mgrListener).added(bo1);
		verify(mgrListener).added(bo2);

		manager.removeAll();
		verify(mgrListener).removed(bo0);
		verify(mgrListener).removed(bo1);
		verify(mgrListener).removed(bo2);

		verifyNoMoreInteractions(mgrListener);
	}

}
