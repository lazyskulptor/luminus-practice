(ns html-templating.core
  (:require
   [selmer.parser :as selmer]))

(defn fn-1 []
  (selmer/render "Hello, {{name}}" {:name "World"}))
