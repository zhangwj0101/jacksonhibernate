package ch.rasc.jacksonhibernate;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.rasc.jacksonhibernate.domain.Player;
import ch.rasc.jacksonhibernate.domain.Team;
import ch.rasc.jacksonhibernate.repository.PlayerRepository;
import ch.rasc.jacksonhibernate.repository.TeamRepository;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class SampleController {

	private final PlayerRepository playerRepository;
	private final TeamRepository teamRepository;
	private final ObjectMapper objectMapper;

	@Autowired
	public SampleController(PlayerRepository playerRepository,
			TeamRepository teamRepository, ObjectMapper objectMapper) {
		this.playerRepository = playerRepository;
		this.teamRepository = teamRepository;
		this.objectMapper = objectMapper;
	}

	@RequestMapping("/")
	String home() {
		return "Hello World!";
	}

	@RequestMapping("/players")
	List<Player> players() {
		return this.playerRepository.findAll();
	}

	@RequestMapping("/teams")
	List<Team> teams() {
		return this.teamRepository.findAll();
	}

	@RequestMapping("/test")
	@Transactional(readOnly = true)
	public void test() throws JsonParseException, JsonMappingException, IOException {
		String j = "{\"id\" : 1,\"name\" : \"Team A\",\"player\" : [ 1, 7, 4 ]}";
		Team t = this.objectMapper.readValue(j, Team.class);
		System.out.println(t);
		System.out.println(t.getPlayer().size());
		for (Player p : t.getPlayer()) {
			System.out.println(p);
		}
	}

}