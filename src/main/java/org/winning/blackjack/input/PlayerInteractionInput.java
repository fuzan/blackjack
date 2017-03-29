package org.winning.blackjack.input;

import org.winning.blackjack.util.card.BJLogger;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PlayerInteractionInput {

    private BJLogger bjLogger = new BJLogger();

    /**
     * no need to close any system in IO
     * @return
     */
    public Integer askPlayerBetting() {
        try {
            final BufferedReader reader = getUserInput();
            return Integer.parseInt(reader.readLine());
        } catch (Exception ex) {
            bjLogger.logException("player betting is not an integer !", ex);
        }
        return 0;
    }

    public String askPlayerWantToBegin() {
        System.out.println("Do you want to play ?");
        try {
            final BufferedReader reader = getUserInput();
            return reader.readLine();
        } catch (Exception ex) {
            bjLogger.logException("player input exception !", ex);
        }
        return "";
    }

    public String askPlayerAction() {
        System.out.println("hit/split/double/stand/surrander ?");
        try {
            final BufferedReader reader = getUserInput();
            return reader.readLine();
        } catch (Exception ex) {
            bjLogger.logException("player action input exception !", ex);
        }
        return "";
    }

    private BufferedReader getUserInput() {
        final InputStreamReader is = new InputStreamReader(System.in);
        return new BufferedReader(is);
    }
}
