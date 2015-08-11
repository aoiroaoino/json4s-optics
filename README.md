# Json4s Optics

[![Build Status](https://travis-ci.org/aoiroaoino/json4s-optics.svg?branch=master)](https://travis-ci.org/aoiroaoino/json4s-optics)

## What is Json4s Optics?

The Json4s Optics is providing optical operation for Json4s by Monocle.

## Quick Start

You need the following imports.
```scala
import org.json4s.JsonAST._
import monocle._, Monocle._
import com.github.aoiroaoino.json4s.optics.AllOptics._
```

If you have the following json value.
```scala
val json: JValue = JObject(
  "name"      -> JString("aoino"),
  "age"       -> JInt(25),
  "favorites" -> JArray(List(JString("scala"), JString("haskell")))
)
```

Get age value.
```scala
json applyPrism jObjectPrism composeOptional index("age") composePrism jIntPrism getOption
//=> Some(25)
```

Capitalize name.
```scala
json applyPrism jObjectPrism composeOptional index("name") composePrism jStringPrism modify(_.capitalize)
//=> JObject("name" -> JString("Aoino"), "age" -> ...)
```

Of cource you can also use alias methods.
```scala
json &<-? jObjectPrism ^|-? index("name") ^<-? jStringPrism modify(_.capitalize)
//=> same as above
```

And easy traverse.
```scala
json &<-? jObjectPrism ^|-? index("favorites") ^<-? jArrayPrism ^|->> each ^<-? jStringPrism modify(_.capitalize)
//=> JObject("name" -> ..., "favorites" -> JArray(List(JString("Scala"), JString("Haskell"))))
```

If you want to more samples, please look [here!](https://github.com/aoiroaoino/json4s-optics/tree/master/src/test/scala/com/github/aoiroaoino/json4s/optics)

