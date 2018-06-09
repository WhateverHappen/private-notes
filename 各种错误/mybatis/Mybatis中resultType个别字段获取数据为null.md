出现Mybatis中resultType个别字段获取数据为null，其他字段正常。

这是bean中获取不到数据的字段：

```
private String ctBase_ddl; //订单量

private String ctBase_yqphd; //邀请配合度

private String ctBase_dv; // 大V
```

数据库对应的字段：

`ct_base_ddl,ct_base_yqphd,ct_base_dv`

经过多次尝试后，发现不使用resultType而是通过**resultMap**完成结果映射，这样bean中字段名带_符号也不会出现这个问题。

bean中获取不到数据的字段： 


private String ctBase_ddl; //订单量
private String ctBase_yqphd; //邀请配合度
private String ctBase_dv; // 大V

数据库对应的字段：

ctBase_ddl，ctBase_yqphd，ctBase_dv  

建立resultMap：

    <resultMap type="Person" id="result_person">
    	<result property="ctBase_ddl" column="ctBase_ddl"/>
    	<result property="ctBase_yqphd" column="ctBase_yqphd"/>
    	<result property="ctBase_dv" column="ctBase_dv"/>
    </resultMap>  

