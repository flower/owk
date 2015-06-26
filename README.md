# Owk
Owk is a compiled, esoteric, golfing, register-based programming language that runs on the FVM. It is compiled into FVM bytecode, which can be put through the FVM for results.

Owk is parsed line by line. You can make a "fake" line by putting a `;`. Comments start with `#` and are only at the start of a line, whitespace is ignored.

```python
a=7
6=84
#The same as
a = 7; 6 = 84
```

There are 16 registers in total, each marked by their [hexadecimal counterpart](https://en.m.wikipedia.org/wiki/Hexadecimal#Using_0.E2.80.939_and_A.E2.80.93F). To load a number to these registers, we use `=`. (It basically overwrites whatever was in the register before.)

```python
#Loads 8 to register f
f=8
#You can also use capitals
F=8
```

You can also do your normal math operations in Owk using the registers.

```python
#Adds the values of registers a & f and stores it in register e
e=a+f
#Multiplies the values of registers 2 & d and stores it in register f
f=2*d
#Mods the values of registers 8 & 6 and stores it in register 1
1 = 8 % 6
```

The available functions are add `+`, subtract `-`, multiply `*`, divide `/`, mod `%`, AND `&`, OR `|`, XOR `^`, left shift `<`, and right shift `>`.

Since Owk is parsed line by line, you can use the `g` operator to go to a specific line.

```python
#Infinite Loop
f=8;e=6
f=f*e;g1
#The number after g is written in hex
```

Negative numbers can be inputed like normal ones.

```python
#Negative 2
f=-2
#NOT 2
e=!2
#Two's complement 2
d=~2
```

You can also negate registers.

```python
#Negative c
f=-c
#NOT c
e=!c
#Two's complement c
d=~c
```

##Lambdas
Lambdas are a special part of Owk, and how functions are written. Then are notated in [Lambda Calculus](https://en.m.wikipedia.org/wiki/Lambda_calculus), which is different than your normal `(x) -> x`.

```python
square:λx.x*x
add:λx.λy.x+y
```

Each lambda begins with a name, followed by a `:` and the function code. Each `λ` notates a varaible use by the lambda, followed by a `.` and more variables or the function code. Here's a side by side example of a Java method and an Owk lambda:

```java
int add(int x, int y) {
    return x + y;
}
```
```python
add:λx.λy.x+y
```

And a more complicated one:

```java
int math(int x, int y) {
    return x * x + y * y;
}
```

```python
math:λx.λy.x*x+y*y;
```

To use these lambdas, we need to assign the output to a register. It's inputs will be in parentheses.

```python
f=math(e,d)
```
