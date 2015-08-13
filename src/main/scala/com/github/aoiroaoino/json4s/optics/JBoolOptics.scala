package com.github.aoiroaoino.json4s.optics

import monocle.Prism
import org.json4s.JsonAST.{JValue, JBool}

trait JBoolOptics {

  def jBoolPrism = Prism[JValue, Boolean] {
    case JBool(b) => Some(b)
    case _        => None
  }(JBool.apply)
}

object JBoolOptics extends JBoolOptics
