1. hashCode的存在主要是用于查找的快捷性，如Hashtable ，HashMap等。hashCode是用来在散列存储结构中确定对象的存储地址的。
2. 如果两个对象相同，就是适用于equals（java.lang.Object）方法，那么这两个对象的hashCode一定要相同。
3. 如果对象的equals方法被重写，那么对象的hashCode也尽量重写，并且产生hashCode适用的对象，一定要和equals方法中使用的一致，否则就会违反上面提到的第二点。
4. 两个对象的hashCode相同，并不一定表示两个对象就相同，也就是不一定适用于equals（java.lang.Object）方法，只能够说明这两个对象在散列存储结构中，如Hashtable，他们“存放在同一个篮子里”。

再归纳一下就是，hashCode适用于查找使用的，而equals适用于比较两个对象是否相等的。

```

1.hashcode是用来查找的，如果你学过数据结构就应该知道，在查找和排序这一章有 
例如内存中有这样的位置  

0  1  2  3  4  5  6  7    

而我有个类，这个类有个字段叫ID,我要把这个类存放在以上8个位置之一，如果不用hashcode而任意存放，那么当查找时就需要到这八个位置里挨个去找，或者用二分法一类的算法。  

但如果用hashcode那就会使效率提高很多。  

我们这个类中有个字段叫ID,那么我们就定义我们的hashcode为ID％8，然后把我们的类存放在取得得余数那个位置。比如我们的ID为9，9除8的余数为1，那么我们就把该类存在1这个位置，如果ID是13，求得的余数是5，那么我们就把该类放在5这个位置。这样，以后在查找该类时就可以通过ID除 8求余数直接找到存放的位置了。  

2.但是如果两个类有相同的hashcode怎么办那（我们假设上面的类的ID不是唯一的），例如9除以8和17除以8的余数都是1，那么这是不是合法的，回答是：可以这样。那么如何判断呢？在这个时候就需要定义 equals了。  

也就是说，我们先通过 hashcode来判断两个类是否存放某个桶里，但这个桶里可能有很多类，那么我们就需要再通过 equals 来在这个桶里找到我们要的类。  

那么。重写了equals()，为什么还要重写hashCode()呢？  

想想，你要在一个桶里找东西，你必须先要找到这个桶啊，你不通过重写hashcode()来找到桶，光重写equals()有什么用啊


```

看一个具体的示例：

```
public class HashTest{
    private int i;
    
    public int getI(){
        return i;
    }

    public void setI(int i){
        this.i = i;
    }

    @Override
    public int hashCode()}
        return Objects.hash(i);
    }

    public final static void main(String[] args){
        HashTest a = new HashTest();
        HashTest b = new HashTest();
        a.setI(1);
        b.setI(1);
        Set<HashTest> set = new HashSet<HashTest>();
        set.add(a);
        set.add(b);
        System.out.println(a.hashCode() == b.hashCode());
        System.out.println(a.equals(b));
    }
}
```

以上示例，只是重写了hashCode方法，从上面的结果可以看出，虽然两个对象的hashCode相等，但是实际上两个对象并不是相等。我们没有重写equals方法，那么就会调用Object默认的equals方法，比较两个对象的引用是不是相同，显示这是两个不同的对象，两个对象的引用肯定是不同的。这里我们将生成的对象放到了HashSet中，而HashSet中只能够存放唯一的对象，也就是相同的（适用于equals方法）的对象只会存放一个，但是这里实际上是两个对象a，b都被放到了HashSet中，这样HashSet就失去了他本身的意义。

我们复写equals方法：

```
public class HashTest{
    private int i;
    
    public int getI(){
        return i;
    }

    public void setI(int i){
        this.i = i;
    }

    @Override
    public int hashCode()}
        return Objects.hash(i);
    }

    @Override
    public boolean equals(Object object){
        if(object == null){
            return false;
        }
        
        if(object == this){
            return true;
        }

        if(!(object instanceof HashTest)){
            return false;
        }

        HashTest other = (HashTest)object;
        if(other.getI() == this.getI()){
            return true;
        }
        return false;
    }

    public final static void main(String[] args){
        HashTest a = new HashTest();
        HashTest b = new HashTest();
        a.setI(1);
        b.setI(1);
        Set<HashTest> set = new HashSet<HashTest>();
        set.add(a);
        set.add(b);
        System.out.println(a.hashCode() == b.hashCode());
        System.out.println(a.equals(b));
    }
}
```

此时结果是true、true，两个对象完全相等。