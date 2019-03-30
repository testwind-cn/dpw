



# 如何使用图形 UML





------

这个是直接在URL上写语句，但是GIT、Eclipse不能正常解析空格，Typora能解析

![Alt text](http://g.gravizo.com/g?a->b:hello;b->a:h1w2iw;)


![Alt text](http://g.gravizo.com/svg?a->b:hello;b->a:h1w2iw;)

![Alt text](http://g.gravizo.com/svg?a->b:hello%20d%20d;b->a:h1w2iw;)



![Alt text](http://g.gravizo.com/g?a-->b:how%20are%20you;note%20right:greeting;a->a:i%20am%20thinking;b->%20a:fine;  )





![Alt text](http://g.gravizo.com/g?HTTP-[web%20server];[web%20server]-[app%20server];%20database%20"mysql"{;[database];};[app%20server]-[database];   )

------


[^1]: sdsds
[^n]: sdsdsdsdss
[^7]: 5464646

[^6]: trtr dssd "sds"

------

这个是用 http://www.gravizo.com/#converter 来做的url转换成一行编码

![Alt text](https://g.gravizo.com/svg?digraph%20G%20%7B%0A%20%20%20%20aize%20%3D%224%2C4%22%3B%0A%20%20%20%20main%20%5Bshape%3Dbox%5D%3B%0A%20%20%20%20main%20-%3E%20parse%20%5Bweight%3D8%5D%3B%0A%20%20%20%20parse%20-%3E%20execute%3B%0A%20%20%20%20main%20-%3E%20init%20%5Bstyle%3Ddotted%5D%3B%0A%20%20%20%20main%20-%3E%20cleanup%3B%0A%20%20%20%20execute%20-%3E%20%7B%20make_string%3B%20printf%7D%0A%20%20%20%20init%20-%3E%20make_string%3B%0A%20%20%20%20edge%20%5Bcolor%3Dred%5D%3B%0A%20%20%20%20main%20-%3E%20printf%20%5Bstyle%3Dbold%2Clabel%3D%22100%20times%22%5D%3B%0A%20%20%20%20make_string%20%5Blabel%3D%22make%20a%20string%22%5D%3B%0A%20%20%20%20node%20%5Bshape%3Dbox%2Cstyle%3Dfilled%2Ccolor%3D%22.7%20.3%201.0%22%5D%3B%0A%20%20%20%20execute%20-%3E%20compare%3B%0A%7D )

<details> 
<summary></summary>


```
digraph G {
    aize ="4,4";
    main [shape=box];
    main -> parse [weight=8];
    parse -> execute;
    main -> init [style=dotted];
    main -> cleanup;
    execute -> { make_string; printf}
    init -> make_string;
    edge [color=red];
    main -> printf [style=bold,label="100 times"];
    make_string [label="make a string"];
    node [shape=box,style=filled,color=".7 .3 1.0"];
    execute -> compare;
}
```

</details>

------

这个是用 http://www.gravizo.com/#converter 来做的url转换成一行编码

![Alt text](https://g.gravizo.com/svg?%40startuml%3B%0A(*)%20--%3E%20if%20%22Some%20Test%22%20then%3B%0A%20%20--%3E%5Btrue%5D%20%22activity%201%22%3B%0A%20%20if%20%22%22%20then%3B%0A%20%20%20%20-%3E%20%22activity%203%22%20as%20a3%3B%0A%20%20else%3B%0A%20%20%20%20if%20%22Other%20test%22%20then%3B%0A%20%20%20%20%20%20-left-%3E%20%22activity%205%22%3B%0A%20%20%20%20else%3B%0A%20%20%20%20%20%20--%3E%20%22activity%206%22%3B%0A%20%20%20%20endif%3B%0A%20%20endif%3B%0Aelse%3B%0A%20%20-%3E%5Bfalse%5D%20%22activity%202%22%3B%0Aendif%3B%0A%0Aa3%20--%3E%20if%20%22last%20test%22%20then%3B%0A%20%20--%3E%20%22activity%207%22%3B%0Aelse%3B%0A%20%20-%3E%20%22activity%208%22%3B%0Aendif%3B%0A%40enduml)


<details> 
<summary></summary>

```
没有;的话，gravizo不能识别。有;的话，PlantUML不能解析
@startuml;
(*) --> if "Some Test" then;
  -->[true] "activity 1";
  if "" then;
    -> "activity 3" as a3;
  else;
    if "Other test" then;
      -left-> "activity 5";
    else;
      --> "activity 6";
    endif;
  endif;
else;
  ->[false] "activity 2";
endif;

a3 --> if "last test" then;
  --> "activity 7";
else;
  -> "activity 8";
endif;
@enduml

```
</details>



------

这和上面一样，去掉分号，是把数据存在 github 的 README.md 中

01

![Alt text](https://g.gravizo.com/source/svg/wjmark01?https%3A%2F%2Fraw.githubusercontent.com%2Ftestwind-cn%2Fdpw%2Fmaster/README.md  )

<details> 
<summary></summary>

```
没有;的话，gravizo不能识别。有;的话，PlantUML不能解析
wjmark01
@startuml
(*) --> if "Some Test" then
  -->[true] "activity 1"
  if "" then
    -> "activity 3" as a3
  else
    if "Other test" then
      -left-> "activity 5"
    else
      --> "activity 6"
    endif
  endif
else
  ->[false] "activity 2"
endif

a3 --> if "last test" then
  --> "activity 7"
else
  -> "activity 8"
endif
@enduml
wjmark01

```
</details>


------




这些是把数据存在 github 的 data.uml 中

2

![Alt text](https://g.gravizo.com/source/svg/wjmark02?https://raw.githubusercontent.com/testwind-cn/dpw/master/data.uml )

3

![Alt text](https://g.gravizo.com/source/svg/wjmark03?https://raw.githubusercontent.com/testwind-cn/dpw/master/data.uml )

4

![Alt text](https://g.gravizo.com/source/svg/wjmark04?https://raw.githubusercontent.com/testwind-cn/dpw/master/data.uml )

5

![Alt text](https://g.gravizo.com/source/svg/wjmark05?https://raw.githubusercontent.com/testwind-cn/dpw/master/data.uml )

------

这些是把数据存在 github 的 README.md 中

16

![Alt text](https://g.gravizo.com/source/svg/wjmark16?https%3A%2F%2Fraw.githubusercontent.com%2Ftestwind-cn%2Fdpw%2Fmaster/README.md  )


24

![Alt text](https://g.gravizo.com/source/svg/wjmark24?https%3A%2F%2Fraw.githubusercontent.com%2Ftestwind-cn%2Fdpw%2Fmaster/README.md )

26

![Alt text](https://g.gravizo.com/source/svg/wjmark26?https%3A%2F%2Fraw.githubusercontent.com%2Ftestwind-cn%2Fdpw%2Fmaster/README.md )


35

![Alt text](https://g.gravizo.com/source/svg/wjmark35?https%3A%2F%2Fraw.githubusercontent.com%2Ftestwind-cn%2Fdpw%2Fmaster/README.md )


38

![Alt text](https://g.gravizo.com/source/svg/wjmark38?https%3A%2F%2Fraw.githubusercontent.com%2Ftestwind-cn%2Fdpw%2Fmaster/README.md )


39

![Alt text](https://g.gravizo.com/source/svg/wjmark39?https%3A%2F%2Fraw.githubusercontent.com%2Ftestwind-cn%2Fdpw%2Fmaster/README.md )

50

![Alt text](https://g.gravizo.com/source/svg/wjmark50?https%3A%2F%2Fraw.githubusercontent.com%2Ftestwind-cn%2Fdpw%2Fmaster/README.md )


51

![Alt text](https://g.gravizo.com/source/svg/wjmark51?https%3A%2F%2Fraw.githubusercontent.com%2Ftestwind-cn%2Fdpw%2Fmaster/README.md )

------

<details> 
<summary></summary>

https://g.gravizo.com/source/svg/wjmark26?https://raw.githubusercontent.com/testwind-cn/dpw/master/README.md

```


wjmark16
@startuml
left to right direction
skinparam packageStyle Folder
actor customer
actor chef
package restaurant {
    customer ->(eat food)
    customer -> (pay for food)
    chef -> (cook food)
}
@enduml
wjmark16







DOT

wjmark24
digraph G {
    aize ="4,4";
           主程序 [shape=box];
           主程序 -> 处理 [weight=8];
            处理 -> 执行;
           主程序 -> init [style=dotted];
           主程序 -> cleanup;
             执行 -> { make_string; printf}
    init -> make_string;
    edge [color=red];
           主程序 -> printf [style=bold,label="100 times"];
    make_string [label="make a string"];
    node [shape=box,style=filled,color=".7 .3 1.0"];
             执行 -> compare;
  }
wjmark24



wjmark26
@startuml
class Dummy {
- private field1
# protected field2
+ public field3
~ package method13()
- private method1237()
# protected method4()
+ public method2()
}
@enduml
wjmark26






wjmark35
@startuml
object Object01
object Object02
object Object03
object Object04
object Object05
object Object06
object Object07我
object Object08

Object01 <|-- Object02
Object03 *-- Object04
Object05 o-- "5" Object06
Object07我 .. Object08 : "some 我labels"
@enduml
wjmark35



wjmark37
@startuml

(*) --> if "Some Test" then

  -->[true] "activity 1"

  if "" then
    -> "activity 3" as a3
  else
    if "Other test" then
      -left-> "activity 5"
    else
      --> "activity 6"
    endif
  endif

else

  ->[false] "activity 2"

endif

a3 --> if "last test" then
  --> "activity 7"
else
  -> "activity 8"
endif

@enduml 
wjmark37




http://blog.csdn.net/u010111422/article/details/69568947
PlantUML（程序员绘制流程图专用工具）

wjmark38
@startuml

title 流程图

(*) --> "步骤1处理"
--> "步骤2处理"
if "条件1判断" then
    ->[true] "条件1成立时执行的动作"
    if "分支条件2判断" then
        ->[no] "条件2不成立时执行的动作"
        -> === 中间流程汇总点1 ===
    else
        -->[yes] === 中间流程汇总点1 ===
    endif
    if "分支条件3判断" then
        -->[yes] "分支条件3成立时执行的动作"
        --> "Page.onRender ()" as render
        --> === REDIRECT_CHECK ===
    else
        -->[no] "分支条件3不成立时的动作"
        --> render
    endif
else
    -->[false] === REDIRECT_CHECK ===
endif

if "条件4判断" then
    ->[yes] "条件4成立时执行的动作"
    --> "流程最后结点"
else
endif
--> "流程最后结点"
-->(*)

@enduml
wjmark38


wjmark39

@startuml
start
:"步骤1处理";
:"步骤2处理";
if ("条件1判断") then (true)
    :条件1成立时执行的动作;
    if ("分支条件2判断") then (no)
        :"条件2不成立时执行的动作";
    else
        if ("条件3判断") then (yes)
            :"条件3成立时的动作";
        else (no)
            :"条件3不成立时的动作";
        endif
    endif
    :"顺序步骤3处理";
endif

if ("条件4判断") then (yes)
:"条件4成立的动作";
else
    if ("条件5判断") then (yes)
        :"条件5成立时的动作";
    else (no)
        :"条件5不成立时的动作";
    endif
endif
stop
@enduml
wjmark39






wjmark50
@startuml

title 我测试流程图

(*) --> "提交身份证"
--> "提交银行卡"
if "实名验证结果" then
    ->[true] "提示用户验证成功"
    if "短信验证码判断" then
        ->[no] "验证码错误提示"
        -> === 中间流程汇总点1 ===
    else
        -->[yes] === 中间流程汇总点1 ===
    endif
    if "分支条件3判断" then
        -->[yes] "分支条件3成立时执行的动作"
        --> "Page.onRender ()" as render
        --> === REDIRECT_CHECK ===
    else
        -->[no] "分支条件3不成立时的动作"
        --> render
    endif
else
    -->[false] === REDIRECT_CHECK ===
endif

if "条件4判断" then
    ->[yes] "条件4成立时执行的动作"
    --> "流程最后结点"
else
endif
--> "流程最后结点"
-->(*)

@enduml
wjmark50





wjmark51
@startuml

left to right direction
skinparam packageStyle rectangle


	actor :商户-买方: as Buyer	
	actor :平台-卖方: as Saler
	actor :金融机构-资金方: as Capital
	


	rectangle 1.采购-申请借款 {
		usecase (支付订单15%首付款) as UC1_pay
		usecase (按订单金额85%\n申请借款) as UC2_borrow

	}
	
	rectangle 2.批准放款 {
		usecase (贷款批准) as UC3_approve
		usecase (收货确认) as UC4_cofirm
		usecase (按订单金额85%放款\n+扣除全部利息的首付款) as UC5_lend
	}
	
	usecase (发货) as UC6_Sale
	
	
	rectangle 3.还款 {
		usecase (分期等额还本金、\n（若有）罚息) as UC7_Return
	}



	
	Buyer --> (UC1_pay) : s1
	Buyer --> (UC2_borrow) : s2
	(UC1_pay) -->  Capital: s3
	(UC2_borrow) --> Capital  : s4
	Capital	 --> (UC3_approve) : s5
	(UC3_approve) --> Saler : s6
	
	  Saler -> (UC6_Sale): s7
	 (UC6_Sale) -> Buyer: s8
	
	Buyer --> (UC4_cofirm): s9
	(UC4_cofirm) --> Capital: s10
	
	Capital	 --> (UC5_lend) : s11
	(UC5_lend) --> Saler: s12
	
	
	
	
	
	Buyer --> (UC7_Return) : s13
	(UC7_Return)  -->  Capital: s14
	




@enduml
wjmark51


wjmark52
@startuml
actor “商户-买方” as Foo1
actor “平台-卖方” as Foo2
database “金融机构-资金方” as Foo3

autonumber
== 商户注册  ==
Foo1 -[#0000FF]> Foo2 : 申请“分期购”补齐商户注册信息
activate Foo1
activate Foo2
Foo2 -[#008000]-> Foo3 : 后台自动为商户开户
activate Foo3
Foo3 -[#008000]-> Foo2 : 返回商户开户账号
deactivate Foo2
Foo3 -[#008000]-> Foo1 : 短信发送商户通华平台初始密码
deactivate Foo1
deactivate Foo3

== 贷款申请  ==
Foo1 -[#0000FF]> Foo2 : 确认选购商品并使用“分期购”
activate Foo1
activate Foo2
activate Foo3
Foo2 -[#0000FF]> Foo3 : 发送：商户号、订单号、商品清单、总金额、首付金额
deactivate Foo2
Foo3 -[#0000FF]> Foo1 : 展示贷款合同、订单号、商品清单、总金额、首付金额
Foo1 -[#0000FF]> Foo3 : 确认贷款合同、填写密码或短信验证码、支付首付
deactivate Foo1

== 贷款批准  ==
Foo3 -> Foo2 : 确认收到首付，批准贷款
activate Foo2

Foo3 -[#008000]-> Foo1 : 短信通知商户贷款批准
activate Foo1
== 放款  ==
Foo2 -> Foo1 : 商品发货

Foo1 -> Foo3 : 确认收货
deactivate Foo1
Foo3 -> Foo2 : 放款
deactivate Foo2

Foo3 -[#008000]-> Foo1 : 短信通知商户贷款情况
activate Foo1
== 还款  ==

Foo1 -[#0000FF]> Foo3 : 登录商户平台或通华平台还款
deactivate Foo1
deactivate Foo3

@enduml
wjmark52

```
left to right direction



@startuml
:First Actor:
:Another\nactor: as Men2
actor Men3
actor :Last actor: as Men4
@enduml






</details>







