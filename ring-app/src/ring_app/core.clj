(ns ring-app.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.util.response :as response]))

(defn handler [request-map]
  (response/response
   (str "<html><body> your IP is : "
        (:remote-addr request-map)
        "</body></html>")))

(defn -main []
  (jetty/run-jetty
   handler
   {:port 3000
    :join? false}))
