package de.sambalmueslie.samanunga.test;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.mockito.internal.creation.bytebuddy.InterceptedInvocation;
import org.mockito.invocation.DescribedInvocation;
import org.mockito.listeners.InvocationListener;
import org.mockito.listeners.MethodInvocationReport;

class InvocationHandler implements InvocationListener {

	InvocationHandler(Class<?> type, Class<?> suite, TestResult result) {
		this.type = type;
		testSuiteName = suite.getSimpleName();
		this.result = result;
	}

	@Override
	public void reportInvocation(MethodInvocationReport methodInvocationReport) {
		if (methodInvocationReport == null) return;

		final DescribedInvocation invocation = methodInvocationReport.getInvocation();
		if (invocation instanceof InterceptedInvocation) {
			handleInterceptedInvocation(methodInvocationReport, (InterceptedInvocation) invocation);
		} else {
			handleDescribedInvocation(methodInvocationReport);
		}
	}

	private String convert(Object obj) {
		if (obj == null || obj instanceof String) return (String) obj;

		final Class<? extends Object> argType = obj.getClass();
		if (argType.isArray()) {
			if (boolean[].class.isAssignableFrom(argType)) return Arrays.toString((boolean[]) obj);
			if (byte[].class.isAssignableFrom(argType)) return Arrays.toString((byte[]) obj);
			if (char[].class.isAssignableFrom(argType)) return Arrays.toString((char[]) obj);
			if (double[].class.isAssignableFrom(argType)) return Arrays.toString((double[]) obj);
			if (float[].class.isAssignableFrom(argType)) return Arrays.toString((float[]) obj);
			if (int[].class.isAssignableFrom(argType)) return Arrays.toString((int[]) obj);
			if (long[].class.isAssignableFrom(argType)) return Arrays.toString((long[]) obj);
			if (short[].class.isAssignableFrom(argType)) return Arrays.toString((short[]) obj);
			return Arrays.toString((Object[]) obj);
		}

		try {
			if (argType.getDeclaredMethod("toString") != null) return obj.toString();
		} catch (final NoSuchMethodException | SecurityException e) {
			// intentionally left empty
		}

		return ReflectionToStringBuilder.toString(obj);
	}

	private void handleDescribedInvocation(MethodInvocationReport methodInvocationReport) {
		// TODO Auto-generated method stub
	}

	private void handleInterceptedInvocation(MethodInvocationReport methodInvocationReport, InterceptedInvocation invocation) {
		if (invocation == null) return;

		final boolean testInvocation = invocation.getLocation().toString().contains(testSuiteName);
		if (testInvocation) return;

		final Method method = invocation.getMethod();
		final Object[] args = invocation.getArguments();
		final Object returnedValue = methodInvocationReport.getReturnedValue();

		final StringBuilder sb = new StringBuilder();
		sb.append("[").append(type.getSimpleName()).append("]\t");
		sb.append(method.getName()).append("(");

		for (int i = 0; args != null && i < args.length; i++) {
			final Object obj = args[i];
			sb.append(convert(obj));

			if (i < args.length - 1) {
				sb.append(", ");
			}
		}
		sb.append(")");

		if (method.getReturnType() != void.class) {
			sb.append("\t = ").append(convert(returnedValue));
		}

		result.invocation(sb.toString());
	}

	private final TestResult result;
	private final String testSuiteName;
	private final Class<?> type;

}
