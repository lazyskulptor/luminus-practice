(ns html-templating.core
  (:require
   [selmer.parser :as selmer]))

(defn fn-1 []
  (selmer/render "Hello, {{name}}" {:name "World"}))

(defn fn-2-1 []
  (selmer/render-file "hello.html" {:name "World"}))

(defn fn-2-2 []
  (selmer/render-file "hello-2.html" {:items (range 10)}))

(defn fn-2-3 []
  (selmer/render "<p>Hello {{user.first}} {{user.last}}</p>"
                 {:user {:first "John" :last "Doe"}}))
