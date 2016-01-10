package metaconf.core.configuration;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import metaconf.core.dao.DAOFactory;
import metaconf.core.scope.Scope;

/**
 * This represents the context in which configuration is evaluated. It's
 * basically a collection of scope values that constraints the validity of
 * certain configuration values.
 * 
 * @author fkunz
 *
 */
@XmlRootElement
@Data
public class Context {
	private SortedSet<Scope> scopes = new TreeSet<>();

	public Context() {
		DAOFactory.getScopeManager().getAllScopes().forEach(d -> {
			scopes.add(Scope.builder().name(d.getName()).value(null).build());
		});
	}

	public void setScopeValue(String scopeTypeName, String scopeValue) {
		scopes.forEach(dval -> {
			if (dval.getScopeType().getName().equals(scopeTypeName)) {
				dval.setValue(scopeValue);
			}
		});
	}

	public long calclulateMatchScore(Context context) {
		if (!areScopesCompatible(scopes, context.scopes)) {
			return 0;
		}

		Iterator<Scope> itA = scopes.iterator();
		Iterator<Scope> itB = context.scopes.iterator();

		long matchScore = 1;
		while (itA.hasNext()) {
			Scope scopeA = itA.next();
			Scope scopeB = itB.next();

			matchScore = matchScore << 2;
			if (scopeA.getValue() == null && scopeB.getValue() == null) {
			} else if (scopeA.getValue() != null && scopeB.getValue() == null) {
				matchScore |= 0b01;
			} else if (scopeA.getValue() == null && scopeB.getValue() != null) {
				matchScore |= 0b10;
			} else if (scopeA.getValue().equals(scopeB.getValue())) {
				matchScore |= 0b11;
			}
		}

		return matchScore;
	}

	private boolean areScopesCompatible(SortedSet<Scope> scopesA,
			SortedSet<Scope> scopesB) {
		if (scopesA.size() != scopesB.size()) {
			return false;
		}

		Iterator<Scope> itA = scopesA.iterator();
		Iterator<Scope> itB = scopesB.iterator();

		while (itA.hasNext()) {
			Scope scopeA = itA.next();
			Scope scopeB = itB.next();

			if (!scopeA.getScopeType().equals(scopeB.getScopeType())) {
				return false;
			}

			if (scopeA.getValue() != null && scopeB.getValue() != null
					&& !scopeA.getValue().equals(scopeB.getValue())) {
				return false;
			}
		}
		return true;
	}

}
