package com.github.aoiroaoino.json4s.optics

import monocle.std.map._
import monocle.syntax.apply._
import monocle.function.At._
import monocle.function.Each._
import monocle.function.Index._
import monocle.function.FilterIndex._

import org.json4s.JsonAST.{JValue, JInt, JString, JObject}

import com.github.aoiroaoino.json4s.optics.JObjectOptics._
import com.github.aoiroaoino.json4s.optics.JNumberOptics._

class JObjectOpticsSpec extends TestSuite {

  val jValue: JValue   = JObject("first" -> JInt(1), "second" -> JInt(2), "third" -> JInt(3))
  val jObject: JObject = JObject("name" -> JString("AoiroAoino"), "age" -> JInt(25))
  val other: JValue    = JString("string")

  it("each - modify") {
    (jObjectPrism ^|->> each ^<-? jIntPrism).modify(_ + 100)(jValue) shouldEqual
      JObject("first" -> JInt(101), "second" -> JInt(102), "third" -> JInt(103))
  }

  it("index - set") {
    (jObjectPrism ^|-? index("first") ^<-? jIntPrism).set(999)(jValue) shouldEqual
      JObject("first" -> JInt(999), "second" -> JInt(2), "third" -> JInt(3))
  }

  it("filterIndex - modify") {
    (jObjectPrism ^|->> filterIndex((_: String).endsWith("d")) ^<-? jIntPrism).modify(_ * 100)(jValue) shouldEqual
      JObject("first" -> JInt(1), "second" -> JInt(200), "third" -> JInt(300))
  }

  it("Other JValue") {
    (jObjectPrism ^|-? index("first") ^<-? jIntPrism).set(999)(other) shouldEqual other
  }

  it("at - get") {
    (jObject &|-> at("name") get) shouldEqual Some(JString("AoiroAoino"))
    (jObject &|-> at("email") get) shouldEqual None
  }

  it("at - set") {
    jObject &|-> at("name") set Some(JString("Aoino")) shouldEqual
      JObject("name" -> JString("Aoino"), "age" -> JInt(25))

    jObject &|-> at("address") set Some(JString("Tokyo")) shouldEqual
      JObject("name" -> JString("AoiroAoino"), "age" -> JInt(25), "address" -> JString("Tokyo"))

    jObject &|-> at("age") set None shouldEqual
      JObject("name" -> JString("AoiroAoino"))

    jObject &|-> at("email") set None shouldEqual jObject
  }
}
