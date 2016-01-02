package strategy.impl;

import java.util.List;
import java.util.Map;

import person.Person;
import strategy.StrategieI;

/**
 * @author DURIEZ Jean-Baptiste et QUINCY Jordane
 */
public class EasyGo implements StrategieI {

	/**
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 * 
	 * <pre>
	 * A EasyGo stay at home at first turn and do the more interesting things.
	 * In our implementation, it's more interesting to go to the bar if the simulation is launched by VIP.
	 * </pre>
	 */
	@Override
	public boolean goToTheBar(final Person me,
			final List<Map<Person, Boolean>> turnHistoric) {

		// le comportement est de rester chez soi et si je trouve plus
		// interessant
		// d’aller au bar, je décide d’aller au bar.

		// this could be easily faked :
		// * on windows, on command prompt :
		// set USERNAME=r.mandiau
		// --> and launch the program via this prompt
		// * in eclipse : edit the run configuration to set the environment
		// variable USERNAME to the desired username
		String usernameWhoRunThisProgram = null;
		try {
			usernameWhoRunThisProgram = System.getProperty("user.name");
		} catch (final Exception e) {
			// No reason to panic but home is a safe place
			return false;
		}

		if (usernameWhoRunThisProgram.toUpperCase().contains("MANDIAU")
				|| usernameWhoRunThisProgram.toUpperCase().contains("RENE")
				|| usernameWhoRunThisProgram.toUpperCase().contains("R.M")
				|| usernameWhoRunThisProgram.toUpperCase().contains("M.R")) {
			// Thanks to run our program
			if (turnHistoric.size() < 1) {
				System.out.println("It's an honor !");
			}
			// It will be more interesting to go to the bar !
			return true;
		} else if (usernameWhoRunThisProgram.toUpperCase().contains("JEAN")
				|| usernameWhoRunThisProgram.toUpperCase().contains("BAPTISTE")
				|| usernameWhoRunThisProgram.toUpperCase().contains("J-B")
				|| usernameWhoRunThisProgram.toUpperCase().contains("JORDANE")) {
			// O.K kids, let's go to the party ♫
			return true;
		} else {
			// I don't trust stranger so I stay at home.
			return false;
		}

	}
}
