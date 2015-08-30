package com.github.aoiroaoino.json4s.optics

import org.json4s.JsonAST.{JValue, JBool, JInt}
import com.github.aoiroaoino.json4s.optics.JBoolOptics._

class JBoolOpticsSpec extends TestSuite {

  val jBool: JValue = JBool(true)
  val other: JValue = JInt(100)

  it("getOption") {
    _jBool.getOption(jBool) shouldEqual Some(true)
  }

  it("set") {
    _jBool.set(false)(jBool) shouldEqual JBool(false)
  }

  it("modify") {
    _jBool.modify(!_)(jBool) shouldEqual JBool(false)
  }

  it("Other JValue") {
    _jBool.getOption(other) shouldEqual None
  }
}
