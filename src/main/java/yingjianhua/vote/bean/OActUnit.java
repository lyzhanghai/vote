package yingjianhua.vote.bean;

import yingjianhua.vote.pub.tb.EnumLine;
import yingjianhua.vote.pub.tb.IEnumOpt;

public enum OActUnit implements IEnumOpt {
    DAY(1, "天"), WEEK(2, "周");
    public static String NAME = "单位";
    public static OActUnit DEFAULT = DAY; // 定义缺省值
    private EnumLine _line;
    
    private OActUnit(int key, String name) {
      _line = new EnumLine(this, key, name);
    }
    
    public EnumLine getLine() {
      return _line;
    }
}