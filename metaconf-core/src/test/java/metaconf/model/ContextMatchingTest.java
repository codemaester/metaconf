package metaconf.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import metaconf.core.configuration.Context;

import org.junit.Test;

public class ContextMatchingTest {

	@Test
	public void testDefaultContext() {
		Context context = new Context();
		long score = context.calclulateMatchScore(context);
		assertThat(score, equalTo(0b1000000L));
	}
	
	@Test
	public void testFilledAndDefaultContext() {
		Context context1 = new Context();
		context1.setScopeValue("dimension_2", "a");
		Context context2 = new Context();
		
		long score = context1.calclulateMatchScore(context2);
		assertThat(score, equalTo(0b1000100L));
	}
	
	@Test
	public void testNullAndFilledContext() {
		Context context1 = new Context();
		Context context2 = new Context();
		context2.setScopeValue("dimension_1", "a");
		
		long score = context1.calclulateMatchScore(context2);
		assertThat(score, equalTo(0b1000010L));
	}
	
	@Test
	public void testEqualContext() {
		Context context1 = new Context();
		context1.setScopeValue("dimension_2", "a");
		Context context2 = new Context();
		context2.setScopeValue("dimension_2", "a");
		
		long score = context1.calclulateMatchScore(context2);
		assertThat(score, equalTo(0b1001100L));
	}
	
	@Test
	public void testNotEqualContext() {
		Context context1 = new Context();
		context1.setScopeValue("dimension_2", "a");
		Context context2 = new Context();
		context2.setScopeValue("dimension_2", "b");
		
		long score = context1.calclulateMatchScore(context2);
		assertThat(score, equalTo(0L));
	}
	
	@Test
	public void testNonConsistentContext() {
		Context context1 = new Context();
		context1.getScopes().remove(context1.getScopes().first());
		Context context2 = new Context();
		
		long score = context1.calclulateMatchScore(context2);
		assertThat(score, equalTo(0L));
	}	

}
