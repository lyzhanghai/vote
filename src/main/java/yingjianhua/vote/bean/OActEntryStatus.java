package yingjianhua.vote.bean;

import yingjianhua.vote.pub.tb.EnumLine;
import yingjianhua.vote.pub.tb.IEnumOpt;

public enum OActEntryStatus implements IEnumOpt {
    INIT(0,"初始"),APPR(1,"已审核"),FAILED(2,"不通过");
    public static String NAME = "报名状态";
    public static OActEntryStatus DEFAULT = INIT; // 定义缺省值
    private EnumLine _line;
    
    private OActEntryStatus(int key, String name) {
      _line = new EnumLine(this, key, name);
    }
    
    public EnumLine getLine() {
      return _line;
    }
 }
