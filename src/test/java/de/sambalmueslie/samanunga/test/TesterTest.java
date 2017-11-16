package de.sambalmueslie.samanunga.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class TesterTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		tester = new Tester(TesterTest.class, name.getMethodName());
	}

	@Test
	public void testSample() {
		// mock the sample interface by using the tester
		// if no logging of the mock calls are requested, mock without the tester
		final SampleInterface sampleIf = tester.mock(SampleInterface.class);

		// create the sample controller, which gots the mock injected and works with it.
		final SampleController controller = new SampleController(sampleIf);

		// do some basic mock operations
		// these are not covered by the tester, cause you could use basic mockito functionality to handle that case
		System.out.println("-> Modify return value");
		when(sampleIf.getIt()).thenReturn("Result");
		System.out.println("-> Call getter");
		assertEquals("Result", sampleIf.getIt());
		System.out.println("-> Verify getter call");
		verify(sampleIf).getIt();

		System.out.println("-> Calling anything not mocked");
		sampleIf.callMe();
		System.out.println("-> Verify call");
		verify(sampleIf).callMe();

		// here are the specific part of the tester, where the usage of the mock done by the controller is logged
		System.out.println("-> Now the indirect calls by the controller");
		// call a method without a return value (notify no verify is needed, cause thats done by the tester)
		controller.execute();

		// call a method with a return value which could checked with junit assertions
		final String result = controller.extract();
		assertEquals("Result", result);

		// the verify call which makes the tester creating the result file,
		// compare it with the reference and return true on success,
		// otherwise false
		assertTrue(tester.verify());

	}

	@Rule
	public final TestName name = new TestName();
	private Tester tester;

}
