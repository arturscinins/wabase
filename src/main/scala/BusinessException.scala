package org.wabase

class BusinessException(message: String, cause: Throwable, params: Any*)
  extends RuntimeException(message, cause) {
  def getParams() = this.params
  def this(message: String) = this(message, null, Nil: _*)
  def this(message: String, cause: Throwable) = this(message, cause, Nil: _*)
}

object BusinessException {
  def apply(message: String):BusinessException = new BusinessException(message, null, Nil: _*)
  def apply(message: String, cause: Throwable):BusinessException = new BusinessException(message, cause, Nil: _*)
}
