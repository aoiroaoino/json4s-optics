package com.github.aoiroaoino.json4s.optics

import monocle.syntax.apply._
import monocle.function.Empty.{_isEmpty, _empty, empty => mempty}

import org.json4s.JsonAST.{JValue, JInt, JNull}

class JNullSpec extends TestSuite {

  val jValue: JValue = JNull
  val other: JValue  = JInt(100)

  it("JNullOptics#_jNull") {
    _jNull.getOption(jValue) shouldEqual Some(JNull)
  }

  it("Other JValue") {
    _jNull.getOption(other) shouldEqual None
  }

  it("empty") {
    // Empty#_isEmpty - always true
    _isEmpty(JNull) shouldEqual true

    // Empty#_empty
    _empty[JNull.type] shouldEqual JNull

    // Empty#empty - always Some(())
    (JNull  &<-? mempty getOption) shouldEqual Some(())
  }
}
