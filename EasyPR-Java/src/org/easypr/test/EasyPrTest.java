package org.easypr.test;

import static org.bytedeco.javacpp.opencv_highgui.imread;
import static org.easypr.core.CoreFunc.getPlateType;
import static org.easypr.core.CoreFunc.projectedHistogram;
import static org.easypr.core.CoreFunc.showImage;

import java.util.Date;
import java.util.Vector;

import org.bytedeco.javacpp.opencv_core.Mat;
import org.easypr.core.CharsIdentify;
import org.easypr.core.CharsRecognise;
import org.easypr.core.CoreFunc;
import org.easypr.core.PlateDetect;
import org.easypr.core.PlateLocate;
import org.junit.Test;

import jaylinn.AndroidOut;
import jaylinn.ImageUtil;
import jaylinn.MatAndPoint;

/**
 * @author lin.yao
 * 
 */
public class EasyPrTest {
	/*@Test
    public void jaylinn(){
		PlateDetect plateDetect = new PlateDetect();
		plateDetect.getGaussianBlurSize();
		Vector<Mat> matVector = new Vector<Mat>();
		System.out.println(matVector);
        plateDetect.plateDetect(imread("res/image/jaylinn/b.jpg"), matVector);
        System.out.println(matVector);
    }*/
    
    @Test
    public void testPlateRecognise() {
        //String imgPath = "res/image/test_image/test.jpg";
    	//String imgPath = "res/image/jaylinn/i.jpg";
    	//String imgPath = "res/image/test_image/plate_recognize.jpg";
        //String imgPath = "res/image/jaylinn/a.jpg";
    	String imgPath = "res/image/jaylinn/b.jpg";
    	//String imgPath = "res/image/jaylinn/c.png";
    	//String imgPath = "res/image/jaylinn/d.jpg";
    	//String imgPath = "res/image/jaylinn/e.jpg";
    	//String imgPath = "res/image/jaylinn/k.jpg";
    	//String imgPath = "res/image/jaylinn/u.jpg";
    	//String imgPath = "res/image/jaylinn/sf.jpg";
    	//String imgPath = "res/image/jaylinn/r.jpg";
    	//String imgPath = "res/image/jaylinn/z.jpg";
    	
        

        Mat src = imread(imgPath);
        MatAndPoint matAndPoint=new MatAndPoint();
        matAndPoint.setMat(src);
        PlateDetect plateDetect = new PlateDetect();
        plateDetect.setPDLifemode(true);
        Vector<MatAndPoint> matVector = new Vector<MatAndPoint>();
        if (0 == plateDetect.plateDetect(matAndPoint, matVector)) {
            CharsRecognise cr = new CharsRecognise();
            for (int i = 0; i < matVector.size(); ++i) {
                String result = cr.charsRecognise(matVector.get(i));
                AndroidOut.jaylinnprintln(matVector.size());
                System.out.println("Chars Recognised: " + result);
                
                Long t=new Date().getTime();
                String iconPath ="f:\\recognition\\temp\\"+t+".jpg";
                ImageUtil.zoomImage("f:\\weikelian.jpg",iconPath, matVector.get(i).getjPoint().getY2()-matVector.get(i).getjPoint().getY1(),matVector.get(i).getjPoint().getX2()-matVector.get(i).getjPoint().getX1());
                
                
                String targerPath = "f:\\recognition\\"+new Date().getTime()+".jpg";
                // 给图片添加水印
                ImageUtil.waterMarkImageByIcon(iconPath, imgPath, targerPath, 0,matVector.get(i).getjPoint().getX1(),matVector.get(i).getjPoint().getY1(),1f);
                
            }
        }else{
        	System.out.println(plateDetect.plateDetect(matAndPoint, matVector));
        }
        AndroidOut.jaylinnprintln(matVector.get(0).getjPoint());
        
    }

    /*@Test
    public void testPlateDetect() {
        String imgPath = "res/image/test_image/test.jpg";

        Mat src = imread(imgPath);
        PlateDetect plateDetect = new PlateDetect();
        plateDetect.setPDLifemode(true);
        Vector<Mat> matVector = new Vector<Mat>();
        if (0 == plateDetect.plateDetect(src, matVector)) {
            for (int i = 0; i < matVector.size(); ++i) {
                showImage("Plate Detected", matVector.get(i));
            }
        }
    }*/

   /* @Test
    public void testPlateLocate() {
        String imgPath = "res/image/test_image/test.jpg";

        Mat src = imread(imgPath);

        PlateLocate plate = new PlateLocate();
        plate.setDebug(true);
        plate.setLifemode(true);

        Vector<Mat> resultVec = plate.plateLocate(src);

        int num = resultVec.size();
        for (int j = 0; j < num; j++) {
            // showImage("Plate Located " + j, resultVec.get(j));
        }

        return;
    }*/

   /* @Test
    public void testCharsRecognise() {
        String imgPath = "res/image/test_image/chars_recognise_huAGH092.jpg";

        Mat src = imread(imgPath);
        CharsRecognise cr = new CharsRecognise();
        cr.setCRDebug(true);
        String result = cr.charsRecognise(src);
        System.out.println("Chars Recognised: " + result);
    }*/

    @Test
    public void testColorDetect() {
        String imgPath = "res/image/test_image/core_func_yellow.jpg";

        Mat src = imread(imgPath);

        CoreFunc.Color color = getPlateType(src, true);
        System.out.println("Color Deteted: " + color);
    }

    @Test
    public void testProjectedHistogram() {
        String imgPath = "res/image/test_image/chars_identify_E.jpg";

        Mat src = imread(imgPath);
        projectedHistogram(src, CoreFunc.Direction.HORIZONTAL);
    }

    @Test
    public void testCharsIdentify() {
        String imgPath = "res/image/test_image/chars_identify_E.jpg";

        Mat src = imread(imgPath);
        CharsIdentify charsIdentify = new CharsIdentify();
        String result = charsIdentify.charsIdentify(src, false, true);
        System.out.println(result);
    }

}
