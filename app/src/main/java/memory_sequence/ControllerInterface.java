package memory_sequence;

import memory_sequence.model.*;

public interface ControllerInterface {
    public void userPressed(String buttonValue);

    public void userChoose(String mode);

    public void returnHome();
}