package yingjianhua.vote.tools;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

@Component
public class UploadUtil {

	@Resource
	private Constant constant;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd");

	public String getYMDPath() {
		return sdf.format(new Date());
	}
	
	public String getDefaultPath(String module, String filename) {
		return constant.getUploadPath()+File.separator+module+getYMDPath()+File.separator+filename;
	}
	
	public String getDefaultAbstractPath(String module, String filename) {
		return ServletActionContext.getServletContext().getRealPath("/")+getDefaultPath(module, filename);
	}
	
	public String getDefaultWebPath(String module, String filename) {
		return constant.getWebPath()+getDefaultPath(module, filename);
	}
}
