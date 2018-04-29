# 字符串常量池

```
String str1 = "ABC";
String str2 = new String("ABC");
```

`String str1 = "ABC";`可能创建一个或者不创建对象，如果"ABC"这个字符串在Java String池中不存在，会在java String池里创建一个String对象("ABC")，然后str1指向这个内存地址，无论以后用这种方式创建多少个值为"ABC"的字符串对象，始终只有一个内存地址被分配，之后的都是String的拷贝，Java中称为“字符串驻留，所有的字符串常量都会在编译之后自动地驻留。

`String str2 = new String("ABC");`至少创建一个对象，也可能两个。因为用到new关键字，肯定会在heap中创建一个str2的String对象，他的value是"ABC"。同时如果这个字符串在Java String池里不存在，则会在池中创建这个String对象"ABC"。

在JVM中，考虑到垃圾回收(Garbage Collection)的方便，将heap划分为三部分：young generation(新生代)、tenured generation(old generation)(老年代)、permanent generation(永生代)。

为了解决字符串重复问题，生命周期长，存于permanent中。

JVM中，相应的类被加载运行后，常量池对应的映射到JVM运行时的常量池中。



考虑下面的问题：

```String str1 = new String("ABC");
String str1 = new String("ABC");
String str2 = new String("ABC");
```

str1 == str2的值是true还是false呢？      false

```
String str3 = "ABC";
String str4 = "ABC";
String str5 = "AB" + "C";
str3 == str4 //true
str3 == str5 //true
```

```
String a = "ABC"；
String b = "AB";
String c = b + "C";
System.out.println(a == c); //false
```

a、b在编译的时候就已经被确定了，而c是引用变量，不会在编译时就被确定。

（由于String类本身是final类型，使用“+”拼接时，会使用StringBuffer，并调用append，之后再调用toString方法，会新建一个String对象，用==判断，结果自然是false）

应用的情况：建议在平时的使用中，尽量使用`String str = "abcd";`这种方式来创建字符串，而不是`String str = new String("abcd");`这种形式。因为使用new构造器创建字符串对象一定会开辟一个新的heap空间，而双引号则是采用了String interning（字符串驻留）进行了优化，效率比构造器高。

# Integer常量池

```
public static void main(String[] args) {
    Integer i1 = 128;
    Integer i2 = 128;
    System.out.println(i1==i2);
    System.out.println(i1.equals(i2));
    Integer i3 = 127;
    Integer i4 = 127;
    System.out.println(i3==i4);
    System.out.println(i3.equals(i4));

    Integer i5 = new Integer(-128);
    Integer i6 = new Integer(-128);
    System.out.println(i5==i6);
    System.out.println(i5.equals(i6));
}
```

程序最终的运行结果是false、true、true、true、false、true

i1到i4使用等号赋值是JDK5的特性，相当于Integer.valueOf(x)

> valueOf(x)的作用是：如果建立的数值x处于缓冲池的数值范围中，则利用缓存，若是不在该范围内，则新建一个对象。

在Java中，Integer具有一个-128到127的缓冲池，在这个区间内赋值，不创建新的对象，而是直接在缓冲池中取值。故(i3 == i4)为真

若是使用new Integer(x)，则是在堆内存中创建新的对象，故(i5 == i6)为假