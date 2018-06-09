switch表达式后面的数据类型只能是byte，short，char，int四种整形类型，枚举类型以及java.lang.String类型。其中，**可以使用byte、short以及char类型是因为这三种类型会被自动提升为int**。而String类型在Java1.7以后才支持。

另外，switch是不支持包装类的。传入包装类可以正确执行是因为Java1.5之后的自动拆装箱机制，将包装类转化为了基本数据类型。