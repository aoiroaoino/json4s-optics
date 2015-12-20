package com.github.aoiroaoino.json4s.optics

trait AllOptics
  extends JValueOptics
  with    JObjectOptics
  with    JArrayOptics
  with    JNumberOptics
  with    JBoolOptics
  with    JStringOptics
  with    JNullOptics
