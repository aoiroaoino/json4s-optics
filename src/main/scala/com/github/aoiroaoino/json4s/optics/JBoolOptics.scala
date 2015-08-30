package com.github.aoiroaoino.json4s.optics

import monocle.{Prism, Iso}

import org.json4s.JsonAST.{JValue, JBool}

trait JBoolOptics {

  def _jBool: Prism[JValue, Boolean] =
    jBoolPrism ^<-> jBoolToBoolean

  def jBoolToBoolean: Iso[JBool, Boolean] =
    Iso[JBool, Boolean](_.value)(JBool.apply)

  def jBoolPrism = Prism[JValue, JBool] {
    case jb: JBool => Some(jb)
    case _         => None
  }(jBoolToJValue)

  private def jBoolToJValue(jb: JBool): JValue = jb
}

object JBoolOptics extends JBoolOptics
