package yingjianhua.vote.bean;

import yingjianhua.vote.pub.tb.EnumLine;
import yingjianhua.vote.pub.tb.IEnumOpt;

public enum OAccountType implements IEnumOpt {
	// @formatter:off
	SERVICE(1, "服务号"), SUBSCRIPTION(2, "订阅号");
	public static String NAME = "公众号类型";
	public static OAccountType DEFAULT = SERVICE; // 定义缺省值
	private EnumLine _line;
	private OAccountType(int key, String name)  {_line = new EnumLine(this, key, name); }
	public EnumLine getLine() {
	  return _line;
	}
}
