package hello;

/**
 * Created by 310237766 on 6/8/2016.
 */
public class DemoPluginExtension {
    private String message = "Default Greeting from Gradle";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
