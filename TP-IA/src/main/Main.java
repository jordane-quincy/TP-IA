package main;

import individu.Individu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import strategie.StrategieI;
import strategie.impl.Fetard;
import bar.Bar;

/**
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class Main {

	public static void main(final String[] args) {
		checkNbArgs(args);

		int argNbPlacesDispo = 0;
		int argNbTour = 0;
		int argTaillePopulation = 0;

		try {
			argNbPlacesDispo = Integer.parseInt(args[0]);
			argNbTour = Integer.parseInt(args[1]);
			argTaillePopulation = Integer.parseInt(args[2]);
		} catch (final NumberFormatException nfe) {
			System.err.println("Arguments non valides: " + nfe.getMessage());
		}

		final Bar bar = new Bar(argNbPlacesDispo);

		final List<Individu> population = new ArrayList<Individu>();
		for (int i = 0; i < argTaillePopulation; i++) {
			final StrategieI strategie = new Fetard();
			final Individu curPerson = new Individu(i, strategie);

			population.add(curPerson);
		}
		
		Map<Integer, Boolean> infosCurTour = new HashMap<Integer, Boolean>();

		for (int curTour = 1; curTour <= argNbTour; curTour++) {
			bar.reset();

			for(final Individu personne : population){
				bar.choisiAllerAuBar(personne);
				infosCurTour
			}
			
		}

	}

	public static void checkNbArgs(final String[] args) {
		if (args == null || args.length != 3) {
			System.err.println("Nombre d'arguments invalide.");
			System.err.println("Format obligatoire :");
			System.err.println("	nbPlacesDispo ");
			System.err.println("	nbTour ");
			System.err.println("	taillePopulation ");
		}
	}

}
