package com.github.aoiroaoino.json4s.optics

import monocle.Prism
import org.json4s.JsonAST.{JValue, JArray}

trait JArrayOptics {

  def jArrayPrism = Prism[JValue, List[JValue]] {
    case ja: JArray => Some(ja.arr)
    case _          => None
  }(JArray.apply)
}

object JArrayOptics extends JArrayOptics
