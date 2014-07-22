package ch.rasc.jacksonhibernate.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Team extends AbstractPersistable<Long> {

	private static final long serialVersionUID = 1L;

	private String name;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "team", orphanRemoval = true)
	private Set<Player> player = new HashSet<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Player> getPlayer() {
		return player;
	}

	public void setPlayer(Set<Player> player) {
		this.player = player;
	}

}