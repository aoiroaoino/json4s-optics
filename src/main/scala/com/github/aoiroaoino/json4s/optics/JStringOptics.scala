package com.github.aoiroaoino.json4s.optics

import monocle.{Prism, Iso}
import monocle.function.Empty

import org.json4s.JsonAST.{JValue, JString}

trait JStringOptics {

  def _jString: Prism[JValue, String] =
    jStringPrism ^<-> jStringToString

  def jStringToString: Iso[JString, String] =
    Iso[JString, String](_.s)(JString.apply)

  def jStringPrism = Prism[JValue, JString] {
    case js: JString => Some(js)
    case _           => None
  }(jStringToJValue)

  private def jStringToJValue(js: JString): JValue = js

  implicit def jStringEmpty: Empty[JString] = new Empty[JString] {
    def empty: Prism[JString, Unit] =
      Prism[JString, Unit](js => if(js.s.isEmpty) Some(()) else None)(_ => JString(""))
  }
}

object JStringOptics extends JStringOptics
