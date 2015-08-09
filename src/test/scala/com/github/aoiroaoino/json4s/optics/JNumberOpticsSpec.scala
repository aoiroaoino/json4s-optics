package com.github.aoiroaoino.json4s.optics

import org.json4s.JsonAST.{JValue, JNumber, JDouble, JDecimal, JInt, JBool}
import com.github.aoiroaoino.json4s.optics.JNumberOptics._

class JNumberOpticsSpec extends TestSuite {

  val jDouble: JValue  = JDouble(1.1)
  val jDecimal: JValue = JDecimal(1.1)
  val jInt: JValue     = JInt(10)
  val other: JValue    = JBool(true)

  describe("JDouble") {
    it("getOption") {
      jDoublePrism.getOption(jDouble) shouldEqual Some(1.1)
    }

    it("set") {
      jDoublePrism.set(1.5)(jDouble) shouldEqual JDouble(1.5)
    }

    it("modify") {
      jDoublePrism.modify(_ + 0.5)(jDouble) shouldEqual JDouble(1.6)
    }

    it("Other JValue") {
      jDoublePrism.getOption(other) shouldEqual None
    }
  }

  describe("JDecimal") {
    it("getOption") {
      jDecimalPrism.getOption(jDecimal) shouldEqual Some(1.1)
    }

    it("set") {
      jDecimalPrism.set(BigDecimal(1.5))(jDecimal) shouldEqual JDecimal(1.5)
    }

    it("modify") {
      jDecimalPrism.modify(_ + 0.5)(jDecimal) shouldEqual JDecimal(1.6)
    }

    it("Other JValue") {
      jDecimalPrism.getOption(other) shouldEqual None
    }
  }

  describe("JInt") {
    it("getOption") {
      jIntPrism.getOption(jInt) shouldEqual Some(10)
    }

    it("set") {
      jIntPrism.set(5)(jInt) shouldEqual JInt(5)
    }

    it("modify") {
      jIntPrism.modify(_ + 10)(jInt) shouldEqual JInt(20)
    }

    it("Other JValue") {
      jIntPrism.getOption(other) shouldEqual None
    }
  }
}
