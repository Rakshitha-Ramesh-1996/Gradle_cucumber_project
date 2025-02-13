package Utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class VideoUtils {

    private static Process ffmpegProcess;

    public static void startRecording(String videoPath) {
        try {
            // Run FFmpeg to capture screen
            String command = "ffmpeg -video_size 1920x1080 -framerate 30 -f x11grab -i :0.0+100,200 " + videoPath;
            ffmpegProcess = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void stopRecording(String videoPath) {
        if (ffmpegProcess != null) {
            ffmpegProcess.destroy();
            // Attach video file to Allure report
            try {
                Allure.addAttachment("Test Video", "video/mp4", Files.newInputStream(Paths.get(videoPath)), "mp4");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
