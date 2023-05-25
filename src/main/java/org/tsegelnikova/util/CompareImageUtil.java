package org.tsegelnikova.util;

import nu.pattern.OpenCV;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.util.ArrayList;

public class CompareImageUtil {
    private CompareImageUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static Double compareTwoImage(File file1, File file2) {
        OpenCV.loadShared();
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat img1 = Imgcodecs.imread(file1.getPath());
        Mat img2 = Imgcodecs.imread(file2.getPath());

        Mat hsvBase = new Mat();
        Mat hsvTest1 = new Mat();

        Imgproc.cvtColor(img1, hsvBase, Imgproc.COLOR_BGR2HSV);
        Imgproc.cvtColor(img2, hsvTest1, Imgproc.COLOR_BGR2HSV);

        int hBins = 800;
        int sBins = 600;
        int[] histSize = { hBins, sBins };
        float[] ranges = { 0, 180, 0, 256 };
        int[] channels = { 0, 1 };
        Mat histBase = new Mat();
        Mat histTest1 = new Mat();

        ArrayList<Mat> hsvBaseList = new ArrayList<>();
        hsvBaseList.add(hsvBase);
        Imgproc.calcHist(hsvBaseList, new MatOfInt(channels), new Mat(), histBase, new MatOfInt(histSize), new MatOfFloat(ranges), false);
        Core.normalize(histBase, histBase, 0, 1, Core.NORM_MINMAX);

        ArrayList<Mat> hsvTest1List = new ArrayList<>();
        hsvTest1List.add(hsvTest1);
        Imgproc.calcHist(hsvTest1List, new MatOfInt(channels), new Mat(), histTest1, new MatOfInt(histSize), new MatOfFloat(ranges), false);
        Core.normalize(histTest1, histTest1, 0, 1, Core.NORM_MINMAX);

        return Imgproc.compareHist( histBase, histTest1, 0 ) * 100;
    }
}
