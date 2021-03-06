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

(defn add-user! [ds user]
  (sql/insert! ds :users user))

(defn add-users! [ds users]
  (sql/insert-multi! ds :users [:id :pass] users))

(defn set-pass! [ds id pass]
  (sql/update!
   ds
   :users
   {:pass pass}
   ["id=?" id]))

(defn remove-user! [ds id]
  (sql/delete! ds :users ["id=?" id]))
