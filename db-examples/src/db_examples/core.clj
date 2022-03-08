(ns db-examples.core
  (:require
   [next.jdbc :as jdbc]))
   ;; [next.jdbc.sql :as sql]
   ;; [next.jdbc.resut-set :as rs]))

(def ds (jdbc/get-datasource
         {:subprotocol "postgresql"
          :dbtype "postgres"
          :host "192.168.7.77"
          :dbname "reporting"
          :port 5454
          :user "admin"
          :password "admin"}))
