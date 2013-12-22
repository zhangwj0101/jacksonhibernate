package ch.rasc.jacksonhibernate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.rasc.jacksonhibernate.domain.Player;
import ch.rasc.jacksonhibernate.domain.QPlayer;
import ch.rasc.jacksonhibernate.domain.QTeam;
import ch.rasc.jacksonhibernate.domain.Team;

import com.mysema.query.jpa.impl.JPAQuery;

@RestController
public class SampleController {

	@PersistenceContext
	private EntityManager entityManager;
	
    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/players")
    @Transactional(readOnly=true)
    List<Player> players() {
       return new JPAQuery(entityManager).from(QPlayer.player).list(QPlayer.player);
    }

    @RequestMapping("/teams")
    @Transactional(readOnly=true)
    List<Team> teams() {
       return new JPAQuery(entityManager).from(QTeam.team).list(QTeam.team);
    }
}