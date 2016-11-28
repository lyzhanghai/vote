package yingjianhua.vote.bean;

import yingjianhua.vote.pub.tb.EnumLine;
import yingjianhua.vote.pub.tb.IEnumOpt;

public enum OSyncStatus implements IEnumOpt {
    INIT(1, "未同步"), SYNC(2, "已同步"), DEL(9, "已删除");
    public static String NAME = "同步状态";
    public static OSyncStatus DEFAULT = INIT; // 定义缺省值
    private EnumLine _line;
    private OSyncStatus(int key, String name)  {_line = new EnumLine(this, key, name); }
    public EnumLine getLine() {
      return _line;
    }
}