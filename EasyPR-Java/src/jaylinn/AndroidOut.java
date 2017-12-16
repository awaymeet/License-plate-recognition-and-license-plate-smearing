package jaylinn;

import static org.bytedeco.javacpp.opencv_highgui.cvShowImage;
import static org.bytedeco.javacpp.opencv_highgui.cvWaitKey;

import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacpp.opencv_core.Mat;

public class AndroidOut {
	public static  int twice=1;
	public static int getTwice() {
		return twice;
	}
	public static void setTwice(int twice) {
		AndroidOut.twice = twice;
	}
	
	
	
	public static void showImage(final String title, final Mat src) {
        try {
            IplImage image = src.asIplImage();
            if (image != null) {
                cvShowImage(title, image);
                cvWaitKey(0);
            }
        } catch (Exception ex) {
        }
    }
	
	// 智能输出
	public static void jaylinnprintln(String[] args){
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();  
        StackTraceElement e = stacktrace[2];  
        String methodName = e.getMethodName();
        String content="";
        for (int i = 0; i < args.length; i++) {
        	if (args.length-1!=i) {
        		content+="参数"+(i+1)+":"+args[i]+"————";
			} else {
				content+="参数"+(i+1)+":"+args[i];
			}
		}
        String packageName=e.getClassName();
        String className=getClassNameTools(packageName);
        
        System.out.println("第"+getTwice()+"次调试"+className+">"+methodName + "开始调用\r\n*******" + "调试参数——————"+content);
        twice=twice+1;
	}
	public static void jaylinnprintln(String args){
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();  
        StackTraceElement e = stacktrace[2];
        String packageName=e.getClassName();
        String className=getClassNameTools(packageName);
        String methodName = e.getMethodName();
        
        
        System.out.println("第"+twice+"次调试"+className+">"+methodName + "开始调用\r\n*******" + "调试参数——————"+args);
        twice=twice+1;
	}
	public static void jaylinnprintln(Object object){
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();  
        StackTraceElement e = stacktrace[2];  
        String methodName = e.getMethodName();
        
        String packageName=e.getClassName();
        String className=getClassNameTools(packageName);
        
        System.out.println("第"+twice+"次调试"+className+">"+methodName + "开始调用\r\n*******" + "调试参数——————"+object);
        twice=twice+1;
	}
	public static void jaylinnprintln(){
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();  
        StackTraceElement e = stacktrace[2];  
        String methodName = e.getMethodName();
        
        String packageName=e.getClassName();
        String className=getClassNameTools(packageName);
        
        System.out.println("第"+twice+"次调试"+className+">"+methodName + "开始调用\r\n*******" + "调试参数——————");
        twice=twice+1;
	}
	public static String getClassNameTools(String strOrig) {
	      int lastIndex = strOrig.lastIndexOf(".");
	      if(lastIndex == - 1){
	         lastIndex=0;
	      }
	      return strOrig.substring(lastIndex+1);
	}
}
