SQL连接可以分为**自然连接、内连接、外链接、交叉连接**

# 自然连接(natural join)

自然连接是一种特殊的等值连接，要求两个关系表中进行比较的属性组必须是名称相同的属性组，并且在结果中把重复的属性列去掉(即：留下名称相同的属性组中的一组)

**表A**

| Aname | Bid  | salary |
| ----- | ---- | ------ |
| Getz  | 1    | 3000   |
| Davis | 2    | 1500   |
| King  | 2    | 2200   |
| Davis | 3    | 5000   |
| Jane  | 5    | 4000   |

**表B**

| Bid  | Bname          |
| ---- | -------------- |
| 1    | Sales          |
| 2    | Marketing      |
| 3    | Accounts       |
| 4    | Administration |

自然连接SQL语句：select * from A natural join B

连接结果为：

| Aname | Bid  | salary | Bname     |
| ----- | ---- | ------ | --------- |
| Getz  | 1    | 3000   | Sales     |
| Davis | 2    | 1500   | Marketing |
| King  | 2    | 2200   | Marketing |
| Davis | 3    | 5000   | Accounts  |

由上面可以看出：**自然连接将两个关系组中在相同名称的属性上具有相同的值的行记录进行匹配(表A和表B中的Bid属性值相等的行记录)，并且重复的属性列去掉，**这样新的一行将出现在查询结果中，而那些没被匹配的行不出现在结果中。因此自然连接的结果会有数据丢失，这些丢失的数据就是那些没有匹配的数据。

PS：自然连接无需指定连接列，SQL会检查两个表中各个列的名城市都相同，不允许指定显示列，显示列只能用*表示

# 内连接(inner join)

内连接(inner join)是从查询结果表中删除与其他连接表中没有匹配行的所有行，所以内链接可能会丢失信息。inner可省略

内连接SQL语句：`select * from A inner join B on A.Bid = B.Bid`，这一句几乎等同于上面的自然连接SQL语句：`select * from A natural join B`，他们唯一的区别在于：**内连接会将名称相同的属性列Bid全部保留，而不会去掉，**如果疆内连接的语句改成`select * from A inner join B on A.Bid = B.Bid using(Aname, A.Bid, salary, Bname)`那么就和自然连接完全一样了。

另外，natural join和 natural inner join是等同的，也就是说下面三句查询语言等同

1. `select * from A inner join B on A.Bid = B.Bid using(Aname,A.Bid,salary,Bname)`
2. `select * from A natural join B`
3. `select * from A natural inner join B`

进一步理解可以清楚，natural和on...using...都是以join连接基础上的附加链接条件，并且只能同时使用其中的一种，同时不可以缺少连接条件，内连接附加natural时，表示名称相同的列进行内连接，此时不能使用on...using...等修饰

# 外链接(outer join)

外连接(outer join)：与内连接的区别在于，**外连接不仅返回匹配的行，也会返回不匹配的行。其中outer可省略

1. 左外连接(left outer join)：**结果集包括LEFT OUTER子句中指定的左表的所有行，而不仅仅是连接列所匹配的行。如果左表的某行在右表中没有匹配行，则在结果集的行中右表的所有选择列表列均为空值(null)**
2. 右外连接(right outer join)：与左外连接相反。**结果集包括  right OUTER子句中指定的右表的所有行，而不仅仅是连接列所匹配的行。如果右表的某行在左表中没有匹配行，则在结果集的行中左表的所有选择列表列均为空值（null）。**
3. 全外连接(full outer join)：**完整外连接返回左表和右表中的所有行。当某行在另一个表中没有匹配行时，则另一个表的选择列表列包含空值（null）。如果表之间有匹配行，则整个结果集行包含基表的数据值。**

同理：当outer join前面加上natural时，表示相同名称的属性进行匹配

# 交叉连接(cross join)

交叉连接（cross join）用于生成两张表的笛卡尔结果集。

例如，`select * from A , B`语句就是返回笛卡尔结果集，等同于`select * from A cross join B`

注：natural修饰不适用cross join