package com.github.aoiroaoino.json4s_optics

import org.json4s.JsonAST.{JValue, JNumber, JDouble, JDecimal, JInt, JBool}

class JNumberSpec extends TestSuite {

  val jDouble: JValue  = JDouble(1.1)
  val jDecimal: JValue = JDecimal(1.1)
  val jInt: JValue     = JInt(10)
  val other: JValue    = JBool(true)

  describe("JDouble") {
    it("getOption") {
      _jDouble.getOption(jDouble) shouldEqual Some(1.1)
    }

    it("set") {
      _jDouble.set(1.5)(jDouble) shouldEqual JDouble(1.5)
    }

    it("modify") {
      _jDouble.modify(_ + 0.5)(jDouble) shouldEqual JDouble(1.6)
    }

    it("Other JValue") {
      _jDouble.getOption(other) shouldEqual None
    }

    it("Lens") {
      val n = JDouble(0.5)
      jDoubleLens.get(n)      shouldEqual 0.5
      jDoubleLens.set(1.0)(n) shouldEqual JDouble(1.0)
    }
  }

  describe("JDecimal") {
    it("getOption") {
      _jDecimal.getOption(jDecimal) shouldEqual Some(1.1)
    }

    it("set") {
      _jDecimal.set(BigDecimal(1.5))(jDecimal) shouldEqual JDecimal(1.5)
    }

    it("modify") {
      _jDecimal.modify(_ + 0.5)(jDecimal) shouldEqual JDecimal(1.6)
    }

    it("Other JValue") {
      _jDecimal.getOption(other) shouldEqual None
    }

    it("Lens") {
      val n = JDecimal(0.5)
      jDecimalLens.get(n)      shouldEqual 0.5
      jDecimalLens.set(1.0)(n) shouldEqual JDecimal(1.0)
    }
  }

  describe("JInt") {
    it("getOption") {
      _jInt.getOption(jInt) shouldEqual Some(10)
    }

    it("set") {
      _jInt.set(5)(jInt) shouldEqual JInt(5)
    }

    it("modify") {
      _jInt.modify(_ + 10)(jInt) shouldEqual JInt(20)
    }

    it("Other JValue") {
      _jInt.getOption(other) shouldEqual None
    }

    it("Lens") {
      val n = JInt(5)
      jIntLens.get(n)    shouldEqual 5
      jIntLens.set(1)(n) shouldEqual JInt(1)
    }
  }
}
