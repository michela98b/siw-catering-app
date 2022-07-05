package it.uniroma3.siw;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.model.Credentials;
import it.uniroma3.siw.catering.model.Ingrediente;
import it.uniroma3.siw.catering.model.Piatto;
import it.uniroma3.siw.catering.model.User;

@SpringBootApplication
public class SiwCatering1Application {


	public static void main(String[] args) {
		SpringApplication.run(SiwCatering1Application.class, args);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("catering-unit");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();

		User admin = new User();
		admin.setNome("Utente");
		admin.setCognome("Admin");

		User user2 = new User();
		user2.setNome("Mario");
		user2.setCognome("Rossi");

		Credentials credentials = new Credentials();
		credentials.setUsername("mariorossi@test.it");
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		credentials.setPassword(passwordEncoder.encode("test"));
		credentials.setUser(user2);
		credentials.setRole(Credentials.DEFAULT_ROLE);

		Credentials adminCredentials = new Credentials();
		adminCredentials.setUsername("admin@test.it");
		adminCredentials.setPassword(passwordEncoder.encode("test"));
		adminCredentials.setUser(admin);
		adminCredentials.setRole(Credentials.ADMIN_ROLE);

		// CREA UNO CHEF

		Chef chef = new Chef();
		chef.setNome("Antonino");
		chef.setCognome("Cannavacciuolo");
		chef.setNazionalita("Italiana");

		Chef chef2 = new Chef();
		chef2.setNome("Bruno");
		chef2.setCognome("Barbieri");
		chef2.setNazionalita("Italiana");

		Ingrediente ingr1 = new Ingrediente();
		ingr1.setNome("Pomodoro");
		ingr1.setDescrizione("Pomodoro");
		ingr1.setOrigine("Pomodoro di Sanmarzano");

		Ingrediente ingr2 = new Ingrediente();
		ingr2.setNome("Pennette");
		ingr2.setDescrizione("Pasta corta");
		ingr2.setOrigine("Italiana");

		Ingrediente ingr3 = new Ingrediente();
		ingr3.setNome("Paccheri");
		ingr3.setDescrizione("Pasta corta");
		ingr3.setOrigine("Italiana");

		Ingrediente ingr4 = new Ingrediente();
		ingr4.setNome("Olio d'oliva");
		ingr4.setDescrizione("Olio d'oliva");
		ingr4.setOrigine("Toscana");

		Ingrediente ingr5 = new Ingrediente();
		ingr5.setNome("Carne");
		ingr5.setDescrizione("Carne macinata di vitella");
		ingr5.setOrigine("Carne umbra");

		Ingrediente ingr6 = new Ingrediente();
		ingr6.setNome("Zucchine");
		ingr6.setDescrizione("Zucchine fresche");
		ingr6.setOrigine("Italia");

		Ingrediente ingr7 = new Ingrediente();
		ingr7.setNome("Gamberi");
		ingr7.setDescrizione("Gambero rosso di Sanremo");
		ingr7.setOrigine("Liguria");

		Ingrediente ingr8 = new Ingrediente();
		ingr8.setNome("Scampi");
		ingr8.setDescrizione("Scampi freschi");
		ingr8.setOrigine("Mar Adriatico");

		Ingrediente ingr9 = new Ingrediente();
		ingr9.setNome("Prezzemolo");
		ingr9.setDescrizione("Foglie di prezzemolo");
		ingr9.setOrigine("Campania");

		Ingrediente ingr10 = new Ingrediente();
		ingr10.setNome("Calamari");
		ingr10.setDescrizione("Calamari freschi");
		ingr10.setOrigine("Mar Mediterraneo");

		Ingrediente ingr11 = new Ingrediente();
		ingr11.setNome("Latte");
		ingr11.setDescrizione("Latte intero");
		ingr11.setOrigine("Italia");

		Ingrediente ingr12 = new Ingrediente();
		ingr12.setNome("Uova");
		ingr12.setDescrizione("Uova");
		ingr12.setOrigine("Italia");

		Ingrediente ingr13 = new Ingrediente();
		ingr13.setNome("Zucchero");
		ingr13.setDescrizione("Zucchero");
		ingr13.setOrigine("Italia");

		Ingrediente ingr14 = new Ingrediente();
		ingr14.setNome("Pistacchio");
		ingr14.setDescrizione("Pistacchio verde di Bronte");
		ingr14.setOrigine("Italia");

		Ingrediente ingr15 = new Ingrediente();
		ingr15.setNome("Cioccolato");
		ingr15.setDescrizione("Cioccolato fondente");
		ingr15.setOrigine("Italia");


		Ingrediente ingr16 = new Ingrediente();
		ingr16.setNome("Spaghetti");
		ingr16.setDescrizione("Pasta lunga");
		ingr16.setOrigine("Italia");

		Buffet pesce = new Buffet();
		pesce.setNome("Buffet Marino");
		pesce.setDescrizione("Buffet con portate a base di pesce");
		pesce.setChef(chef);

		Buffet dolci = new Buffet();
		dolci.setNome("Buffet di Dolci");
		dolci.setDescrizione("Buffet con portate a base di dolci");
		dolci.setChef(chef);


		List<Buffet> listaBuffet = new ArrayList<>();
		listaBuffet.add(pesce);
		listaBuffet.add(dolci);

		chef.setBuffets(listaBuffet);

		Piatto piattoP1 = new Piatto();
		piattoP1.setNome("Paccheri alla crema di scampi");
		piattoP1.setDescrizione("Paccheri alla crema di scampi");

		Piatto piattoP2 = new Piatto();
		piattoP2.setNome("Pennette con zucchine e gamberi");
		piattoP2.setDescrizione("Pennette con zucchine e gamberi");

		Piatto piattoP3 = new Piatto();
		piattoP3.setNome("Frittura di pesce");
		piattoP3.setDescrizione("Frittura di calamari e gamberi");

		List<Ingrediente> ingredientiPescePiattoP1 = new ArrayList<>();
		ingredientiPescePiattoP1.add(ingr3);
		ingredientiPescePiattoP1.add(ingr8);
		ingredientiPescePiattoP1.add(ingr9);
		piattoP1.setIngredienti(ingredientiPescePiattoP1);

		List<Ingrediente> ingredientiPescePiattoP2 = new ArrayList<>();
		ingredientiPescePiattoP2.add(ingr2);
		ingredientiPescePiattoP2.add(ingr6);
		ingredientiPescePiattoP2.add(ingr7);
		piattoP2.setIngredienti(ingredientiPescePiattoP2);

		List<Ingrediente> ingredientiPescePiattoP3 = new ArrayList<>();
		ingredientiPescePiattoP3.add(ingr7);
		ingredientiPescePiattoP3.add(ingr10);
		piattoP3.setIngredienti(ingredientiPescePiattoP3);

		List<Piatto> piattiPesce = new ArrayList<>();
		piattiPesce.add(piattoP1);
		piattiPesce.add(piattoP2);
		piattiPesce.add(piattoP3);

		pesce.setPiatti(piattiPesce);

		Piatto piattoDolce1 = new Piatto();
		piattoDolce1.setNome("Biscotti di frolla");
		piattoDolce1.setDescrizione("Piccola pasticceria secca assortita");

		Piatto piattoDolce2 = new Piatto();
		piattoDolce2.setNome("Pasticceria mignon");
		piattoDolce2.setDescrizione("Pasticceria mignon fresca assortita");

		List<Ingrediente> ingredientiPiattoDolce1 = new ArrayList<>();
		ingredientiPiattoDolce1.add(ingr11);
		ingredientiPiattoDolce1.add(ingr12);
		ingredientiPiattoDolce1.add(ingr13);
		ingredientiPiattoDolce1.add(ingr14);
		ingredientiPiattoDolce1.add(ingr15);
		piattoDolce1.setIngredienti(ingredientiPiattoDolce1);

		List<Ingrediente> ingredientiPiattoDolce2 = new ArrayList<>();
		ingredientiPiattoDolce2.add(ingr11);
		ingredientiPiattoDolce2.add(ingr12);
		ingredientiPiattoDolce2.add(ingr13);
		ingredientiPiattoDolce2.add(ingr14);
		ingredientiPiattoDolce2.add(ingr15);
		piattoDolce2.setIngredienti(ingredientiPiattoDolce2);

		List<Piatto> piattiDolce = new ArrayList<>();
		piattiDolce.add(piattoDolce1);
		piattiDolce.add(piattoDolce2);

		dolci.setPiatti(piattiDolce);

		tx.begin ();
		em.persist (user2);
		em.persist(admin);
		em.persist (credentials);
		em.persist(adminCredentials);
		em.persist (chef);
		em.persist (chef2);
		em.persist (ingr1);
		em.persist (ingr2);
		em.persist (ingr3);
		em.persist (ingr4);
		em.persist (ingr5);
		em.persist (ingr6);
		em.persist (ingr7);
		em.persist (ingr8);
		em.persist (ingr9);
		em.persist (ingr10);
		em.persist (ingr11);
		em.persist (ingr12);
		em.persist (ingr13);
		em.persist (ingr14);
		em.persist (ingr15);
		em.persist (ingr16);
		em.persist (piattoP1);
		em.persist (piattoP2);
		em.persist (piattoP3);
		em.persist (piattoDolce1);
		em.persist (piattoDolce2);
		em.persist (pesce);
		em.persist (dolci);
		tx.commit ();

		em.close();
		emf.close();

	}
}
