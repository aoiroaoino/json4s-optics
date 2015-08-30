package com.github.aoiroaoino.json4s.optics

import monocle.{Prism, Iso}
import org.json4s.JsonAST.{JValue, JNumber, JDouble, JDecimal, JInt}

trait JNumberOptics {

  def _jDouble: Prism[JValue, Double] =
    jNumberPrism ^<-? jDoublePrism ^<-> jDoubleToDouble

  def _jDecimal: Prism[JValue, BigDecimal] =
    jNumberPrism ^<-? jDecimalPrism ^<-> jDecimalToBigDecimal

  def _jInt: Prism[JValue, BigInt] =
    jNumberPrism ^<-? jIntPrism ^<-> jIntToBigInt


  //=== JNumber Prisms
  def jNumberPrism = Prism[JValue, JNumber] {
    case jn: JNumber => Some(jn)
    case _          => None
  }(jNumberToJValue)

  def jDoublePrism = Prism[JNumber, JDouble] {
    case jd: JDouble => Some(jd)
    case _           => None
  }(castToJNumberFrom[JDouble])

  def jDecimalPrism = Prism[JNumber, JDecimal] {
    case jd: JDecimal => Some(jd)
    case _            => None
  }(castToJNumberFrom[JDecimal])

  def jIntPrism = Prism[JNumber, JInt] {
    case ji: JInt => Some(ji)
    case _        => None
  }(castToJNumberFrom[JInt])


  //=== Iso
  def jDoubleToDouble: Iso[JDouble, Double] =
    Iso[JDouble, Double](_.num)(JDouble.apply)

  def jDecimalToBigDecimal: Iso[JDecimal, BigDecimal] =
    Iso[JDecimal, BigDecimal](_.num)(JDecimal.apply)

  def jIntToBigInt: Iso[JInt, BigInt] =
    Iso[JInt, BigInt](_.num)(JInt.apply)


  // JNumber to JValue
  private def jNumberToJValue(j: JNumber): JValue = {
    j match {
      case x: JDouble  => x
      case x: JDecimal => x
      case x: JInt     => x
    }
  }

  // JDouble, JDecimal or JInt to JNumber
  private def castToJNumberFrom[A <: JNumber](j: A): JNumber = j
}

object JNumberOptics extends JNumberOptics
