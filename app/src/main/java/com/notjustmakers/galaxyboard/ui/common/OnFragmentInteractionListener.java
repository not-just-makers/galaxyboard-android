package com.notjustmakers.galaxyboard.ui.common;

/**
 * Used to notify when a fragment wants to change the main title.
 *
 * @author Andres Sanchez (andressanchez)
 */
public interface OnFragmentInteractionListener {

    /**
     * Called when a fragment wants to change the main title to the provided one.
     *
     * @param title New title to be set.
     */
    void onTitleChange(String title);

}
