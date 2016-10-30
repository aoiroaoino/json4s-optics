package com.github.aoiroaoino.json4s_optics

import monocle.{Lens, Prism, Iso}
import monocle.macros.GenLens

import org.json4s.JsonAST.{JValue, JBool}

object jbool extends JBoolOptics

trait JBoolOptics {

  def _jBool: Prism[JValue, Boolean] =
    jBoolPrism ^<-> jBoolToBoolean

  def jBoolLens: Lens[JBool, Boolean] =
    GenLens[JBool](_.value)

  def jBoolToBoolean: Iso[JBool, Boolean] =
    Iso[JBool, Boolean](_.value)(JBool.apply)

  def jBoolPrism = Prism[JValue, JBool] {
    case jb: JBool => Some(jb)
    case _         => None
  }(jBoolToJValue)

  private def jBoolToJValue(jb: JBool): JValue = jb
}
