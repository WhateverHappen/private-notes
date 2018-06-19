# HashMap

我们在开发的过程中使用HashMap比较多，在Map中插入、删除和定位元素，HashMap是最好的选择。但如果要按自然顺序或自定义顺序遍历键，那么TreeMap会更好。如果需要输出的顺序和输入的相同，那么用LinkedHashMap可以实现，他还可以按读取顺序来排列。

HashMap是一个最常用的Map，他根据键的hashCode值存储数据，根据键可以直接获取它的值，具有很快的访问速度。HashMap最多只允许一条记录的键为null，允许多条记录的值为null。HashMap不支持线程同步，即任一时刻可以有多个线程同时写HashMap，可能会导致数据的不一致性。如果需要同步，可以用Collections的synchronizedMap方法使HashMap具有同步的能力。jdk1.8以后，HashMap底层进行了优化。之前按照数组存储节点，hash值相同时直接往后排列，相当于数组中对应的元素都是链表，当hash值相同的元素大于八个的时候，存储结构会变成红黑树来存储，方便查找。

# TreeMap

TreeMap实现SortMap接口，能够把它保存的记录根据键排序，默认是按键值得升序排序，也可以指定排序的比较器，当用Iterator遍历TreeMap时，得到的记录是排过序的。

TreeMap基于**红黑树**实现，该映射根据**其键的自然顺序进行排序**，或者根据**创建映射时提供的Comparator进行排序**。

TreeMap是**非同步**的。

TreeMap取出来的是排序后的键值对。

由于TreeMap需要排序，所以需要一个Comparator为键值进行大小比较。当然也是用Comparator定位的。

* Comparator可以在创建TreeMap时指定
* 如果创建时没有确定，那么就会使用key.compareTo（）方法，这就要求key必须实现Comparable接口。
* TreeMap是使用Tree数据结构实现的，所以使用compare接口就可以完成定位了。

# LinkedHashMap

LinkedHashMap保存了记录的插入顺序，在用Iterator遍历LinkedHashMap时，先得到的记录肯定是先插入的。也可以在构造时用带参数，按照应用次数排序。在遍历的时候会比HashMap慢，不过有种情况例外，当HashMap容量很大，实际数据较少时，遍历起来可能会比LinkedHashMap慢，因为LinkedHashMap的遍历速度只和实际数据有关，和容量无关，而HashMap的遍历速度和它的容量有关。

LinkedHashMap是HashMap的一个子类，如果需要输出的顺序和输入的相同，那么用LinkedHashMap可以实现，他还可以按读取顺序来排列，像连接池中可以应用。