package jaylinn;


import org.bytedeco.javacpp.opencv_core.Mat;

public class MatAndPoint {

	private Long id;
	private Mat mat;
	private JPoint jPoint;
	
	
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Mat getMat() {
		return mat;
	}



	public void setMat(Mat mat) {
		this.mat = mat;
	}



	public JPoint getjPoint() {
		return jPoint;
	}



	public void setjPoint(JPoint jPoint) {
		this.jPoint = jPoint;
	}



	@Override
	public String toString() {
		return "MatAndPoint [id=" + id + ", mat=" + mat + ", jPoint=" + jPoint + "]";
	}
	
	
}
