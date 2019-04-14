package com.notjustmakers.galaxyboard.ui.problems;

import com.notjustmakers.galaxyboard.model.Problem;

/**
 * Used to notify when a problem has been changed.
 *
 * @author Andres Sanchez (andressanchez)
 */
public interface OnProblemInteractionListener {

    /**
     * A problem has been changed. This method will trigger all the UI changes
     * required to display the new one.
     *
     * @param problem Problem
     */
    void onProblemChange(Problem problem);

}
