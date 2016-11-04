package com.github.aoiroaoino.json4s_optics

import monocle.syntax.apply._
import monocle.function.Empty.{_isEmpty, _empty, empty => mempty}

import org.json4s.JsonAST.{JValue, JString, JInt}

class JStringSpec extends TestSuite {
  import jstring._

  val jValue: JValue   = JString("red")
  val jString: JString = JString("blue")
  val other: JValue    = JInt(100)

  it("getOption") {
    _jString.getOption(jValue) shouldEqual Some("red")
  }

  it("set") {
    _jString.set("green")(jValue) shouldEqual JString("green")
  }

  it("modify") {
    _jString.modify(_.toUpperCase)(jValue) shouldEqual JString("RED")
  }

  it("Other JValue") {
    _jString.getOption(other) shouldEqual None
  }

  it("empty") {
    // Empty#_isEmpty
    _isEmpty(jString)     shouldEqual false
    _isEmpty(JString("")) shouldEqual true

    // Empty#_empty
    _empty[JString] shouldEqual JString("")

    // Empty#empty
    (jString     &<-? mempty getOption) shouldEqual None
    (JString("") &<-? mempty getOption) shouldEqual Some(())
  }
}
