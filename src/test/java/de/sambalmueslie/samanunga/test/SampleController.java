package de.sambalmueslie.samanunga.test;

public class SampleController {

	public SampleController(SampleInterface sampleInterface) {
		this.sampleInterface = sampleInterface;
	}

	public void execute() {
		sampleInterface.sendSth(new byte[] { 1, 2, 3, 4, 5 });
		sampleInterface.callMe();
	}

	public String extract() {
		sampleInterface.notify("Extract");
		return sampleInterface.getIt();
	}

	private final SampleInterface sampleInterface;
}
