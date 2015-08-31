package com.github.aoiroaoino.json4s.optics

import monocle.std.list._
import monocle.syntax.apply._
import monocle.function.Each._
import monocle.function.Empty.{_isEmpty, _empty, empty => mempty}
import monocle.function.Index._

import org.json4s.JsonAST.{JValue, JInt, JString, JArray}

import com.github.aoiroaoino.json4s.optics.JArrayOptics._
import com.github.aoiroaoino.json4s.optics.JStringOptics._

class JArrayOpticsSpec extends TestSuite {

  val jValue: JValue = JArray(List(JString("a"), JString("b"), JString("c")))
  val jArray: JArray = JArray(List(JInt(1), JInt(2), JInt(3)))
  val other: JValue  = JInt(100)

  it("each - modify") {
    (_jArray ^|->> each ^<-? _jString).modify(_.toUpperCase)(jValue) shouldEqual
      JArray(List(JString("A"), JString("B"), JString("C")))
  }

  it("index - getOption") {
    (_jArray ^|-? index(2) ^<-? _jString).getOption(jValue) shouldEqual Some("c")
  }

  it("Other JValue") {
    (_jArray ^|->> each ^<-? _jString).modify(_.toUpperCase)(other) shouldEqual other
  }

  it("empty") {
    // Empty#_isEmpty
    _isEmpty(jArray)      shouldEqual false
    _isEmpty(JArray(Nil)) shouldEqual true

    // Empty#_empty
    _empty[JArray] shouldEqual JArray(Nil)

    // Empty#empty
    (jArray      &<-? mempty getOption) shouldEqual None
    (JArray(Nil) &<-? mempty getOption) shouldEqual Some(())
  }
}
