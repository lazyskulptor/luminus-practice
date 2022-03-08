(ns db-examples.core
  (:require
   [next.jdbc :as jdbc]
   [next.jdbc.sql :as sql]
   [next.jdbc.resut-set :as rs]))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))
