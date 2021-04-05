# u4j
## Unix4Java - use Unix text-processing tools in Java
---

### Overview

This project aims to allow Java programmers who like Unix/Linux command-line
tools to use the same interfaces in Java programs. It will consist of a Java API
used to manipulate text in a functional, line-oriented paradigm, and a
transpiler that lets you write shell pipelines and then convert it to Java
source code that uses the API.

The Java API will aim to implement as much of the interfaces and functionality
as possible of standard Unix/Linux text utilities, specifically the GNU
coreutils implementations.

### Roadmap
When everything is done, we will be able to write things like this...
```
`cat billyquotes.txt              | \
    grep amends                   | \
        sed "s/amends/almonds/g"  | \
            tail -1`
```
and transpile it to this...
```
cat("billyquotes.txt")
    .grep("amends")
    .sed("s", "amends", "almonds", "g")
    .tail(1)
    .toString()
```
which will read a file, perform a text search, replace substrings in matched
lines, and return the last line of the results.

 - NOTE: The Java API part is currently subject to change as I iron out the details.

This project may or may not turn into something actually useful, but it will be
fun to work on, hopefully.

### Contributing
Contributions are very welcome! I will review PRs and probably merge anything
that looks remotely readable.

The most immediate way you can help is to add operations on `TextStream`. Just
make sure everything lines up with `coreutils` (or related) tools' interfaces.

### Current status and todos
The `TextStream` class is operational, with a few operations already implemented
(`head`, `grep`, `cat`). There is also `TextStreamProducers`, which generates
`TextStream`s (`cat`, `echo`).

Right now we need documentation, and probably lots of other things.


