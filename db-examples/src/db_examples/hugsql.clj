(ns db-examples.hugsql
  (:require [db-examples.core :refer [ds]]
            [next.jdbc :as jdbc]
            [hugsql.core :as hugsql]
            [hugsql.adapter.next-jdbc :as next-adapter]))

(hugsql/def-db-fns "users.sql"
  {:adapter (next-adapter/hugsql-adapter-next-jdbc)})

(def db (jdbc/get-connection ds))

(defn add-user-transaction [user]
  (jdbc/with-transaction [tx db]
    (if-not (find-user tx {:id (:id user)})
      (add-user! tx user))))
