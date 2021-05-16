package fr.diginamic.recensement.services;

import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.math.NumberUtils;

import fr.diginamic.exception.ComparaisonNumberException;
import fr.diginamic.exception.NotNumberException;
import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;

/**
 * Recherche et affichage de toutes les villes d'un département dont la
 * population est comprise entre une valeur min et une valeur max renseignées
 * par l'utilisateur.
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationBorneService extends MenuService {

	@Override
	public void traiter(Recensement rec, Scanner scanner) throws NotNumberException, ComparaisonNumberException {

		System.out.println("Quel est le code du département recherché ? ");
		String choix = scanner.nextLine();

		System.out.println("Choississez une population minimum (en milliers d'habitants): ");
		String saisieMin = scanner.nextLine();
		
		if(!NumberUtils.isDigits(saisieMin)) {
			throw new NotNumberException("Vous ne pouvez rentrer une lettre !");
		}
		int mini = Integer.parseInt(saisieMin);
		if(mini < 0) {
			throw new ComparaisonNumberException("Vous ne pouvez pas saisir un chiffre plus petit que 0 !");
		}
		
		System.out.println("Choississez une population maximum (en milliers d'habitants): ");
		String saisieMax = scanner.nextLine();
		
		if(!NumberUtils.isDigits(saisieMax)) {
			throw new NotNumberException("Vous ne pouvez pas saisir une lettre ! ");
		}
		
		int maxi = Integer.parseInt(saisieMax);
		if(maxi < 0) {
			throw new ComparaisonNumberException("Vous ne pouvez pas saisir un chiffre plus petit que 0 !");
		}

		int min = Integer.parseInt(saisieMin) * 1000;
		int max = Integer.parseInt(saisieMax) * 1000;
		
		List<Ville> villes = rec.getVilles();
		for (Ville ville : villes) {
			if (ville.getCodeDepartement().equalsIgnoreCase(choix)) {
				if (ville.getPopulation() >= min && ville.getPopulation() <= max) {
					System.out.println(ville);
				}
			}
		}
	}

}
