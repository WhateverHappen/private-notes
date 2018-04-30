HashMap本质是数组加链表。根据key取得hash值，然后计算出数组下表，如果多个key对应到同一个下标，就用链表串起来，新插入的在前面。

ConcurrentHashMap：在HashMap的基础上，ConcurrentHashMap采用<font color=red>**锁分段技术，**</font>将数据分为多个segment，默认为16个（concurrency level），每个小的片段segment上面都有锁存在，那么在插入元素的时候就需要先找到应该插入到哪一个片段segment，然后每次操作对一个segment加锁，避免多线程锁的几率，提高并发效率。

# HashMap概述

HashMap基于哈希表的Map接口的实现。此实现提供所有可选的映射操作，并允许使用null值和null键。（除了不同步和允许使用null之外，HashMap类与Hashtable大致相同）。此类不保证映射的顺序，特别是他不保证该顺序恒久不变。

值得注意的是HashMap不是线程安全的，如果想要线程安全的HashMap，可以通过Collections类的静态方法synchronizedMap获得线程安全的HashMap。

```
Map map = Collections.syunchronizedMap(new HashMap());
```

# HashMap

HashMap的底层主要是基于数组和链表来实现的，他之所以有相当快的查询速度主要是因为他是通过计算散列码来决定存储的位置。能够很快地计算出对象所存储的位置。HashMap中主要是通过key的hashCode来计算hash值的，只要hashCode相同，计算出来的hash值就一样。如果存储的对象多了，就有可能不同的对象所算出来的hash值是相同的，这就出现了所谓的hash冲突。HashMap底层是通过链表来解决hash冲突的。

HashMap中的<font color=red>**加载因子是表示Hash表中元素填满的程度。**</font>若：加载因子越大，填满的元素越多，好处是，空间的利用率高了，但是冲突的机会加大了。反之，加载因子越小，填满的元素越少，好处是冲突的机会减少了，但是空间浪费多了。