package Utility;

import io.qameta.allure.Allure;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class VideoUtils {

    private static Process ffmpegProcess;

    // Starts the screen recording using FFmpeg
    public static void startRecording(String videoPath) {
        try {
            // Ensure the directory exists before starting the recording
            Files.createDirectories(Paths.get(videoPath).getParent());

            // Run FFmpeg command to capture the screen
            String command = "ffmpeg -f avfoundation -framerate 30 -video_size 1920x1080 -i 1 " + videoPath;

            // Using ProcessBuilder instead of Runtime.exec()
            ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
            processBuilder.redirectErrorStream(true);  // Redirect error stream to stdout
            ffmpegProcess = processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error starting the screen recording.");
        }
    }

    // Stops the screen recording and attaches the video to Allure report
    public static void stopRecording(String videoPath) {
        if (ffmpegProcess != null) {
            try {
                // Stop the recording process
                ffmpegProcess.destroy();

                // Wait for the process to fully terminate
                ffmpegProcess.waitFor();

                // Attach the video to Allure report
                if (Files.exists(Paths.get(videoPath))) {
                    Allure.addAttachment("Test Video", "video/mp4", Files.newInputStream(Paths.get(videoPath)), "mp4");
                } else {
                    System.err.println("Video file does not exist at: " + videoPath);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                System.err.println("Error stopping the screen recording.");
            } finally {
                // Reset the ffmpeg process
                ffmpegProcess = null;
            }
        }
    }
}
