package com.github.aoiroaoino.json4s.optics

import monocle.std.list._
import monocle.function.Each._
import monocle.function.Index._

import org.json4s.JsonAST.{JValue, JInt, JString, JArray}

import com.github.aoiroaoino.json4s.optics.JArrayOptics._
import com.github.aoiroaoino.json4s.optics.JStringOptics._

class JArrayOpticsSpec extends TestSuite {

  val jArray: JValue = JArray(List(JString("a"), JString("b"), JString("c")))
  val other: JValue  = JInt(100)

  it("each - modify") {
    (_jArray ^|->> each ^<-? jStringPrism).modify(_.toUpperCase)(jArray) shouldEqual
      JArray(List(JString("A"), JString("B"), JString("C")))
  }

  it("index - getOption") {
    (_jArray ^|-? index(2) ^<-? jStringPrism).getOption(jArray) shouldEqual Some("c")
  }

  it("Other JValue") {
    (_jArray ^|->> each ^<-? jStringPrism).modify(_.toUpperCase)(other) shouldEqual other
  }
}
