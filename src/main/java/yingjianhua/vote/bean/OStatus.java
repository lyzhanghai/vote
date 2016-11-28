package yingjianhua.vote.bean;

import yingjianhua.vote.pub.tb.EnumLine;
import yingjianhua.vote.pub.tb.IEnumOpt;

public enum OStatus  implements IEnumOpt {
    FOLLOW(1, "关注"), NOFOLLOW(2, "取消关注");
    public static String NAME = "关注状态";
    public static OStatus DEFAULT = FOLLOW; // 定义缺省值
    private EnumLine _line;
    private OStatus(int key, String name)  {_line = new EnumLine(this, key, name); }
    public EnumLine getLine() {
      return _line;
    }
}
