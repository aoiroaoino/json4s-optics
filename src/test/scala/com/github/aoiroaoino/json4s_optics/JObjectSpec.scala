package com.github.aoiroaoino.json4s_optics

// import monocle.std.map._
import monocle.syntax.apply._
import monocle.function.At._
import monocle.function.Each._
import monocle.function.Empty.{_isEmpty, _empty, empty => mempty}
import monocle.function.Index._
import monocle.function.FilterIndex._

import org.json4s.JsonAST.{JValue, JInt, JString, JObject}

class JObjectSpec extends TestSuite {

  val jValue: JValue   = JObject("first" -> JInt(1), "second" -> JInt(2), "third" -> JInt(3))
  val jObject: JObject = JObject("name" -> JString("AoiroAoino"), "age" -> JInt(25))
  val other: JValue    = JString("string")

  it("each - modify") {
    (_jObject ^|->> each ^<-? _jInt).modify(_ + 100)(jValue) shouldEqual
      JObject("first" -> JInt(101), "second" -> JInt(102), "third" -> JInt(103))
  }

  it("index - set") {
    (_jObject ^|-? index("first") ^<-? _jInt).set(999)(jValue) shouldEqual
      JObject("first" -> JInt(999), "second" -> JInt(2), "third" -> JInt(3))
  }

  it("filterIndex - modify") {
    (_jObject ^|->> filterIndex((_: String).endsWith("d")) ^<-? _jInt).modify(_ * 100)(jValue) shouldEqual
      JObject("first" -> JInt(1), "second" -> JInt(200), "third" -> JInt(300))
  }

  it("Other JValue") {
    (_jObject ^|-? index("first") ^<-? _jInt).set(999)(other) shouldEqual other
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

  it("empty") {
    // Empty#_isEmpty
    _isEmpty(jObject)      shouldEqual false
    _isEmpty(JObject(Nil)) shouldEqual true

    // Empty#_empty
    _empty[JObject] shouldEqual JObject(Nil)

    // Empty#empty
    (jObject      &<-? mempty getOption) shouldEqual None
    (JObject(Nil) &<-? mempty getOption) shouldEqual Some(())
  }
}
