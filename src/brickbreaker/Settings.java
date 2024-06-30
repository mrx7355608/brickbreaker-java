
package brickbreaker;


public class Settings {
    private boolean backgroundMusicOn = true;
    private boolean soundEffectsOn = false;

    public boolean isBackgroundMusicOn() {
        return backgroundMusicOn;
    }

    public boolean isSoundEffectsOn() {
        return soundEffectsOn;
    }

    public void setBackgroundMusicOn(boolean backgroundMusicOn) {
        this.backgroundMusicOn = backgroundMusicOn;
    }

    public void setSoundEffectsOn(boolean soundEffectsOn) {
        this.soundEffectsOn = soundEffectsOn;
    }
    
}
