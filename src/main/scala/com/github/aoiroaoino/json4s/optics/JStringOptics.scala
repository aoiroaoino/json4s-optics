package com.github.aoiroaoino.json4s.optics

import monocle.Prism
import org.json4s.JsonAST.{JValue, JString}

trait JStringOptics {

  def jStringPrism = Prism[JValue, String] {
    case JString(s) => Some(s)
    case _          => None
  }(JString(_))
}

object JStringOptics extends JStringOptics
