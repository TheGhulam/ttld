package ui_elements;

import com.badlogic.gdx.graphics.Texture;

public class button {

    private boolean isActive;
    private Texture activeButton, inactiveButton;

    public button(Texture activeButton, Texture inactiveButton) {
        isActive = false;
        this.activeButton = activeButton;
        this.inactiveButton = inactiveButton;
    }

}
