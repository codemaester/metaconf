package metaconf.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.Is.is;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import metaconf.core.dimensions.Dimension;

import org.junit.Test;


public class CategoryTest {

	private Dimension catP1 = Dimension.builder()
			.priority(1).name("prio1").build();
	private Dimension catP2 = Dimension.builder()
			.priority(2).name("prio2").build();
	private Dimension catP3 = Dimension.builder()
			.priority(3).name("prio3").build();
	
	@Test
	public void testPriority() {
		List<Dimension> cats = new ArrayList<>();
		cats.add(catP3);
		cats.add(catP2);
		cats.add(catP1);
		Collections.sort(cats);
		
		assertThat(cats.get(0), equalTo(catP1));
		assertThat(cats.get(1), equalTo(catP2));
		assertThat(cats.get(2), equalTo(catP3));
	}
	
	@Test
	public void testBuildPrio() {
		assertThat(catP1.getPriority(), is(1));
	}
	
	@Test
	public void testBuildName() {
		assertThat(catP1.getName(), is("prio1"));
	}
}
