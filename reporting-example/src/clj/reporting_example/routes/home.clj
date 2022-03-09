(ns reporting-example.routes.home
  (:require
   [reporting-example.layout :as layout]
   [reporting-example.db.core :as db]
   [clojure.java.io :as io]
   [clojure.tools.logging :as log]
   [reporting-example.middleware :as middleware]
   [ring.util.http-response :as response]
   [reporting-example.reports :as reports]))

(defn home-page [request]
  (layout/render request "home.html"))

(defn write-response [report-bytes]
  (with-open [in (java.io.ByteArrayInputStream. report-bytes)]
    (-> (response/ok in)
        (response/header "Content-Disposition" "filename=document.pdf")
        (response/header "Content-Length" (count report-bytes))
        (response/content-type "application/pdf"))))

(defn generate-report [{:keys [path-params] :as request}]
  (try
    (let [report-type (:report-type path-params)
          out (java.io.ByteArrayOutputStream.)]
      (condp = (keyword report-type)
        :table (reports/table-report out)
        :list (reports/list-report out))
      (write-response (.toByteArray out)))
    (catch Exception ex
      (log/error ex "failed to render report!")
      (layout/render request "home.html" {:error (.getMessage ex)}))))

(defn about-page [request]
  (layout/render request "about.html"))

(defn home-routes []
  [ "" 
   {:middleware [middleware/wrap-csrf
                 middleware/wrap-formats]}
   ["/" {:get home-page}]
   ["/about" {:get about-page}]
   ["/report/:report-type" {:get generate-report}]])

