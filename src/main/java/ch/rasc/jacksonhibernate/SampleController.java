package ch.rasc.jacksonhibernate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.rasc.jacksonhibernate.domain.Player;
import ch.rasc.jacksonhibernate.domain.Team;
import ch.rasc.jacksonhibernate.repository.PlayerRepository;
import ch.rasc.jacksonhibernate.repository.TeamRepository;

@RestController
public class SampleController {

	private final PlayerRepository playerRepository;
	private final TeamRepository teamRepository;

	@Autowired
	public SampleController(PlayerRepository playerRepository,
			TeamRepository teamRepository) {
		this.playerRepository = playerRepository;
		this.teamRepository = teamRepository;
	}

	@RequestMapping("/")
	String home() {
		return "Hello World!";
	}

	@RequestMapping("/players")
	List<Player> players() {
		return playerRepository.findAll();
	}

	@RequestMapping("/teams")
	List<Team> teams() {
		return teamRepository.findAll();
	}

}