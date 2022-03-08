(ns db-examples.core
  (:require
   [next.jdbc :as jdbc]
   [next.jdbc.sql :as sql]))
   ;; [next.jdbc.resut-set :as rs]))

(def ds (jdbc/get-datasource
         {:subprotocol "postgresql"
          :dbtype "postgres"
          :host "192.168.7.77"
          :dbname "reporting"
          :port 5454
          :user "admin"
          :password "admin"}))

(defn create-users-table! [ds]
  (jdbc/execute! ds
                 ["create table users (
                    id varchar(32) primary key,
                    pass varchar(100)
                    )"]))

(defn get-user [ds id]
  (first (sql/query ds ["select * from users where id = ?" id])))
