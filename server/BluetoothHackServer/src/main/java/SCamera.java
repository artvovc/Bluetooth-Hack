import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;

/**
 * Created by Artemie on 04.02.2017.
 */
public class SCamera {
    private static SCamera ourInstance = new SCamera();

    public static SCamera getInstance() {
        return ourInstance;
    }

    WebcamPanel panel;

    int camera = 0;

    public WebcamPanel getPanel() {
        return panel;
    }

    public void setPanel(WebcamPanel panel) {
        this.panel = panel;
    }


    private SCamera() {
    }
}
