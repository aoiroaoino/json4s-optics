package com.github.aoiroaoino.json4s.optics

import monocle.Prism
import org.json4s.JsonAST.{JValue, JNumber, JDouble, JDecimal, JInt}

trait JNumberOptics {

  def jNumberPrism = Prism[JValue, JNumber] {
    case j: JNumber => Some(j)
    case _           => None
  }(jNumberToJValue)

  def jNumberToDouble = Prism[JNumber, Double] {
    case JDouble(j) => Some(j)
    case _          => None
  }(JDouble(_))

  def jNumberToDecimal = Prism[JNumber, BigDecimal] {
    case JDecimal(j) => Some(j)
    case _           => None
  }(JDecimal(_))

  def jNumberToInt = Prism[JNumber, BigInt] {
    case JInt(j) => Some(j)
    case _       => None
  }(JInt(_))

  def jDoublePrism =
    jNumberPrism ^<-? jNumberToDouble

  def jDecimalPrism =
    jNumberPrism ^<-? jNumberToDecimal

  def jIntPrism =
    jNumberPrism ^<-? jNumberToInt

  // JNumber to JValue
  private def jNumberToJValue(j: JNumber): JValue = {
    j match {
      case x: JDouble  => x
      case x: JDecimal => x
      case x: JInt     => x
    }
  }
}

object JNumberOptics extends JNumberOptics
