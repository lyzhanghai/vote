package yingjianhua.vote.tools;

public class Constant {
	private String webPath;
	private String basePath;
	private String uploadPath;
	
	public Constant(String webPath, String basePath, String uploadPath) {
		this.webPath = webPath;
		this.basePath = basePath;
		this.uploadPath = uploadPath;
	}
	
	public String getWebPath() {
		return webPath;
	}
	public String getBasePath() {
		return basePath;
	}
	public String getUploadPath() {
		return uploadPath;
	}
}
