package ch.rasc.jacksonhibernate;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.rasc.jacksonhibernate.domain.Player;

import com.fasterxml.jackson.annotation.ObjectIdGenerator.IdKey;
import com.fasterxml.jackson.annotation.ObjectIdResolver;

@Component
public class PlayerIdResoler implements ObjectIdResolver {

	private final EntityManager entityManager;

	@Autowired
	public PlayerIdResoler(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void bindItem(IdKey id, Object pojo) {
		// nothing here
	}

	@Override
	public Object resolveId(IdKey id) {
		return this.entityManager.find(Player.class, id.key);
	}

	@Override
	public ObjectIdResolver newForDeserialization(Object context) {
		return this;
	}

	@Override
	public boolean canUseFor(ObjectIdResolver resolverType) {
		return resolverType.getClass() == getClass();
	}

}
