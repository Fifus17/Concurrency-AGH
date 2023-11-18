# Trace Theory Assignment

Solves tasks related to concurrency assignment.

## Table of Contents

- [Introduction](#introduction)
- [How to Run](#how-to-run)
- [Examples](#examples)
    - [Example 1](#example-1)
    - [Example 2](#example-2)

## Introduction

The program accomplishes the following tasks:

1. Determine the dependency relation D.
2. Determine the independence relation I.
3. Determine the Foata Normal Form FNF([w]) of trace [w].
4. Draw the dependency graph in minimal form for the word w.

## How to Run

To run the program with Maven, use the following command:

```bash
mvn exec:java -Dexec.mainClass="main.kotlin.Main" -Dexec.args="(a)x:=x+1 (b)y:=y+2z (c)x:=3x+z (d)w:=w+v (e)z:=y-z (f)v:=x+v A={a,b,c,d,e,f} w=acdcfbbe n=example3"
```
Replace `-Dexec.args` your own set of transactions, word **w**, alphabet **A** and optionally a name **n** of the file where the graph should be saved to.

Program comes with two examples. To try them, uncomment the example you want to run and comment the second. When you run the program with your own data, one example must be uncommented nevertheless.

## Examples

Example data and results are shown below.

### Example 1
```bash
mvn exec:java -Dexec.mainClass="main.kotlin.Main" -Dexec.args="(a)x:=x+y (b)y:=y+2z (c)x:=3x+z (d)z:=y-z A={a,b,c,d} w=baadcb n=example1"
```

```bash
$ cat output.txt
```
