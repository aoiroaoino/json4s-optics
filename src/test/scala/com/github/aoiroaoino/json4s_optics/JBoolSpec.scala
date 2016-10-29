package com.github.aoiroaoino.json4s_optics

import org.json4s.JsonAST.{JValue, JBool, JInt}

class JBoolSpec extends TestSuite {
  import jbool._

  val jValue: JValue = JBool(true)
  val jBool: JBool   = JBool(false)
  val other: JValue  = JInt(100)

  it("getOption") {
    _jBool.getOption(jValue) shouldEqual Some(true)
  }

  it("set") {
    _jBool.set(false)(jValue) shouldEqual JBool(false)
  }

  it("modify") {
    _jBool.modify(!_)(jValue) shouldEqual JBool(false)
  }

  it("Other JValue") {
    _jBool.getOption(other) shouldEqual None
  }

  it("Lens") {
    jBoolLens.get(jBool)       shouldEqual false
    jBoolLens.set(true)(jBool) shouldEqual JBool(true)
  }
}
