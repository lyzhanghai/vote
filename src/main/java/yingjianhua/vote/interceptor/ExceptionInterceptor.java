package yingjianhua.vote.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import yingjianhua.vote.actions.resource.AbstractCRUDAction;
import yingjianhua.vote.pub.Exp;

public class ExceptionInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String rtn;
		try {
			try {
				System.out.println("expInterceptor start");
				rtn = invocation.invoke();
				System.out.println("expInterceptor end");
			} catch (Exp e) {
				ServletActionContext.getResponse().getWriter().print("{\"success\":false,\"errmsg\":\""+e.getExpMessage()+"\"}");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(invocation.getAction() instanceof AbstractCRUDAction) {
				return null;
			}
			return ActionSupport.ERROR;
		}
		return rtn;
	}

}
