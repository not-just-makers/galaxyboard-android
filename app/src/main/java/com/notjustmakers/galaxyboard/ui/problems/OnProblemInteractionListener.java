package com.notjustmakers.galaxyboard.ui.problems;

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
     * @param problemId Problem identifier in the API.
     */
    void onProblemChange(int problemId);

}
