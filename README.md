# Json4s Optics

[![Build Status](https://travis-ci.org/aoiroaoino/json4s-optics.svg?branch=master)](https://travis-ci.org/aoiroaoino/json4s-optics)

## What is Json4s Optics?

The Json4s Optics is providing optical operation for Json4s by Monocle.

## Quick Start

You need the following imports.
```scala
// Json4s
import org.json4s.JsonAST._

// Monocle
import monocle._, Monocle._

// This library
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
json applyPrism _jObject composeOptional index("age") composePrism _jInt getOption
//=> Some(25)
```

Capitalize name.
```scala
json applyPrism _jObject composeOptional index("name") composePrism _jString modify(_.capitalize)
//=> JObject("name" -> JString("Aoino"), "age" -> ...)
```

Of cource you can also use alias methods.
```scala
json &<-? _jObject ^|-? index("name") ^<-? _jString modify(_.capitalize)
//=> same as above
```

And easy traverse.
```scala
json &<-? _jObject ^|-? index("favorites") ^<-? _jArray ^|->> each ^<-? _jString modify(_.capitalize)
//=> JObject("name" -> ..., "favorites" -> JArray(List(JString("Scala"), JString("Haskell"))))
```

If you want to more samples, please look [here!](https://github.com/aoiroaoino/json4s-optics/tree/master/test/src/test/scala/com/github/aoiroaoino/json4s/optics)

