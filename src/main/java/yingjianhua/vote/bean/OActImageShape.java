package yingjianhua.vote.bean;

import yingjianhua.vote.pub.tb.EnumLine;
import yingjianhua.vote.pub.tb.IEnumOpt;

public enum OActImageShape implements IEnumOpt {
    SQ(0, "方图"), HO(1, "横图"), VE(2, "竖图"), UL(3, "不限制");
    public static String NAME = "图片限制";
    public static OActImageShape DEFAULT = UL; // 定义缺省值
    private EnumLine _line;
    
    private OActImageShape(int key, String name) {
      _line = new EnumLine(this, key, name);
    }
    
    public EnumLine getLine() {
      return _line;
    }
}