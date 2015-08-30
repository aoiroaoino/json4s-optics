package com.github.aoiroaoino.json4s.optics

import org.json4s.JsonAST.{JValue, JString, JInt}
import com.github.aoiroaoino.json4s.optics.JStringOptics._

class JStringOpticsSpec extends TestSuite {

  val jString: JValue = JString("red")
  val other: JValue = JInt(100)

  it("getOption") {
    _jString.getOption(jString) shouldEqual Some("red")
  }

  it("set") {
    _jString.set("green")(jString) shouldEqual JString("green")
  }

  it("modify") {
    _jString.modify(_.toUpperCase)(jString) shouldEqual JString("RED")
  }

  it("Other JValue") {
    _jString.getOption(other) shouldEqual None
  }
}
