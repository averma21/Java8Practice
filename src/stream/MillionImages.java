package stream;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MillionImages implements Runnable {

    final int totalImages;
    final Path dirPath;
    final CountDownLatch countDownLatch;

    public MillionImages(int totalImages, Path dirPath, CountDownLatch countDownLatch) {
        this.totalImages = totalImages;
        this.dirPath = dirPath;
        this.countDownLatch = countDownLatch;
    }

    private void createImage(String text, String filePath) {
        Font font = new Font("TimesNewRoman", Font.BOLD, 24);

        int height = 300, width = 300;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2d = image.createGraphics();
//        graphics2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
//        graphics2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        graphics2d.setFont(font);
        FontMetrics fontmetrics = graphics2d.getFontMetrics();
        graphics2d.setColor(Color.GREEN);
        graphics2d.setBackground(Color.WHITE);
        int x = (width - fontmetrics.stringWidth(text)) / 2;
        int y = ((height - fontmetrics.getHeight()) / 2) + fontmetrics.getAscent();
        graphics2d.clearRect(0, 0, width, height);
        graphics2d.drawString(text, x, y);
        graphics2d.dispose();
        try {
            ImageIO.write(image, "gif", new File(filePath));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void run() {
        for (int i = 1; i <= totalImages; i++) {
            createImage("Text is " + i, dirPath.toAbsolutePath() + "/image" + i);
        }
        countDownLatch.countDown();
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newWorkStealingPool();
        long time = System.currentTimeMillis();
        Path root = Files.createDirectory(Paths.get("test" + System.currentTimeMillis()));
        int runnableCount = 20;
        int imagesPerRunnable = 200;
        CountDownLatch latch = new CountDownLatch(runnableCount);
        for (int i = 1; i <= runnableCount; i++) {
            Path path = Files.createDirectory(Paths.get(root.toAbsolutePath() + "/dir" + i));
            executorService.submit(new MillionImages(imagesPerRunnable, path, latch));
        }
        latch.await();
        long timeSpent = System.currentTimeMillis() - time;
        System.out.println("Created " + runnableCount * imagesPerRunnable + " images in " + timeSpent + "ms ("+TimeUnit.SECONDS.convert(System.currentTimeMillis() - time, TimeUnit.MILLISECONDS) + " seconds)");
    }

}
