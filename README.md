



# 如何使用图形 UML





![Alt text](http://g.gravizo.com/g?a->b:hello;b->a:h1w2iw;)


![Alt text](http://g.gravizo.com/svg?a->b:hello;b->a:h1w2iw;)

![Alt text](http://g.gravizo.com/svg?a->b:hello d d;b->a:h1w2iw;)



![Alt text](http://g.gravizo.com/g?    a --> b: how are you;note right: greeting;a -> a: i am thinking;b -> a: fine;  )



![Alt text](http://g.gravizo.com/g? left to right direction; skinparam packageStyle rect;actor customer;actor chef;rectangle restaurant {customer ->(eat food);customer -> (pay for food);chef -> (cook food);}  )




![Alt text](http://g.gravizo.com/g?  (*) --> "buy 10 apples"; if "is there watermelon " then; -->[true] "buy a apple"; -right-> (*); else; ->[false] "Something else"; -->(*); endif;  )


![Alt text](http://g.gravizo.com/g?  HTTP - [web server];  [web server] - [app server];  database "mysql" {;  [database];  }; [app server] - [database];   )

[^1]: sdsds
[^n]: sdsdsdsdss
[^7]: 5464646

[^6]: trtr dssd "sds"

------



![Alt text](https://g.gravizo.com/svg?digraph%20G%20%7B%0Aaize%20%3D%224%2C4%22%3B%0Amain%20%5Bshape%3Dbox%5D%3B%0Amain%20-%3E%20parse%20%5Bweight%3D8%5D%3B%0Aparse%20-%3E%20execute%3B%0Amain%20-%3E%20init%20%5Bstyle%3Ddotted%5D%3B%0Amain%20-%3E%20cleanup%3B%0Aexecute%20-%3E%20%7B%20make_string%3B%20printf%7D%0Ainit%20-%3E%20make_string%3B%0Aedge%20%5Bcolor%3Dred%5D%3B%0Amain%20-%3E%20printf%20%5Bstyle%3Dbold%2Clabel%3D%22100%20times%22%5D%3B%0Amake_string%20%5Blabel%3D%22make%20a%20string%22%5D%3B%0Anode%20%5Bshape%3Dbox%2Cstyle%3Dfilled%2Ccolor%3D%22.7%20.3%201.0%22%5D%3B%0Aexecute%20-%3E%20compare%3B%0A%7D )


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

------

![Alt text](https://g.gravizo.com/svg?%40startuml%3B%0A(*)%20--%3E%20if%20%22Some%20Test%22%20then%3B%0A%20%20--%3E%5Btrue%5D%20%22activity%201%22%3B%0A%20%20if%20%22%22%20then%3B%0A%20%20%20%20-%3E%20%22activity%203%22%20as%20a3%3B%0A%20%20else%3B%0A%20%20%20%20if%20%22Other%20test%22%20then%3B%0A%20%20%20%20%20%20-left-%3E%20%22activity%205%22%3B%0A%20%20%20%20else%3B%0A%20%20%20%20%20%20--%3E%20%22activity%206%22%3B%0A%20%20%20%20endif%3B%0A%20%20endif%3B%20%20%20%20%0Aelse%3B%20%20%20%20%0A%20%20-%3E%5Bfalse%5D%20%22activity%202%22%3B%20%20%20%20%0Aendif%3B%20%20%20%20%0Aa3%20--%3E%20if%20%22last%20test%22%20then%3B%0A%20%20--%3E%20%22activity%207%22%3B%0Aelse%3B%0A%20%20-%3E%20%22activity%208%22%3B%0Aendif%3B%20%20%20%20%0A%40enduml%20)

<details> 
<summary></summary>
```
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
```
</details>

![Alt text](http://www.gravizo.com/img/1x1.png#)

------



2

![Alt text](https://g.gravizo.com/source/svg/thiisthemark2?https://raw.githubusercontent.com/testwind-cn/dpw/master/data.uml )

3

![Alt text](https://g.gravizo.com/source/svg/thiisthemark3?https://raw.githubusercontent.com/testwind-cn/dpw/master/data.uml )

4
![Alt text](https://g.gravizo.com/source/svg/thiisthemark4?https://raw.githubusercontent.com/testwind-cn/dpw/master/data.uml )

5
![Alt text](https://g.gravizo.com/source/svg/thiisthemark5?https://raw.githubusercontent.com/testwind-cn/dpw/master/data.uml )

------

16

![Alt text](https://g.gravizo.com/source/svg/thiisthemark16?https%3A%2F%2Fraw.githubusercontent.com%2Ftestwind-cn%2Fdpw%2Fmaster/README.md  )

26
![Alt text](https://g.gravizo.com/source/svg/thiisthemark26?https%3A%2F%2Fraw.githubusercontent.com%2Ftestwind-cn%2Fdpw%2Fmaster/README.md )


35
![Alt text](https://g.gravizo.com/source/svg/thiisthemark35?https%3A%2F%2Fraw.githubusercontent.com%2Ftestwind-cn%2Fdpw%2Fmaster/README.md )

------

<details> 
<summary></summary>
https://g.gravizo.com/source/svg/thiisthemark26?https://raw.githubusercontent.com/testwind-cn/dpw/master/README.md


```

thiisthemark16
left to right direction; 
skinparam packageStyle rect;
actor customer;actor chef;
rectangle restaurant {customer ->(teat food);
customer -> (pay for food);
chef -> (cook food);}  

thiisthemark16

thiisthemark26
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
thiisthemark26






thiisthemark35
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
thiisthemark35

```

</details>







