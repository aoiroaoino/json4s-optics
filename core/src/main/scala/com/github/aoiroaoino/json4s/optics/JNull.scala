package com.github.aoiroaoino.json4s.optics

import monocle.Prism
import monocle.function.Empty

import org.json4s.JsonAST.{JValue, JNull}

trait JNullOptics {

  def _jNull: Prism[JValue, JNull.type] =
    jNullPrism

  def jNullPrism = Prism[JValue, JNull.type] {
    jValue => if (jValue == JNull) Some(JNull) else None
  }(_ => JNull: JValue)
}

trait JNullInstances {

  implicit def jNullEmpty: Empty[JNull.type] = new Empty[JNull.type] {
    def empty: Prism[JNull.type, Unit] =
      Prism[JNull.type, Unit](_ => Some(()))(_ => JNull)
  }
}