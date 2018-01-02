package de.sambalmueslie.samanunga.id;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IdMgrTest {
	@Test
	public void testLongIdGeneration() {
		final long id01 = IdMgr.getNextId(Object.class);
		final long id02 = IdMgr.getNextId(Object.class);
		final long id03 = IdMgr.getNextId(Object.class);
		assertEquals(0, id01);
		assertEquals(1, id02);
		assertEquals(2, id03);

		final long id04 = IdMgr.getNextId(Integer.class);
		final long id05 = IdMgr.getNextId(Integer.class);
		assertEquals(0, id04);
		assertEquals(1, id05);
		IdMgr.clear(Object.class);

		final long id06 = IdMgr.getNextId(Object.class);
		final long id07 = IdMgr.getNextId(Object.class);
		assertEquals(0, id06);
		assertEquals(1, id07);

		final long id08 = IdMgr.getNextId(Integer.class);
		final long id09 = IdMgr.getNextId(Integer.class);
		assertEquals(2, id08);
		assertEquals(3, id09);

		IdMgr.clearAll();

		final long id10 = IdMgr.getNextId(Object.class);
		final long id11 = IdMgr.getNextId(Integer.class);
		assertEquals(0, id10);
		assertEquals(0, id11);

	}

	@Test
	public void testStringIdGeneration() {
		final String id01 = IdMgr.getNextId(Object.class, "obj");
		assertEquals("obj-000", id01);

		final String id02 = IdMgr.getNextId(Object.class, "obj");
		assertEquals("obj-001", id02);

		final String id03 = IdMgr.getNextId(Object.class, null);
		assertEquals("object-002", id03);

		IdMgr.clearAll();
	}

}
