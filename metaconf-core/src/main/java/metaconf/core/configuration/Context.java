package metaconf.core.configuration;

import java.util.SortedSet;
import java.util.TreeSet;

import lombok.Data;
import metaconf.core.scope.Scope;

/**
 * This represents the context in which configuration is evaluated. It's basically a collection of 
 * scope values that constraints the validity of certain configuration values.
 * @author fkunz
 *
 */
@Data
public class Context {
	private SortedSet<Scope> scopes = new TreeSet<>();
}
