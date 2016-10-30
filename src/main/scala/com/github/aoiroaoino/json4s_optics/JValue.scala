package com.github.aoiroaoino.json4s_optics

import monocle.Traversal
import monocle.function.Plated

import scalaz.Applicative

import org.json4s.JsonAST._

object jvalue extends JValueOptics

trait JValueOptics {
  import scalaz.std.list._
  import scalaz.std.map._
  import scalaz.syntax.traverse._

  implicit def jValuePlated: Plated[JValue] = new Plated[JValue] {
    val plate: Traversal[JValue, JValue] = new Traversal[JValue, JValue] {
      def modifyF[F[_]: Applicative](f: JValue => F[JValue])(x: JValue): F[JValue] =
        x match {
          case j@(JString(_) | JBool(_) | JDouble(_) | JDecimal(_) | JInt(_)| JNull | JNothing) => Applicative[F].point(j)
          case JArray(a) => a.traverse(f).map(JArray)
          case JObject(o) => o.toMap.traverse(f).map(_.toList).map(JObject.apply)
        }
    }
  }
}
