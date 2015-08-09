package com.github.aoiroaoino.json4s.optics

import monocle.std.map._
import monocle.syntax.apply._
import monocle.function.Each._
import monocle.function.Index._
import monocle.function.FilterIndex._

import org.json4s.JsonAST.{JValue, JInt, JString, JObject}

import com.github.aoiroaoino.json4s.optics.JObjectOptics._
import com.github.aoiroaoino.json4s.optics.JNumberOptics._

class JObjectOpticsSpec extends TestSuite {

  val jObject: JValue = JObject("first" -> JInt(1), "second" -> JInt(2), "third" -> JInt(3))
  val other: JValue   = JString("string")

  it("each - modify") {
    (jObjectPrism ^|->> each ^<-? jIntPrism).modify(_ + 100)(jObject) shouldEqual
      JObject("first" -> JInt(101), "second" -> JInt(102), "third" -> JInt(103))
  }

  it("index - set") {
    (jObjectPrism ^|-? index("first") ^<-? jIntPrism).set(999)(jObject) shouldEqual
      JObject("first" -> JInt(999), "second" -> JInt(2), "third" -> JInt(3))
  }

  it("filterIndex - modify") {
    (jObjectPrism ^|->> filterIndex((_: String).endsWith("d")) ^<-? jIntPrism).modify(_ * 100)(jObject) shouldEqual
      JObject("first" -> JInt(1), "second" -> JInt(200), "third" -> JInt(300))
  }

  it("Other JValue") {
    (jObjectPrism ^|-? index("first") ^<-? jIntPrism).set(999)(other) shouldEqual other
  }
}
