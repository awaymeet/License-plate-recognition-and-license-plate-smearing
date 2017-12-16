package org.easypr.core;

import static org.bytedeco.javacpp.opencv_highgui.imwrite;

import java.util.Vector;

import org.bytedeco.javacpp.opencv_core.Mat;

import jaylinn.AndroidOut;
import jaylinn.ImageUtil;
import jaylinn.MatAndPoint;

/**
 * @author Created by fanwenjie
 * @author lin.yao
 * 
 */
public class PlateDetect {

    /**
     * @param src
     * @param resultVec
     *            可能是车牌的图块集合
     * @return the error number
     *         <ul>
     *         <li>0: plate detected successfully;
     *         <li>-1: source Mat is empty;
     *         <li>-2: plate not detected.
     *         </ul>
     */
    public int plateDetect(final MatAndPoint src,Vector<MatAndPoint> rmp) {
        Vector<MatAndPoint> vmp = plateLocate.plateLocate(src.getMat());
        Vector<Mat> matVec=new Vector<>();
        for(MatAndPoint tmatAndPoint:vmp){
        	matVec.add(tmatAndPoint.getMat());
        }
        if (0 == matVec.size()) {
            return -1;
        }

        AndroidOut.jaylinnprintln(vmp.size()+"_______________________________"+rmp.size());
        if (0 != plateJudge.plateJudge(vmp,rmp)) {
            return -2;
        }
        AndroidOut.jaylinnprintln(rmp.get(0).getjPoint());
        
        
        /*for(MatAndPoint tmatAndPoint:vmp){
        	
        	AndroidOut.jaylinnprintln(tmatAndPoint.getjPoint());
        	String srcImgPath = "f:/b.jpg";
        	ImageUtil.zoomImage(srcImgPath, "f:\\p.jpg",tmatAndPoint.getjPoint().getX2()-tmatAndPoint.getjPoint().getX1(), tmatAndPoint.getjPoint().getY2()-tmatAndPoint.getjPoint().getY1());
            String iconPath = "f:\\p.jpg";
            String targerPath = "f:/99.jpg";
            ImageUtil.waterMarkImageByIcon(iconPath, srcImgPath, targerPath, 0, 20, 10,0.8f);
        }*/

        if (getPDDebug()) {
            int size = (int) rmp.size();
            for (int i = 0; i < size; i++) {
                Mat img = rmp.get(i).getMat();
                String str = "tmp/plate_judge_result_" + Integer.valueOf(i).toString() + ".jpg";
                imwrite(str, img);
            }
        }

        return 0;
    }

    /**
     * 生活模式与工业模式切换
     * 
     * @param pdLifemode
     */
    public void setPDLifemode(boolean pdLifemode) {
        plateLocate.setLifemode(pdLifemode);
    }

    /**
     * 是否开启调试模式
     * 
     * @param pdDebug
     */
    public void setPDDebug(boolean pdDebug) {
        plateLocate.setDebug(pdDebug);
    }

    /**
     * 获取调试模式状态
     * 
     * @return
     */
    public boolean getPDDebug() {
        return plateLocate.getDebug();
    }

    public void setGaussianBlurSize(int gaussianBlurSize) {
        plateLocate.setGaussianBlurSize(gaussianBlurSize);
    }

    public final int getGaussianBlurSize() {
        return plateLocate.getGaussianBlurSize();
    }

    public void setMorphSizeWidth(int morphSizeWidth) {
        plateLocate.setMorphSizeWidth(morphSizeWidth);
    }

    public final int getMorphSizeWidth() {
        return plateLocate.getMorphSizeWidth();
    }

    public void setMorphSizeHeight(int morphSizeHeight) {
        plateLocate.setMorphSizeHeight(morphSizeHeight);
    }

    public final int getMorphSizeHeight() {
        return plateLocate.getMorphSizeHeight();
    }

    public void setVerifyError(float verifyError) {
        plateLocate.setVerifyError(verifyError);
    }

    public final float getVerifyError() {
        return plateLocate.getVerifyError();
    }

    public void setVerifyAspect(float verifyAspect) {
        plateLocate.setVerifyAspect(verifyAspect);
    }

    public final float getVerifyAspect() {
        return plateLocate.getVerifyAspect();
    }

    public void setVerifyMin(int verifyMin) {
        plateLocate.setVerifyMin(verifyMin);
    }

    public void setVerifyMax(int verifyMax) {
        plateLocate.setVerifyMax(verifyMax);
    }

    public void setJudgeAngle(int judgeAngle) {
        plateLocate.setJudgeAngle(judgeAngle);
    }

    private PlateLocate plateLocate = new PlateLocate();

    private PlateJudge plateJudge = new PlateJudge();

}
