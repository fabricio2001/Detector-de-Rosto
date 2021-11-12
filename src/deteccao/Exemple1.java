/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deteccao;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import static org.opencv.imgcodecs.Imgcodecs.CV_LOAD_IMAGE_COLOR;
import static org.opencv.imgcodecs.Imgcodecs.imread;
import org.opencv.imgproc.Imgproc;
import static org.opencv.imgproc.Imgproc.COLOR_BGR2GRAY;
import org.opencv.objdetect.CascadeClassifier;


/**
 *
 * @author fabri
 */
public class Exemple1 {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat imagemColorida = imread("src\\pessoas\\pessoas3.jpg");
        Mat imagemCinza = new Mat();
        Imgproc.cvtColor(imagemColorida, imagemCinza, COLOR_BGR2GRAY);
        
        CascadeClassifier classificador = new CascadeClassifier("src\\cascades\\haarcascade_frontalface_default.xml");
        MatOfRect faceDetectadas = new MatOfRect();
        
        classificador.detectMultiScale(imagemCinza, faceDetectadas,
                1.2, // scale factor
                1, // minNeighbors
                0, // flags
                new Size(30, 30), // minSize
                new Size(100,100)); // maxSize
        
        System.out.println(faceDetectadas.toArray().length);
        for (Rect rect: faceDetectadas.toArray()) {
            System.out.println(rect.x + " " + rect.y + " " + rect.width + " " + rect.height);
            Imgproc.rectangle(imagemColorida, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 255), 2);
        }
        
        Utilitarios ut = new Utilitarios();
        ut.mostraImagem(ut.convertMatToImage(imagemColorida));
    }
}
