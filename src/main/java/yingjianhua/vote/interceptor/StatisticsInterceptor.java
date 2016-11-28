package yingjianhua.vote.interceptor;

import java.util.Date;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class StatisticsInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Date begin = new Date();
		System.out.println("statisInterceptor start");
		String rtn = invocation.invoke();
		System.out.println("statisInterceptor end");
		String path = invocation.getProxy().getActionName();
		Date end = new Date();
		System.err.println("【"+path+"】请求耗时【"+(end.getTime()-begin.getTime())+"】ms");
		return rtn;
	}

}
