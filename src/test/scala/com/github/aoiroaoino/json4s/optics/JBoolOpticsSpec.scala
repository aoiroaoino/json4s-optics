package com.github.aoiroaoino.json4s.optics

import org.json4s.JsonAST.{JValue, JBool, JInt}
import com.github.aoiroaoino.json4s.optics.JBoolOptics._

class JBoolOpticsSpec extends TestSuite {

  val jBool: JValue = JBool(true)
  val other: JValue = JInt(100)

  it("getOption") {
    jBoolPrism.getOption(jBool) shouldEqual Some(true)
  }

  it("set") {
    jBoolPrism.set(false)(jBool) shouldEqual JBool(false)
  }

  it("modify") {
    jBoolPrism.modify(!_)(jBool) shouldEqual JBool(false)
  }

  it("Other JValue") {
    jBoolPrism.getOption(other) shouldEqual None
  }
}
