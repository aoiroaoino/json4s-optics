package com.github.aoiroaoino.json4s_optics

import monocle.{Lens, Prism, Iso}
import monocle.function.Empty
import monocle.macros.GenLens

import org.json4s.JsonAST.{JValue, JArray}

object jarray extends JArrayOptics

trait JArrayOptics {

  def _jArray: Prism[JValue, List[JValue]] =
    jArrayPrism ^<-> jArrayToList

  def jArrayToList: Iso[JArray, List[JValue]] =
    Iso[JArray, List[JValue]](_.arr)(JArray.apply)

  def jArrayPrism = Prism[JValue, JArray] {
    case ja: JArray => Some(ja)
    case _          => None
  }(jValueToJArray)

  private def jValueToJArray(ja: JArray): JValue = ja


  implicit def jArrayEmpty: Empty[JArray] = new Empty[JArray] {
    def empty: Prism[JArray, Unit] =
      Prism((ja: JArray) => if (ja.arr.isEmpty) Some(()) else None)(_ => JArray(List.empty[JValue]))
  }
}
