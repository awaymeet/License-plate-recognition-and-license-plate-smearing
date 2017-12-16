package org.easypr.core;

import org.bytedeco.javacpp.opencv_core.*;

import jaylinn.MatAndPoint;

import java.util.Vector;


/**
 * @author Created by fanwenjie
 * @author lin.yao
 *
 */
public class PlateRecognize {

    public int plateRecognize(MatAndPoint src, Vector<String> licenseVec) {
        //车牌方块集合
        Vector<MatAndPoint> vmp = new Vector<MatAndPoint>();
        
        
        
        int resultPD = plateDetect.plateDetect(src, vmp);
        if (resultPD == 0) {
            int num = (int) vmp.size();
            for (int j = 0; j < num; j++) {
                //Mat plate = vmp.get(j).getMat();
                //获取车牌颜色
                String plateType = charsRecognise.getPlateType(vmp.get(j).getMat());
                //获取车牌号
                String plateIdentify = charsRecognise.charsRecognise(vmp.get(j));
                String license = plateType + ":" + plateIdentify;
                licenseVec.add(license);
            }
        }
        return resultPD;
    }

    /**
     * 设置是否开启生活模式
     * @param lifemode
     */
    public void setLifemode(boolean lifemode) {
        plateDetect.setPDLifemode(lifemode);
    }

    /**
     * 是否开启调试模式
     * @param debug
     */
    public void setDebug(boolean debug) {
        plateDetect.setPDDebug(debug);
        charsRecognise.setCRDebug(debug);
    }

    private PlateDetect plateDetect = new PlateDetect();
    private CharsRecognise charsRecognise = new CharsRecognise();
}
