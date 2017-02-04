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

    WebcamPanel panel = new WebcamPanel(Webcam.getDefault());

    int camera = 0;

    public WebcamPanel getPanel() {
        return panel;
    }

    public void setPanel(WebcamPanel panel,int camera) {
        if(this.camera != camera)
        this.panel = panel;
        if(this.camera == 0) this.panel = new WebcamPanel(Webcam.getDefault());
    }

    private SCamera() {
    }
}
